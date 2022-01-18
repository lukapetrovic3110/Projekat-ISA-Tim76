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
              <v-dialog v-model="dialogRevisionCottage" persistent>
                <v-card>
                  <v-card-title class="text-h5 justify-center"
                    >Revision form</v-card-title
                  >
                  <v-card-text>
                    <v-container>
                      <v-text-field
                        v-model="comment"
                        label="Comment"
                        type="text"
                        :rules="[() => !!comment || 'This field is required']"
                        hint="Please enter a comment (maximum 100 characters)"
                      >
                      </v-text-field>
                      <v-text-field
                        v-model="rating"
                        label="Rating"
                        type="number"
                        min="1" 
                        value="0" 
                        step="0.1"
                        max="5"
                        :rules="[() => !!rating || 'This field is required']"
                        hint="Please enter a rating in the range of 1 to 5"
                      >
                      </v-text-field>
                      <v-radio-group
                        v-model="cottageRevisionGroup"
                        hide-details
                      >
                        <v-radio
                          value="COTTAGE"
                          label="Revision about the cottage"
                        >
                        </v-radio>
                        <v-radio
                          value="COTTAGE_OWNER"
                          label="Revision to the owner of the cottage"
                        >
                        </v-radio>
                      </v-radio-group>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="writeCottageRevision"
                    >
                      Write
                    </v-btn>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="closeRevisionCottageDialog"
                    >
                      Cancel
                    </v-btn>
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.cottageActions`]="{ item }">
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
              <v-btn
                class="ma-2"
                outlined
                color="red"
                text
                @click="displayRevisionCottageDialog(item)"
              >
                WRITE A REVISION
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
              <v-dialog v-model="dialogRevisionShip" persistent>
                <v-card>
                  <v-card-title class="text-h5 justify-center"
                    >Revision form</v-card-title
                  >
                  <v-card-text>
                    <v-container>
                      <v-text-field
                        v-model="comment"
                        label="Comment"
                        type="text"
                        :rules="[() => !!comment || 'This field is required']"
                        hint="Please enter a comment (maximum 100 characters)"
                      >
                      </v-text-field>
                      <v-text-field
                        v-model="rating"
                        label="Rating"
                        type="number"
                        min="1" 
                        value="0" 
                        step="0.1"
                        max="5"
                        :rules="[() => !!rating || 'This field is required']"
                        hint="Please enter a rating in the range of 1 to 5"
                      >
                      </v-text-field>
                      <v-radio-group v-model="shipRevisionGroup" hide-details>
                        <v-radio value="SHIP" label="Revision about the ship">
                        </v-radio>
                        <v-radio value="SHIP_OWNER" label="Revision to the owner of the ship">
                        </v-radio>
                      </v-radio-group>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="writeShipRevision"
                    >
                      Write
                    </v-btn>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="closeRevisionShipDialog"
                    >
                      Cancel
                    </v-btn>
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.shipActions`]="{ item }">
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
              <v-btn
                class="ma-2"
                outlined
                color="red"
                text
                @click="displayRevisionShipDialog(item)"
              >
                WRITE A REVISION
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
              <v-dialog v-model="dialogRevisionFishingInstructor" persistent>
                <v-card>
                  <v-card-title class="text-h5 justify-center"
                    >Revision form</v-card-title
                  >
                  <v-card-text>
                    <v-container>
                      <v-text-field
                        v-model="comment"
                        label="Comment"
                        type="text"
                        :rules="[() => !!comment || 'This field is required']"
                        hint="Please enter a comment (maximum 100 characters)"
                      >
                      </v-text-field>
                      <v-text-field
                        v-model="rating"
                        label="Rating"
                        type="number"
                        min="1" 
                        value="0" 
                        step="0.1"
                        max="5"
                        :rules="[() => !!rating || 'This field is required']"
                        hint="Please enter a rating in the range of 1 to 5"
                      >
                      </v-text-field>
                      <v-radio-group v-model="adventureRevisionGroup" hide-details>
                        <v-radio value="ADVENTURE" label="Revision about the adventure">
                        </v-radio>
                        <v-radio value="FISHING_INSTRUCTOR" label="Revision to the fishing instructor">
                        </v-radio>
                      </v-radio-group>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="writeFishingInstructorRevision"
                    >
                      Write
                    </v-btn>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="closeRevisionFishingInstructorDialog"
                    >
                      Cancel
                    </v-btn>
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.adventureActions`]="{ item }">
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
              <v-btn
                class="ma-2"
                outlined
                color="red"
                text
                @click="displayRevisionFishingInstructorDialog(item)"
              >
                WRITE A REVISION
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
        text: "Actions",
        value: "cottageActions",
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
        text: "Actions",
        value: "shipActions",
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
        text: "Actions",
        value: "adventureActions",
        align: "center",
        sortable: false,
      },
    ],
    dialogCottageOwnerInformation: false,
    dialogShipOwnerInformation: false,
    dialogFishingInstructorInformation: false,
    dialogRevisionCottage: false,
    dialogRevisionShip: false,
    dialogRevisionFishingInstructor: false,
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
    reservationCottageHistory: [],
    reservationShipHistory: [],
    reservationAdventureHistory: [],
    cottageRevisionGroup: "",
    shipRevisionGroup: "",
    adventureRevisionGroup: "",
    comment: "",
    rating: null,
    revisionItem: null,
    defaultRevisionItem: null,
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
    displayRevisionCottageDialog(item) {
      this.dialogRevisionCottage = true;
      this.revisionItem = Object.assign({}, item);
    },
    writeCottageRevision() {
      if (!this.validateComment() || !this.validateRating() || !this.validateCottageRevisionGroup()) 
        return;
      if(this.cottageRevisionGroup.toString() === "COTTAGE") {
        this.axios
          .post(
            "http://localhost:8091/revision/writeRevision",
            {
              revisionEntityId: this.revisionItem.cottageId,
              rating: this.rating,
              comment: this.comment,
              revisionType: "COTTAGE",
            },
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            }
          )
          .then((response) => {
           console.log(response.data);
           alert("The revision was successfully written and submitted!");
          })
          .catch((error) => console.log(error));
      } else {
        this.axios
          .post(
            "http://localhost:8091/revision/writeRevision",
            {
              revisionEntityId: this.revisionItem.ownerId,
              rating: this.rating,
              comment: this.comment,
              revisionType: "COTTAGE_OWNER",
            },
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            }
          )
          .then((response) => {
           console.log(response.data);
           alert("The revision was successfully written and submitted!");
          })
          .catch((error) => console.log(error));
      }
      this.closeRevisionCottageDialog();
      
    },
    closeRevisionCottageDialog() {
      this.dialogRevisionCottage = false;
      this.comment = "";
      this.rating = "";
      this.cottageRevisionGroup = "";
      this.$nextTick(() => {
        this.revisionItem = Object.assign({}, this.defaultRevisionItem);
      });
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
    displayRevisionShipDialog(item) {
      this.dialogRevisionShip = true;
      this.revisionItem = Object.assign({}, item);
    },
    writeShipRevision() {
      if (!this.validateComment() || !this.validateRating() || !this.validateShipRevisionGroup()) 
        return;
      if(this.shipRevisionGroup.toString() === "SHIP") {
        this.axios
          .post(
            "http://localhost:8091/revision/writeRevision",
            {
              revisionEntityId: this.revisionItem.shipId,
              rating: this.rating,
              comment: this.comment,
              revisionType: "SHIP",
            },
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            }
          )
          .then((response) => {
           console.log(response.data);
           alert("The revision was successfully written and submitted!");
          })
          .catch((error) => console.log(error));
      } else {
        this.axios
          .post(
            "http://localhost:8091/revision/writeRevision",
            {
              revisionEntityId: this.revisionItem.ownerId,
              rating: this.rating,
              comment: this.comment,
              revisionType: "SHIP_OWNER",
            },
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            }
          )
          .then((response) => {
           console.log(response.data);
           alert("The revision was successfully written and submitted!");
          })
          .catch((error) => console.log(error));
      }
      this.closeRevisionShipDialog();
    },
    closeRevisionShipDialog() {
      this.dialogRevisionShip = false;
      this.comment = "";
      this.rating = "";
      this.shipRevisionGroup = "";
      this.$nextTick(() => {
        this.revisionItem = Object.assign({}, this.defaultRevisionItem);
      });
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
    displayRevisionFishingInstructorDialog(item) {
      this.dialogRevisionFishingInstructor = true;
      this.revisionItem = Object.assign({}, item);
    },
    writeFishingInstructorRevision() {
      if (!this.validateComment() || !this.validateRating() || !this.validateAdventureRevisionGroup()) 
        return;
      if(this.adventureRevisionGroup.toString() === "ADVENTURE") {
        this.axios
          .post(
            "http://localhost:8091/revision/writeRevision",
            {
              revisionEntityId: this.revisionItem.adventureId,
              rating: this.rating,
              comment: this.comment,
              revisionType: "ADVENTURE",
            },
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            }
          )
          .then((response) => {
           console.log(response.data);
           alert("The revision was successfully written and submitted!");
          })
          .catch((error) => console.log(error));
      } else {
        this.axios
          .post(
            "http://localhost:8091/revision/writeRevision",
            {
              revisionEntityId: this.revisionItem.fishingInstructorId,
              rating: this.rating,
              comment: this.comment,
              revisionType: "FISHING_INSTRUCTOR",
            },
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            }
          )
          .then((response) => {
           console.log(response.data);
           alert("The revision was successfully written and submitted!");
          })
          .catch((error) => console.log(error));
      }
      this.closeRevisionFishingInstructorDialog();
    },
    closeRevisionFishingInstructorDialog() {
      this.dialogRevisionFishingInstructor = false;
      this.comment = "";
      this.rating = "";
      this.$nextTick(() => {
        this.revisionItem = Object.assign({}, this.defaultRevisionItem);
      });
    },
    onlySpaces(str) {
      return str.trim().length === 0;
    },
    validateComment() {
      if (this.onlySpaces(this.comment)) {
        alert("Please enter a comment!");
        return false;
      } else if (this.comment.length > 100) {
        alert("The comment is long please enter up to 100 characters!");
        return false;
      } else if (this.comment.match(/[\\#$%^&*'<>/"]/g)) {
        alert("The comment shouldn't contain special characters.");
        return false;
      }
      return true;
    },
    validateRating() {
      if(this.rating === null) {
        alert("Please a rating!");
        return false;
      }
      else if (!(this.rating >= 1 && this.rating <= 5)) {
        alert("The rating must be in the range of 1 to 5!");
        return false;
      }
      return true;
    },
    validateCottageRevisionGroup() {
      if (this.cottageRevisionGroup.length == 0) {
        alert("Please select a revision type!");
        return false;
      }
      return true;
    },
    validateShipRevisionGroup() {
      if (this.shipRevisionGroup.length == 0) {
        alert("Please select a revision type!");
        return false;
      }
      return true;
    },
     validateAdventureRevisionGroup() {
      if (this.adventureRevisionGroup.length == 0) {
        alert("Please select a revision type!");
        return false;
      }
      return true;
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
  width: 90%;
  text-align: center;
  margin: auto;
  margin-top: 3%;
}
</style>