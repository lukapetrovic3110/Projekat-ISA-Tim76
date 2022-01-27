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
                >Ship reservations</v-toolbar-title
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
                    <v-btn color="blue darken-1" text @click="closeDialog">
                      Ok
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.clientInformation`]="{ item }">
            <v-card-actions
              v-if="item.shipReservationClientInformationDTO !== null"
            >
              <v-spacer></v-spacer>
              <v-btn color="blue" text @click="displayClientInformation(item)">
                VIEW
              </v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
            <v-card-actions v-else>
              <v-spacer></v-spacer>
              <v-btn disabled text> NO CLIENT </v-btn>
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
    this.getAllReservationsForShipOwner();
  },

  methods: {
    getAllReservationsForShipOwner() {
      this.shipOwnerId = localStorage.getItem("userId");
      this.axios
        .get(
          "http://localhost:8091/reservationShip/shipOwnerReservations/" +
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

    displayClientInformation(item) {
      this.shipReservationItemIndex = this.shipReservations.indexOf(item);
      this.shipReservationItem = Object.assign({}, item);
      this.formattedshipReservationAddress =
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