<template>
  <div>
    <v-card id="tableView" justify-center>
      <div>
        <v-data-table
          :headers="shipReservationHeaders"
          :items="shipReservations"
          class="elevation-1"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center"
                >Finished ship reservations</v-toolbar-title
              >
              <v-spacer></v-spacer>

              <v-dialog v-model="dialogClientInformation" max-width="75%">
                <v-card>
                  <v-card-title>
                    <span class="text-h5 justify-center"
                      >Information about client</span
                    >
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="
                              shipReservationItem
                                .shipReservationClientInformationDTO
                                .firstName
                            "
                            label="First name"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="
                              shipReservationItem
                                .shipReservationClientInformationDTO.lastName
                            "
                            label="Last name"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="
                              shipReservationItem
                                .shipReservationClientInformationDTO.email
                            "
                            label="Email"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="
                              shipReservationItem
                                .shipReservationClientInformationDTO
                                .phoneNumber
                            "
                            label="Phone number"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="formattedShipReservationAddress"
                            label="Address"
                            readonly
                          ></v-text-field>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="closeClientInformationDialog"
                    >
                      Ok
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog v-model="dialogReportForm" width="30%" persistent>
                <v-card>
                  <v-card-title>
                    <span class="text-h5">Report form</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-text-field 
                        v-model="comment"
                        label="Comment"
                        type="text"
                        :rules="[() => !!comment || 'This field is required']"
                      >
                      </v-text-field>
                      <v-radio-group
                        v-model="clientArrival"
                        hide-details
                      >
                      <v-radio
                        value="client-arrived"
                        label="Client arrived">
                      </v-radio>
                      <v-radio
                        value="client-didnt-arrive"
                        label="Client didn't arrive">
                      </v-radio>
                      </v-radio-group>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="saveReport"
                    >
                      Save
                    </v-btn>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="closeReportFormDialog"
                    >
                      Cancel
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.clientInformation`]="{ item }">
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue" text @click="displayClientInformation(item)">
                VIEW
              </v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </template>
          <template v-slot:[`item.reportAction`]="{ item }">
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                class="ma-2"
                outlined
                color="indigo"
                text
                @click="displayReportForm(item)"
              >
                WRITE A REPORT
              </v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </template>
        </v-data-table>
      </div>
    </v-card>
  </div>
</template>


<script>
export default {
  name: "ShipOwnerReservationHistory",

  data: () => ({
    shipReservations: [],
    dialogClientInformation: false,
    dialogReportForm: false,
    comment: "",
    clientArrival: "",
    shipReservationId: null,
    clientEmail: "",
    index: null,
    reservationItem: null,
    reservationDefaultItem: null,
    shipReservationHeaders: [
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
        text: "Price (RSD)",
        align: "center",
        sortable: true,
        value: "price",
      },
      {
        text: "Ship",
        align: "center",
        sortable: true,
        value: "shipName",
      },
      {
        text: "Capacity",
        align: "center",
        sortable: true,
        value: "capacity",
      },
      {
        text: "Engine number",
        align: "center",
        sortable: true,
        value: "engineNumber",
      },
      {
        text: "Engine power",
        align: "center",
        sortable: true,
        value: "enginePower",
      },
      {
        text: "Status",
        align: "center",
        sortable: true,
        value: "reservationStatus",
      },
      {
        text: "Client information",
        align: "center",
        sortable: false,
        value: "clientInformation",
      },
      {
        text: "",
        align: "center",
        sortable: false,
        value: "reportAction",
      },
    ],

    shipReservationItemIndex: -1,
    shipReservationItem: {
      shipReservationId: 0,
      dateAndTime: "",
      duration: 0,
      price: 0,
      shipName: "",
      capacity: 0,
      engineNumber: 0,
      enginePower: 0,
      shipReservationClientInformationDTO: {
        firstName: "",
        lastName: "",
        email: "",
        phoneNumber: "",
        address: {
          street: "",
          streetNumber: "",
          city: "",
          country: "",
          longitude: 0,
          latitude: 0,
        },
      },
      reservationStatus: "",
    },
    formattedShipReservationAddress: "",
  }),

  mounted() {
    this.getAllFinishedReservationsForShipOwner();
  },

  methods: {
    getAllFinishedReservationsForShipOwner() {
      this.shipOwnerId = localStorage.getItem("userId");
      this.axios
        .get(
          "http://localhost:8091/reservationShip/shipOwnerFinishedReservations/" +
            this.shipOwnerId,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          this.shipReservations = response.data;
          this.shipReservations.forEach((reservation) => {
            reservation.dateAndTime = new Date(
              reservation.dateAndTime
            ).toLocaleString();
          });
        })
        .catch((err) => {
          window.location.href = "http://localhost:8083/";
          alert("401 Unauthorized");
          console.log(err);
        });
    },

    saveReport() {
      if (!this.validateComment() || !this.validateOption()) {
        return;
      }

      this.shipReservations.splice(this.index, 1);
      this.axios
        .post(
          "http://localhost:8091/reservationShip/report",
          {
            comment: this.comment,
            clientArrival: this.clientArrival,
            clientEmail: this.clientEmail,
            shipReservationId: this.shipReservationId,
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

      this.closeReportFormDialog();
    },

    validateComment() {
      if (this.comment.length == 0 || this.comment.match(/[ ]/g)) {
        return false;
      }

      return true;
    },

    validateOption() {
      if (this.clientArrival.length == 0) {
        return false;
      }

      return true;
    },

    displayClientInformation(item) {
      this.shipReservationItemIndex = this.shipReservations.indexOf(item);
      this.shipReservationItem = Object.assign({}, item);
      this.formattedShipReservationAddress =
        this.shipReservationItem.shipReservationClientInformationDTO
          .address.street +
        " " +
        this.shipReservationItem.shipReservationClientInformationDTO
          .address.streetNumber +
        " " +
        this.shipReservationItem.shipReservationClientInformationDTO
          .address.city +
        ", " +
        this.shipReservationItem.shipReservationClientInformationDTO
          .address.country;

      this.dialogClientInformation = true;
    },

    displayReportForm(item) {
      this.comment = "";
      this.clientArrival = "";
      this.index = this.shipReservations.indexOf(item);
      this.reservationItem = Object.assign({}, item);
      this.shipReservationId = this.reservationItem.shipReservationId;
      this.clientEmail = this.reservationItem.shipReservationClientInformationDTO.email;
      this.dialogReportForm = true;
    },

    closeClientInformationDialog() {
      this.dialogClientInformation = false;
    },

    closeReportFormDialog() {
      this.dialogReportForm = false;
      this.$nextTick(() => {
        this.reservationItem = Object.assign({}, this.reservationDefaultItem);
        this.editedIndex = -1;
      });
    },
  },
};
</script>

<style scoped>
#tableView {
  width: 90%;
  text-align: center;
  margin: auto;
  margin-top: 5%;
}
</style>