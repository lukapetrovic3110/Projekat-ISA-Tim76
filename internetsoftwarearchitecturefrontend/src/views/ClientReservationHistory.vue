<template>
  <div>
    <h1 id="capption">My reservation history</h1>
    <v-card id="reservationHistoryCard">
      <div>
        <v-data-table
          :headers="reservationCottageHistoryHeaders"
          :items="reservationCottageHistory"
          items-per-page="5"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Cottage reservation history
              </v-toolbar-title>
              <v-spacer></v-spacer>
              <v-dialog v-model="dialogCottageOwnerInformation" max-width="75%">
                <v-card>
                  <v-card-title>
                    <span class="text-h5 justify-center"
                      >Information about cottage owner</span
                    >
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="cottageOwner.firstName"
                            label="First name"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="cottageOwner.lastName"
                            label="Last name"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="cottageOwner.email"
                            label="Email"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="cottageOwner.phoneNumber"
                            label="Phone number"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="cottageOwner.address"
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
                      @click="closeCottageOwnerInformationDialog"
                      >Ok
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.cottageAction`]="{ item }">
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                class="ma-2"
                outlined
                color="blue"
                text
                @click="displayCottageOwnerInformation(item)"
              >
                VIEW COTTAGE OWNER INFORMATION
              </v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </template>
        </v-data-table>
      </div>
    </v-card>
    <v-card id="reservationHistoryCard">
      <div>
        <v-data-table
          :headers="reservationShipHistoryHeaders"
          :items="reservationShipHistory"
          items-per-page="5"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Ship reservation history
              </v-toolbar-title>
              <v-spacer></v-spacer>
              <v-dialog v-model="dialogShipOwnerInformation" max-width="75%">
                <v-card>
                  <v-card-title>
                    <span class="text-h5 justify-center"
                      >Information about ship owner</span
                    >
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="shipOwner.firstName"
                            label="First name"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="shipOwner.lastName"
                            label="Last name"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="shipOwner.email"
                            label="Email"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="shipOwner.phoneNumber"
                            label="Phone number"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="shipOwner.address"
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
                      @click="closeShipOwnerInformationDialog"
                      >Ok
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.shipAction`]="{ item }">
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                class="ma-2"
                outlined
                color="blue"
                text
                @click="displayShipOwnerInformation(item)"
              >
                VIEW SHIP OWNER INFORMATION
              </v-btn>
              <v-spacer></v-spacer>
              <v-spacer></v-spacer>
            </v-card-actions>
          </template>
        </v-data-table>
      </div>
    </v-card>
    <v-card id="reservationHistoryCard">
      <div>
        <v-data-table
          :headers="reservationAdventureHistoryHeaders"
          :items="reservationAdventureHistory"
          items-per-page="5"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Adventure reservation history
              </v-toolbar-title>
              <v-spacer></v-spacer>
              <v-dialog
                v-model="dialogFishingInstructorInformation"
                max-width="75%"
              >
                <v-card>
                  <v-card-title>
                    <span class="text-h5 justify-center"
                      >Information about fishing instructor</span
                    >
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="fishingInstructor.firstName"
                            label="First name"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="fishingInstructor.lastName"
                            label="Last name"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="fishingInstructor.email"
                            label="Email"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="fishingInstructor.phoneNumber"
                            label="Phone number"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="fishingInstructor.biography"
                            label="Address"
                            readonly
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="fishingInstructor.address"
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
                      @click="closeFishingInstructorInformationDialog"
                      >Ok
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.adventureAction`]="{ item }">
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                class="ma-2"
                outlined
                color="blue"
                text
                @click="displayFishingInstructorInformation(item)"
              >
                VIEW FISHING INSTRUCTOR INFORMATION
              </v-btn>
              <v-spacer></v-spacer>
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
  name: "ClientReservationHistory",
  data: () => ({
    reservationCottageHistoryHeaders: [
      {
        text: "Reservation start date and time",
        value: "dateAndTime",
        align: "center",
      },
      {
        text: "Duration (days)",
        value: "duration",
        align: "center",
      },
      {
        text: "Price (RSD)",
        value: "price",
        align: "center",
      },
      {
        text: "Number of persons",
        value: "numberOfPersons",
        align: "center",
      },
      {
        text: "Name of cottage",
        value: "nameOfCottage",
        align: "center",
      },
      {
        text: "Number of rooms",
        value: "numberOfRooms",
        align: "center",
      },
      {
        text: "Number of beds per room",
        value: "numberOfBedsPerRoom",
        align: "center",
      },
      {
        text: "Cottage address",
        value: "cottageAddress",
        align: "center",
      },
      {
        text: "Action",
        value: "cottageAction",
        align: "center",
        sortable: false,
      },
    ],
    reservationShipHistoryHeaders: [
      {
        text: "Reservation start date and time",
        value: "dateAndTime",
        align: "center",
      },
      {
        text: "Duration (hours)",
        value: "duration",
        align: "center",
      },
      {
        text: "Price (RSD)",
        value: "price",
        align: "center",
      },
      {
        text: "Number of persons",
        value: "numberOfPersons",
        align: "center",
      },
      {
        text: "Name of ship",
        value: "nameOfShip",
        align: "center",
      },
      {
        text: "Maximum of person on ship",
        value: "maxNumberOfPersons",
        align: "center",
      },
      {
        text: "Ship address",
        value: "shipAddress",
        align: "center",
      },
      {
        text: "Action",
        value: "shipAction",
        align: "center",
        sortable: false,
      },
    ],
    reservationAdventureHistoryHeaders: [
      {
        text: "Reservation start date and time",
        value: "dateAndTime",
        align: "center",
      },
      {
        text: "Duration (hours)",
        value: "duration",
        align: "center",
      },
      {
        text: "Price (RSD)",
        value: "price",
        align: "center",
      },
      {
        text: "Number of persons",
        value: "numberOfPersons",
        align: "center",
      },
      {
        text: "Name of adventure",
        value: "nameOfAdventure",
        align: "center",
      },
      {
        text: "Adventure description",
        value: "description",
        align: "center",
      },
      {
        text: "Maximum of person on the adventure",
        value: "maxNumberOfPersons",
        align: "center",
      },
      {
        text: "Adventure address",
        value: "adventureAddress",
        align: "center",
      },
      {
        text: "Action",
        value: "adventureAction",
        align: "center",
        sortable: false,
      },
    ],
    reservationCottageHistory: [],
    dialogCottageOwnerInformation: false,
    dialogShipOwnerInformation: false,
    dialogFishingInstructorInformation: false,
    cottageOwner: {
      id: null,
      firstName: "",
      lastName: "",
      email: "",
      phoneNumber: "",
      address: "",
    },
    shipOwner: {
      id: null,
      firstName: "",
      lastName: "",
      email: "",
      phoneNumber: "",
      address: "",
    },
    fishingInstructor: {
      id: null,
      firstName: "",
      lastName: "",
      email: "",
      phoneNumber: "",
      biography: "",
      address: "",
    },
    reservationShipHistory: [],
    reservationAdventureHistory: [],
  }),
  mounted() {
    this.checkAuthentication();
  },
  methods: {
    checkAuthentication() {
      this.axios
        .get("http://localhost:8091/client", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          if (response.data != null) this.viewAllReservationHistory();
        })
        .catch((error) => {
          window.location.href = "http://localhost:8083/login";
          alert(
            "401 Unauthorized - respected you are not logged in to the system."
          );
          console.log(error);
        });
    },
    viewAllReservationHistory() {
      this.viewCottageReservationHistory();
      this.viewShipReservationHistory();
      this.viewAdventureReservationHistory();
    },
    viewCottageReservationHistory() {
      this.axios
        .get("http://localhost:8091/reservationCottage/clientHistory", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          this.reservationCottageHistory = response.data;
          console.log(response.data);
        })
        .catch((error) => console.log(error));
    },
    viewShipReservationHistory() {
      this.axios
        .get("http://localhost:8091/reservationShip/clientHistory", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => (this.reservationShipHistory = response.data))
        .catch((error) => console.log(error));
    },
    viewAdventureReservationHistory() {
      this.axios
        .get("http://localhost:8091/reservationAdventure/clientHistory", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => (this.reservationAdventureHistory = response.data))
        .catch((error) => console.log(error));
    },
    displayCottageOwnerInformation(item) {
      this.dialogCottageOwnerInformation = true;
      this.cottageOwner.id = item.ownerId;
      this.cottageOwner.firstName = item.ownerFirstName;
      this.cottageOwner.lastName = item.ownerLastName;
      this.cottageOwner.email = item.ownerEmail;
      this.cottageOwner.phoneNumber = item.ownerPhoneNumber;
      this.cottageOwner.address = item.ownerAddress;
      console.log(this.cottageOwner);
    },
    closeCottageOwnerInformationDialog() {
      this.dialogCottageOwnerInformation = false;
    },
    displayShipOwnerInformation(item) {
      this.dialogShipOwnerInformation = true;
      this.shipOwner.id = item.ownerId;
      this.shipOwner.firstName = item.ownerFirstName;
      this.shipOwner.lastName = item.ownerLastName;
      this.shipOwner.email = item.ownerEmail;
      this.shipOwner.phoneNumber = item.ownerPhoneNumber;
      this.shipOwner.address = item.ownerAddress;
      console.log(this.shipOwner);
    },
    closeShipOwnerInformationDialog() {
      this.dialogShipOwnerInformation = false;
    },
    displayFishingInstructorInformation(item) {
      this.dialogFishingInstructorInformation = true;
      this.fishingInstructor.id = item.fishingInstructorId;
      this.fishingInstructor.firstName = item.fishingInstructorFirstName;
      this.fishingInstructor.lastName = item.fishingInstructorLastName;
      this.fishingInstructor.email = item.fishingInstructorEmail;
      this.fishingInstructor.phoneNumber = item.fishingInstructorPhoneNumber;
      this.fishingInstructor.biography = item.fishingInstructorBiography;
      this.fishingInstructor.address = item.fishingInstructorAddress;
      console.log(this.fishingInstructor);
    },
    closeFishingInstructorInformationDialog() {
      this.dialogFishingInstructorInformation = false;
    },
  },
};
</script>

<style scoped>
#capption {
  margin-top: 2%;
  margin-bottom: 2%;
  color: #007acc;
  text-align: center;
  font-weight: bold;
}
#reservationHistoryCard {
  width: 80%;
  text-align: center;
  margin: auto;
  margin-top: 3%;
}
</style>