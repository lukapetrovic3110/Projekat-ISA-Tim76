<template>
  <div>
    <v-card id="tableView" justify-center>
      <div>
        <v-data-table
          :headers="headersCottageFastReservation"
          :items="cottageFastReservations"
          hide-default-footer
          no-data-text="There are no fast reservations"
          class="elevation-1"
        >
          <template v-slot:top>
            <v-toolbar flat>
              <v-toolbar-title style="color: gray"
                >Cottage fast reservations</v-toolbar-title
              >
              <v-divider class="mx-4" inset vertical></v-divider>
              <v-spacer></v-spacer>
              <v-dialog
                v-model="dialogCottageFastReservation"
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
                              v-model="cottageReservationDateMenu"
                              :close-on-content-click="false"
                              :nudge-right="40"
                              transition="scale-transition"
                              min-width="auto"
                            >
                              <template v-slot:activator="{ on, attrs }">
                                <v-text-field
                                  v-model="cottageReservationDate"
                                  label="Reservation start date"
                                  prepend-icon="mdi-calendar"
                                  readonly
                                  v-bind="attrs"
                                  v-on="on"
                                ></v-text-field>
                              </template>
                              <v-date-picker
                                v-model="cottageReservationDate"
                                @input="cottageReservationDateMenu = false"
                              ></v-date-picker>
                            </v-menu>
                            <v-spacer></v-spacer>
                          </td>
                          <td>
                            <v-menu
                              ref="cottageReservationTimeMenu"
                              v-model="cottageReservationTimeMenu"
                              :close-on-content-click="false"
                              :nudge-right="40"
                              transition="scale-transition"
                              max-width="290px"
                              min-width="290px"
                            >
                              <template v-slot:activator="{ on, attrs }">
                                <v-text-field
                                  v-model="cottageReservationTime"
                                  label="Reservation start time"
                                  prepend-icon="mdi-clock-time-four-outline"
                                  readonly
                                  v-bind="attrs"
                                  v-on="on"
                                ></v-text-field>
                              </template>
                              <v-time-picker
                                v-model="cottageReservationTime"
                                full-width
                                @click:minute="
                                  $refs.cottageReservationTimeMenu.save(
                                    cottageReservationTime
                                  )
                                "
                              ></v-time-picker>
                            </v-menu>
                          </td>
                        </tr>
                      </v-simple-table>
                      <v-text-field
                        v-model="cottageFastReservationItem.duration"
                        label="Duration (days)"
                        type="text"
                      >
                      </v-text-field>
                      <v-text-field
                        v-model="cottageFastReservationItem.maxNumberOfPersons"
                        label="Max number of persons"
                        type="text"
                      >
                      </v-text-field>
                      <v-text-field
                        v-model="cottageFastReservationItem.price"
                        label="Price (RSD)"
                        type="text"
                      >
                      </v-text-field>
                      <v-text-field
                        v-model="cottageFastReservationItem.discountPercentage"
                        label="Discount (%)"
                        type="text"
                      >
                      </v-text-field>
                      <v-card-text>
                        <h2 class="text-h6 mb-2">
                          Cottage additional services
                        </h2>
                        <v-chip-group
                          v-model="
                            cottageFastReservationItem.cottageAdditionalServices
                          "
                          column
                          multiple
                        >
                          <v-chip filter outlined> WiFi </v-chip>
                          <v-chip filter outlined> Parking </v-chip>
                          <v-chip filter outlined> Pet friendly </v-chip>
                        </v-chip-group>
                      </v-card-text>
                    </v-container>
                  </v-card-text>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="closeCottageFastReservation"
                    >
                      Cancel
                    </v-btn>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="saveCottageFastReservation"
                    >
                      Save
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
              <v-dialog
                v-model="dialogCottageFastReservationDelete"
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
                      @click="closeCottageFastReservationDelete"
                      >Cancel</v-btn
                    >
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="deleteCottageFastReservationItemConfirm"
                      >OK</v-btn
                    >
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.actions`]="{ item }">
            <v-icon small @click="deleteCottageFastReservationItem(item)">
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
  data: () => ({
    cottageReservationDateMenu: false,
    cottageReservationTimeMenu: false,
    cottageReservationDate: "",
    cottageReservationTime: "",

    cottageFastReservations: [],
    cottageReservationAdditionalServices: [],
    dialogCottageFastReservation: false,
    dialogCottageFastReservationDelete: false,

    headersCottageFastReservation: [
      {
        text: "Start date and time",
        align: "center",
        sortable: true,
        value: "dateAndTime",
      },
      {
        text: "Duration (days)",
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
        text: "Cottage additional services",
        align: "center",
        sortable: true,
        value: "cottageAdditionalServices",
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

    cottageFastReservationItemIndex: -1,
    cottageFastReservationItem: {
      cottageFastReservationId: null,
      dateAndTime: "",
      duration: "",
      maxNumberOfPersons: "",
      cottageAdditionalServices: [],
      price: "",
      discountPercentage: "",
    },
    cottageFastReservationDefaultItem: {
      cottageFastReservationId: null,
      dateAndTime: "",
      duration: "",
      maxNumberOfPersons: "",
      cottageAdditionalServices: [],
      price: "",
      discountPercentage: "",
    },
    cottageAdditionalServicesRequest: [],
    formattedCottageAdditionalServices: [],
  }),

  watch: {
    dialogCottageFastReservation(val) {
      val || this.closeCottageFastReservation();
    },

    dialogCottageFastReservationDelete(val) {
      val || this.closeCottageFastReservationDelete();
    },
  },

  mounted() {
    this.getAllAdditionalServicesForCottageReservation();
    this.getAllFastReservationsForCottage();
  },

  methods: {
    getAllFastReservationsForCottage() {
      this.cottageId = localStorage.getItem("cottageId");
      this.axios
        .get(
          "http://localhost:8091/reservationCottage/cottageOwnerFastReservations/" +
            this.cottageId,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          this.cottageFastReservations = response.data;
          console.log(this.cottageFastReservations);
          this.cottageFastReservations.forEach((reservation) => {
            reservation.dateAndTime = new Date(
              reservation.dateAndTime
            ).toLocaleString();
            let reservationAdditionalServices = [];
            reservation.cottageAdditionalServices.forEach((element) => {
              reservationAdditionalServices.push(
                element.cottageAdditionalServiceType
              );
            });
            reservation.cottageAdditionalServices =
              reservationAdditionalServices;
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

    getAllAdditionalServicesForCottageReservation() {
      this.axios
        .get("http://localhost:8091/reservationAdditionalService/", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          this.cottageReservationAdditionalServices = response.data;
        })
        .catch((err) => {
          window.location.href = "http://localhost:8083/";
          alert("401 Unauthorized");
          console.log(err);
        });
    },

    saveCottageFastReservation() {
      if (this.cottageFastReservationItemIndex > -1) {
        Object.assign(
          this.cottageFastReservations[this.cottageFastReservationItemIndex],
          this.cottageFastReservationItem
        );
      } else {
        this.cottageId = localStorage.getItem("cottageId");
        this.cottageFastReservationItem.dateAndTime = new Date(
          this.cottageReservationDate +
            " " +
            this.cottageReservationTime +
            ":00"
        );
        this.cottageFastReservationItem.cottageAdditionalServices.forEach(
          (element) => {
            this.cottageAdditionalServicesRequest.push(
              this.cottageReservationAdditionalServices[element]
            );
          }
        );

        this.axios
          .post(
            "http://localhost:8091/reservationCottage/saveFastReservation/" +
              this.cottageId,
            {
              dateAndTime: this.cottageFastReservationItem.dateAndTime,
              duration: this.cottageFastReservationItem.duration,
              maxNumberOfPersons:
                this.cottageFastReservationItem.maxNumberOfPersons,
              cottageAdditionalServices: this.cottageAdditionalServicesRequest,
              price: this.cottageFastReservationItem.price,
              discountPercentage:
                this.cottageFastReservationItem.discountPercentage,
            },
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            }
          )
          .then((response) => {
            console.log(response.data);
            this.cottageAdditionalServicesRequest = [];
          });

        if (this.cottageFastReservationItem.discountPercentage === "") {
          this.cottageFastReservationItem.discountPercentage = "No discount";
        }
        this.cottageFastReservationItem.cottageAdditionalServices.forEach(
          (element) => {
            this.formattedCottageAdditionalServices.push(
              this.cottageReservationAdditionalServices[element]
                .cottageAdditionalServiceType
            );
          }
        );
        this.cottageFastReservationItem.cottageAdditionalServices =
          this.formattedCottageAdditionalServices;
        this.cottageFastReservations.push(this.cottageFastReservationItem);
        this.formattedCottageAdditionalServices = [];
        this.cottageReservationDate = "";
        this.cottageReservationTime = "";
      }
      this.closeCottageFastReservation();
    },

    deleteCottageFastReservationItemConfirm() {
      this.axios
        .post(
          "http://localhost:8091/reservationCottage/deleteFastReservation/",
          {
            cottageReservationId:
              this.cottageFastReservationItem.cottageFastReservationId,
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

      this.cottageFastReservations.splice(
        this.cottageFastReservationItemIndex,
        1
      );
      this.closeCottageFastReservationDelete();
    },

    deleteCottageFastReservationItem(item) {
      this.cottageFastReservationItemIndex =
        this.cottageFastReservations.indexOf(item);
      this.cottageFastReservationItem = Object.assign({}, item);
      this.dialogCottageFastReservationDelete = true;
    },

    closeCottageFastReservation() {
      this.dialogCottageFastReservation = false;
      this.cottageReservationDate = "";
      this.cottageReservationTime = "";
      this.$nextTick(() => {
        this.cottageFastReservationItem = Object.assign(
          {},
          this.cottageFastReservationDefaultItem
        );
        this.cottageFastReservationItemIndex = -1;
      });
    },

    closeCottageFastReservationDelete() {
      this.dialogCottageFastReservationDelete = false;
      this.$nextTick(() => {
        this.cottageFastReservationItem = Object.assign(
          {},
          this.cottageFastReservationDefaultItem
        );
        this.cottageFastReservationItemIndex = -1;
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