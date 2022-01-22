<template>
  <div>
    <v-card id="tableView" justify-center>
      <div>
        <v-data-table
          :headers="headersShipFastReservation"
          :items="shipFastReservations"
          hide-default-footer
          no-data-text="There are no fast reservations"
          class="elevation-1"
        >
          <template v-slot:top>
            <v-toolbar flat>
              <v-toolbar-title style="color: gray"
                >Ship fast reservations</v-toolbar-title
              >
              <v-divider class="mx-4" inset vertical></v-divider>
              <v-spacer></v-spacer>
              <v-dialog
                v-model="dialogShipFastReservation"
                width="35%"
                persistent
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="purple"
                    dark
                    class="mb-2"
                    v-bind="attrs"
                    v-on="on"
                  >
                    New reservation
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span class="text-h5">{{ "New reservation" }}</span>
                  </v-card-title>

                  <v-card-text>
                    <v-container>
                      <v-simple-table>
                        <tr>
                          <td>
                            <v-menu
                              v-model="shipReservationDateMenu"
                              :close-on-content-click="false"
                              :nudge-right="40"
                              transition="scale-transition"
                              min-width="auto"
                            >
                              <template v-slot:activator="{ on, attrs }">
                                <v-text-field
                                  v-model="shipReservationDate"
                                  label="Reservation start date"
                                  prepend-icon="mdi-calendar"
                                  readonly
                                  v-bind="attrs"
                                  v-on="on"
                                ></v-text-field>
                              </template>
                              <v-date-picker
                                v-model="shipReservationDate"
                                @input="shipReservationDateMenu = false"
                              ></v-date-picker>
                            </v-menu>
                            <v-spacer></v-spacer>
                          </td>
                          <td>
                            <v-menu
                              ref="shipReservationTimeMenu"
                              v-model="shipReservationTimeMenu"
                              :close-on-content-click="false"
                              :nudge-right="40"
                              transition="scale-transition"
                              max-width="290px"
                              min-width="290px"
                            >
                              <template v-slot:activator="{ on, attrs }">
                                <v-text-field
                                  v-model="shipReservationTime"
                                  label="Reservation start time"
                                  prepend-icon="mdi-clock-time-four-outline"
                                  readonly
                                  v-bind="attrs"
                                  v-on="on"
                                ></v-text-field>
                              </template>
                              <v-time-picker
                                v-model="shipReservationTime"
                                full-width
                                @click:minute="
                                  $refs.shipReservationTimeMenu.save(
                                    shipReservationTime
                                  )
                                "
                              ></v-time-picker>
                            </v-menu>
                          </td>
                        </tr>
                      </v-simple-table>
                      <v-text-field
                        v-model="shipFastReservationItem.duration"
                        label="Duration (hours)"
                        type="text"
                      >
                      </v-text-field>
                      <v-text-field
                        v-model="shipFastReservationItem.maxNumberOfPersons"
                        label="Max number of persons"
                        type="text"
                      >
                      </v-text-field>
                      <v-text-field
                        v-model="shipFastReservationItem.price"
                        label="Price (RSD)"
                        type="text"
                      >
                      </v-text-field>
                      <v-text-field
                        v-model="shipFastReservationItem.discountPercentage"
                        label="Discount (%)"
                        type="text"
                      >
                      </v-text-field>
                      <v-card-text>
                        <h2 class="text-h6 mb-2">
                          Ship additional services
                        </h2>
                        <v-chip-group
                          v-model="
                            shipFastReservationItem.shipAdditionalServices
                          "
                          column
                          multiple
                        >
                          <v-chip filter outlined> WiFi </v-chip>
                          <v-chip filter outlined> Minibar </v-chip>
                          <v-chip filter outlined> Pet friendly </v-chip>
                        </v-chip-group>
                      </v-card-text>
                      <v-card-text>
                        <h2 class="text-h6 mb-2">
                          Navigation equipment
                        </h2>
                        <v-chip-group
                          v-model="
                            shipFastReservationItem.shipFishingEquipment
                          "
                          column
                          multiple
                        >
                          <v-chip filter outlined> Fishing rod </v-chip>
                          <v-chip filter outlined> Fishing net </v-chip>
                        </v-chip-group>
                      </v-card-text>
                    </v-container>
                  </v-card-text>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="closeShipFastReservation"
                    >
                      Cancel
                    </v-btn>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="saveShipFastReservation"
                    >
                      Save
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
              <v-dialog
                v-model="dialogShipFastReservationDelete"
                max-width="500px"
              >
                <v-card>
                  <v-card-title class="text-h6"
                    >Are you sure you want to delete this
                    reservation?</v-card-title
                  >
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="closeShipFastReservationDelete"
                      >Cancel</v-btn
                    >
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="deleteShipFastReservationItemConfirm"
                      >OK</v-btn
                    >
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.actions`]="{ item }">
            <v-icon small @click="deleteShipFastReservationItem(item)" v-if="item.shipFastReservationId !== null">
              mdi-delete
            </v-icon>
            <v-icon small disabled v-else>
              mdi-delete
            </v-icon>
          </template>
        </v-data-table>
      </div>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "ShipFastReservation",

  data: () => ({
    shipReservationDateMenu: false,
    shipReservationTimeMenu: false,
    shipReservationDate: "",
    shipReservationTime: "",

    shipFastReservations: [],
    shipReservationAdditionalServicesValues: [],
    shipFishingEquipmentValues: [],
    dialogShipFastReservation: false,
    dialogShipFastReservationDelete: false,

    headersShipFastReservation: [
      {
        text: "Start date and time",
        align: "center",
        sortable: true,
        value: "dateAndTime",
      },
      {
        text: "Duration (hours)",
        align: "center",
        sortable: true,
        value: "duration",
      },
      {
        text: "Max number of persons",
        align: "center",
        sortable: true,
        value: "maxNumberOfPersons",
      },
      {
        text: "Ship additional services",
        align: "center",
        sortable: true,
        value: "shipAdditionalServices",
      },
      {
        text: "Ship fishing equipment",
        align: "center",
        sortable: true,
        value: "shipFishingEquipment",
      },
      {
        text: "Price (RSD)",
        align: "center",
        sortable: true,
        value: "price",
      },
      {
        text: "Discount (%)",
        align: "center",
        sortable: true,
        value: "discountPercentage",
      },
      { text: "Actions", align: "center", value: "actions", sortable: false },
    ],

    shipFastReservationItemIndex: -1,
    shipFastReservationItem: {
      shipFastReservationId: null,
      dateAndTime: "",
      duration: "",
      maxNumberOfPersons: "",
      shipAdditionalServices: [],
      shipFishingEquipment: [],
      price: "",
      discountPercentage: "",
    },
    shipFastReservationDefaultItem: {
      shipFastReservationId: null,
      dateAndTime: "",
      duration: "",
      maxNumberOfPersons: "",
      shipAdditionalServices: [],
      shipFishingEquipment: [],
      price: "",
      discountPercentage: "",
    },
    shipAdditionalServicesRequest: [],
    shipFishingEquipmentRequest: [],
    formattedShipAdditionalServices: [],
    formattedShipFishingEquipment: [],
  }),

  watch: {
    dialogShipFastReservation(val) {
      val || this.closeShipFastReservation();
    },

    dialogShipFastReservationDelete(val) {
      val || this.closeShipFastReservationDelete();
    },
  },

  mounted() {
    this.getAllAdditionalServicesForShipReservation();
    this.getAllFastReservationsForShip();
    this.getAllFishingEquipmentForShip();
  },

  methods: {
    getAllFastReservationsForShip() {
      this.shipId = localStorage.getItem("shipId");
      this.axios
        .get(
          "http://localhost:8091/reservationShip/shipFastReservations/" +
            this.shipId,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          this.shipFastReservations = response.data;
          console.log(this.shipFastReservations);
          this.shipFastReservations.forEach((reservation) => {
            reservation.dateAndTime = new Date(reservation.dateAndTime).toLocaleString();
            let reservationAdditionalServices = [];
            let reservationFishingEquipment = [];
            reservation.shipAdditionalServices.forEach((element) => {
              reservationAdditionalServices.push(element.shipAdditionalServiceType);
            });
            reservation.fishingEquipmentForShip.forEach((element) => {
              reservationFishingEquipment.push(element.fishingEquipmentForShipType);
            });
            reservation.shipAdditionalServices = reservationAdditionalServices;
            reservation.shipFishingEquipment = reservationFishingEquipment;
            reservationAdditionalServices = [];

            if (reservation.discountPercentage === null) {
              reservation.discountPercentage = "No discount";
            }
          });
        })
        .catch((err) => {
          window.location.href = "http://localhost:8083/";
          alert("401 Unauthorized");
          console.log(err);
        });
    },

    getAllAdditionalServicesForShipReservation() {
      this.axios
        .get("http://localhost:8091/shipAdditionalService/", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          this.shipReservationAdditionalServicesValues = response.data;
        })
        .catch((err) => {
          window.location.href = "http://localhost:8083/";
          alert("401 Unauthorized");
          console.log(err);
        });
    },

    getAllFishingEquipmentForShip() {
        this.axios
        .get("http://localhost:8091/fishingEquipment/", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          this.shipFishingEquipmentValues = response.data;
        })
        .catch((err) => {
          window.location.href = "http://localhost:8083/";
          alert("401 Unauthorized");
          console.log(err);
        });
    },

    saveShipFastReservation() {
      if (this.shipFastReservationItemIndex > -1) {
        Object.assign(
          this.shipFastReservations[this.shipFastReservationItemIndex],
          this.shipFastReservationItem
        );
      } else {
        this.shipId = localStorage.getItem("shipId");
        this.shipFastReservationItem.dateAndTime = new Date(
          this.shipReservationDate +
            " " +
            this.shipReservationTime +
            ":00"
        ).toLocaleString();
        this.shipFastReservationItem.shipAdditionalServices.forEach(
          (element) => {
            this.shipAdditionalServicesRequest.push(
              this.shipReservationAdditionalServicesValues[element]
            );
          }
        );
        this.shipFastReservationItem.shipFishingEquipment.forEach(
          (element) => {
            this.shipFishingEquipmentRequest.push(
              this.shipFishingEquipmentValues[element]
            );
          }
        );
        
        this.axios
          .post(
            "http://localhost:8091/reservationShip/saveFastReservation/" +
              this.shipId,
            {
              dateAndTime: new Date(this.shipFastReservationItem.dateAndTime),
              formattedDateAndTime: this.shipFastReservationItem.formattedDateAndTime,
              duration: this.shipFastReservationItem.duration,
              maxNumberOfPersons: this.shipFastReservationItem.maxNumberOfPersons,
              shipAdditionalServices: this.shipAdditionalServicesRequest,
              fishingEquipmentForShip: this.shipFishingEquipmentRequest,
              price: this.shipFastReservationItem.price,
              discountPercentage: this.shipFastReservationItem.discountPercentage,
            },
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            }
          )
          .then((response) => {
            console.log(response.data);
            this.shipAdditionalServicesRequest = [];
            this.shipFishingEquipmentRequest = [];
            this.shipId = localStorage.getItem("shipId");
            this.getAllFastReservationsForShip();
          });

        if (this.shipFastReservationItem.discountPercentage === "") {
          this.shipFastReservationItem.discountPercentage = "No discount";
        }

        this.shipFastReservationItem.shipAdditionalServices.forEach(
          (element) => {
            this.formattedShipAdditionalServices.push(
              this.shipReservationAdditionalServicesValues[element]
                .shipAdditionalServiceType
            );
          }
        );
        this.shipFastReservationItem.shipFishingEquipment.forEach(
          (element) => {
            this.formattedShipFishingEquipment.push(
              this.shipFishingEquipmentValues[element]
                .fishingEquipmentForShipType
            );
          }
        );
        this.shipFastReservationItem.shipAdditionalServices = this.formattedShipAdditionalServices;
        this.shipFastReservationItem.shipFishingEquipment = this.formattedShipFishingEquipment;
        this.shipFastReservations.push(this.shipFastReservationItem);
        this.formattedShipAdditionalServices = [];
        this.formattedShipFishingEquipment = [];
        this.shipReservationDate = "";
        this.shipReservationTime = "";
      }
      this.closeShipFastReservation();
    },

    deleteShipFastReservationItemConfirm() {
      this.axios
        .post(
          "http://localhost:8091/reservationShip/deleteFastReservation",
          {
            shipReservationId:
              this.shipFastReservationItem.shipFastReservationId,
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
        });

      this.shipFastReservations.splice(
        this.shipFastReservationItemIndex,
        1
      );
      this.closeShipFastReservationDelete();
    },

    deleteShipFastReservationItem(item) {
      this.shipFastReservationItemIndex =
        this.shipFastReservations.indexOf(item);
      this.shipFastReservationItem = Object.assign({}, item);
      this.dialogShipFastReservationDelete = true;
    },

    closeShipFastReservation() {
      this.dialogShipFastReservation = false;
      this.shipReservationDate = "";
      this.shipReservationTime = "";
      this.$nextTick(() => {
        this.shipFastReservationItem = Object.assign(
          {},
          this.shipFastReservationDefaultItem
        );
        this.shipFastReservationItemIndex = -1;
      });
    },

    closeShipFastReservationDelete() {
      this.dialogShipFastReservationDelete = false;
      this.$nextTick(() => {
        this.shipFastReservationItem = Object.assign(
          {},
          this.shipFastReservationDefaultItem
        );
        this.shipFastReservationItemIndex = -1;
      });
    },
  },
};
</script>

<style scoped>
#tableView {
  width: 83%;
  text-align: center;
  margin: auto;
  margin-top: 5%;
}
</style>