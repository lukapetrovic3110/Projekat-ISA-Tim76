<template>
  <div>
    <v-card id="tableView" justify-center>
      <div>
        <v-data-table
          :headers="cottageReservationHeaders"
          :items="cottageReservations"
          class="elevation-1"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center"
                >Finished cottage reservations</v-toolbar-title
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
                              cottageReservationItem
                                .cottageReservationClientInformationDTO
                                .firstName
                            "
                            label="First name"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="
                              cottageReservationItem
                                .cottageReservationClientInformationDTO.lastName
                            "
                            label="Last name"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="
                              cottageReservationItem
                                .cottageReservationClientInformationDTO.email
                            "
                            label="Email"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="
                              cottageReservationItem
                                .cottageReservationClientInformationDTO
                                .phoneNumber
                            "
                            label="Phone number"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="formattedCottageReservationAddress"
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
  name: "CottageOwnerReservationHistory",
  data: () => ({
    cottageReservations: [],
    dialogClientInformation: false,
    dialogReportForm: false,
    comment: "",
    clientArrival: "",
    cottageReservationId: null,
    clientEmail: "",
    index: null,
    reservationItem: null,
    reservationDefaultItem: null,
    cottageReservationHeaders: [
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
        text: "Price (RSD)",
        align: "center",
        sortable: true,
        value: "price",
      },
      {
        text: "Cottage",
        align: "center",
        sortable: true,
        value: "cottageName",
      },
      {
        text: "Number of rooms",
        align: "center",
        sortable: true,
        value: "numberOfRooms",
      },
      {
        text: "Number of beds per room",
        align: "center",
        sortable: true,
        value: "numberOfBedsPerRoom",
      },
      {
        text: "Max number of persons",
        align: "center",
        sortable: true,
        value: "maxNumberOfPersons",
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

    cottageReservationItemIndex: -1,
    cottageReservationItem: {
      cottageReservationId: 0,
      dateAndTime: "",
      duration: 0,
      maxNumberOfPersons: 0,
      price: 0,
      cottageName: "",
      numberOfRooms: 0,
      numberOfBedsPerRoom: 0,
      cottageReservationClientInformationDTO: {
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
    formattedCottageReservationAddress: "",
  }),

  mounted() {
    this.getAllFinishedReservationsForCottageOwner();
  },

  methods: {
    getAllFinishedReservationsForCottageOwner() {
      this.cottageOwnerId = localStorage.getItem("userId");
      this.axios
        .get(
          "http://localhost:8091/reservationCottage/cottageOwnerFinishedReservations/" +
            this.cottageOwnerId,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          this.cottageReservations = response.data;
          this.cottageReservations.forEach((reservation) => {
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

      this.cottageReservations.splice(this.index, 1);
      this.axios
        .post(
          "http://localhost:8091/reservationCottage/report",
          {
            comment: this.comment,
            clientArrival: this.clientArrival,
            clientEmail: this.clientEmail,
            cottageReservationId: this.cottageReservationId,
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
      this.cottageReservationItemIndex = this.cottageReservations.indexOf(item);
      this.cottageReservationItem = Object.assign({}, item);
      this.formattedCottageReservationAddress =
        this.cottageReservationItem.cottageReservationClientInformationDTO
          .address.street +
        " " +
        this.cottageReservationItem.cottageReservationClientInformationDTO
          .address.streetNumber +
        " " +
        this.cottageReservationItem.cottageReservationClientInformationDTO
          .address.city +
        ", " +
        this.cottageReservationItem.cottageReservationClientInformationDTO
          .address.country;

      this.dialogClientInformation = true;
    },

    displayReportForm(item) {
      this.comment = "";
      this.clientArrival = "";
      this.index = this.cottageReservations.indexOf(item);
      this.reservationItem = Object.assign({}, item);
      this.cottageReservationId = this.reservationItem.cottageReservationId;
      this.clientEmail = this.reservationItem.cottageReservationClientInformationDTO.email;
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
  width: 83%;
  text-align: center;
  margin: auto;
  margin-top: 5%;
}
</style>