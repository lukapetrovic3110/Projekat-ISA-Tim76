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
                >Cottage reservations</v-toolbar-title
              >
              <v-spacer></v-spacer>

              <v-dialog v-model="dialogClientInformation" max-width="75%">
                <v-card>
                  <v-card-title>
                    <span class="text-h5 justify-center">Information about client</span>
                  </v-card-title>

                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="cottageReservationItem.cottageReservationClientInformationDTO.firstName"
                            label="First name"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="cottageReservationItem.cottageReservationClientInformationDTO.lastName"
                            label="Last name"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="cottageReservationItem.cottageReservationClientInformationDTO.email"
                            label="Email"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="cottageReservationItem.cottageReservationClientInformationDTO.phoneNumber"
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
                    <v-btn color="blue darken-1" text @click="closeDialog">
                      Ok
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
    ],

    cottageReservationItemIndex: -1,
    cottageReservationItem: {
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
        }
      },
      reservationStatus: "",
    },
    formattedCottageReservationAddress: "",

  }),

  mounted() {
    this.getAllReservationsForCottageOwner();
  },

  methods: {
    getAllReservationsForCottageOwner() {
      this.cottageOwnerId = localStorage.getItem("userId");
      this.axios
        .get(
          "http://localhost:8091/reservationCottage/cottageOwnerReservations/" +
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
            ).toDateString();
          });
        })
        .catch((err) => {
          window.location.href = "http://localhost:8083/";
          alert("401 Unauthorized");
          console.log(err);
        });
    },

    displayClientInformation(item) {
      this.cottageReservationItemIndex = this.cottageReservations.indexOf(item);
      this.cottageReservationItem = Object.assign({}, item);
      this.formattedCottageReservationAddress = this.cottageReservationItem.cottageReservationClientInformationDTO.address.street
                                              + " "
                                              + this.cottageReservationItem.cottageReservationClientInformationDTO.address.streetNumber
                                              + " "
                                              + this.cottageReservationItem.cottageReservationClientInformationDTO.address.city
                                              + ", "
                                              + this.cottageReservationItem.cottageReservationClientInformationDTO.address.country;

      this.dialogClientInformation = true;
    },

    closeDialog() {
      this.dialogClientInformation = false;
    },
  },
};
</script>

<style scoped>
#tableView {
  width: 95%;
  text-align: center;
  margin: auto;
  margin-top: 5%;
}
</style>