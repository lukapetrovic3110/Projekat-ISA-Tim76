<template>
  <div>
    <h1 id="capption">Write a complaint</h1>
    <v-card id="complaintCard">
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
              <v-dialog v-model="dialogComplaintCottage" persistent>
                <v-card>
                  <v-card-title class="text-h5 justify-center"
                    >Complaint form</v-card-title
                  >
                  <v-card-text>
                    <v-container>
                      <v-text-field
                        v-model="comment"
                        label="Comment"
                        type="text"
                        :rules="[() => !!comment || 'This field is required']"
                        hint="Please enter a comment (maximum 50 characters)"
                      >
                      </v-text-field>
                      <v-radio-group
                        v-model="cottageComplaintGroup"
                        hide-details
                      >
                        <v-radio
                          value="cottage"
                          label="Complaint about the cottage"
                        >
                        </v-radio>
                        <v-radio
                          value="cottage-owner"
                          label="Complaint to the owner of the cottage"
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
                      @click="writeCottageComplaint"
                    >
                      Write
                    </v-btn>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="closeComplaintCottageDialog"
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
                @click="displayComplaintCottageDialog(item)"
              >
                WRITE A COMPLAINT
              </v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </template>
        </v-data-table>
      </div>
    </v-card>
    <v-card id="complaintCard">
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
              <v-dialog v-model="dialogComplaintShip" persistent>
                <v-card>
                  <v-card-title class="text-h5 justify-center"
                    >Complaint form</v-card-title
                  >
                  <v-card-text>
                    <v-container>
                      <v-text-field
                        v-model="comment"
                        label="Comment"
                        type="text"
                        :rules="[() => !!comment || 'This field is required']"
                        hint="Please enter a comment (maximum 50 characters)"
                      >
                      </v-text-field>
                      <v-radio-group v-model="shipComplaintGroup" hide-details>
                        <v-radio value="ship" label="Complaint about the ship">
                        </v-radio>
                        <v-radio
                          value="ship-owner"
                          label="Complaint to the owner of the ship"
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
                      @click="writeShipComplaint"
                    >
                      Write
                    </v-btn>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="closeComplaintShipDialog"
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
                @click="displayComplaintShipDialog(item)"
              >
                WRITE A COMPLAINT
              </v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </template>
        </v-data-table>
      </div>
    </v-card>
    <v-card id="complaintCard">
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
              <v-dialog v-model="dialogComplaintFishingInstructor" persistent>
                <v-card>
                  <v-card-title class="text-h5 justify-center"
                    >Complaint about the fishing instructor</v-card-title
                  >
                  <v-card-text>
                    <v-container>
                      <v-text-field
                        v-model="comment"
                        label="Comment"
                        type="text"
                        :rules="[() => !!comment || 'This field is required']"
                        hint="Please enter a comment (maximum 50 characters)"
                      >
                      </v-text-field>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="writeFishingInstructorComplaint"
                    >
                      Write
                    </v-btn>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="closeComplaintFishingInstructorDialog"
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
                @click="displayComplaintFishingInstructorDialog(item)"
              >
                WRITE A COMPLAINT
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
  name: "ClientComplaint",
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
    dialogComplaintCottage: false,
    dialogComplaintShip: false,
    dialogComplaintFishingInstructor: false,
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
    reservationCottageHistory: [],
    reservationShipHistory: [],
    reservationAdventureHistory: [],
    cottageComplaintGroup: "",
    shipComplaintGroup: "",
    comment: "",
    complaintItem: null,
    defaultComplaintItem: null,
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
    displayComplaintCottageDialog(item) {
      this.dialogComplaintCottage = true;
      this.complaintItem = Object.assign({}, item);
    },
    writeCottageComplaint() {
      if (!this.validateComment() || !this.validateCottageComplaintGroup())
        return;

      if(this.cottageComplaintGroup.toString() === "cottage") {
        this.axios
          .post(
            "http://localhost:8091/complaint/writeCottageComplaint",
            {
              comment: this.comment,
              complaintEntityId: this.complaintItem.cottageId,
            },
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            }
          )
          .then((response) => {
           console.log(response.data);
           alert("The complaint was successfully written and submitted!");
          })
          .catch((error) => console.log(error));
      } else {
        this.axios
          .post(
            "http://localhost:8091/complaint/writeCottageOwnerComplaint",
            {
              comment: this.comment,
              complaintEntityId: this.complaintItem.ownerId,
            },
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            }
          )
          .then((response) => {
           console.log(response.data);
           alert("The complaint was successfully written and submitted!");
          })
          .catch((error) => console.log(error));
      }
      this.closeComplaintCottageDialog();
      
    },
    closeComplaintCottageDialog() {
      this.dialogComplaintCottage = false;
      this.comment = "";
      this.cottageComplaintGroup = "";
      this.$nextTick(() => {
        this.complaintItem = Object.assign({}, this.defaultComplaintItem);
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
    displayComplaintShipDialog(item) {
      this.dialogComplaintShip = true;
      this.complaintItem = Object.assign({}, item);
    },
    writeShipComplaint() {
      if (!this.validateComment() || !this.validateShipComplaintGroup()) return;
      if(this.shipComplaintGroup.toString() === "ship") {
        this.axios
          .post(
            "http://localhost:8091/complaint/writeShipComplaint",
            {
              comment: this.comment,
              complaintEntityId: this.complaintItem.shipId,
            },
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            }
          )
          .then((response) => {
           console.log(response.data);
           alert("The complaint was successfully written and submitted!");
          })
          .catch((error) => console.log(error));
      } else {
        this.axios
          .post(
            "http://localhost:8091/complaint/writeShipOwnerComplaint",
            {
              comment: this.comment,
              complaintEntityId: this.complaintItem.ownerId,
            },
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            }
          )
          .then((response) => {
           console.log(response.data);
           alert("The complaint was successfully written and submitted!");
          })
          .catch((error) => console.log(error));
      }
      this.closeComplaintShipDialog();
    },
    closeComplaintShipDialog() {
      this.dialogComplaintShip = false;
      this.comment = "";
      this.shipComplaintGroup = "";
      this.$nextTick(() => {
        this.complaintItem = Object.assign({}, this.defaultComplaintItem);
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
    displayComplaintFishingInstructorDialog(item) {
      this.dialogComplaintFishingInstructor = true;
      this.complaintItem = Object.assign({}, item);
    },
    writeFishingInstructorComplaint() {
      if (!this.validateComment()) return;
      this.axios
          .post(
            "http://localhost:8091/complaint/writeFishingInstructorComplaint",
            {
              comment: this.comment,
              complaintEntityId: this.complaintItem.fishingInstructorId,
            },
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            }
          )
          .then((response) => {
           console.log(response.data);
           alert("The complaint was successfully written and submitted!");
          })
          .catch((error) => console.log(error));

          this.closeComplaintFishingInstructorDialog();
    },
    closeComplaintFishingInstructorDialog() {
      this.dialogComplaintFishingInstructor = false;
      this.comment = "";
      this.$nextTick(() => {
        this.complaintItem = Object.assign({}, this.defaultComplaintItem);
      });
    },
    onlySpaces(str) {
      return str.trim().length === 0;
    },
    validateComment() {
      if (this.onlySpaces(this.comment)) {
        alert("Please enter a comment!");
        return false;
      } else if (this.comment.length > 50) {
        alert("The comment is long please enter up to 50 characters!");
        return false;
      } else if (this.comment.match(/[\\#$%^&*'<>/"]/g)) {
        alert("The comment shouldn't contain special characters.");
        return false;
      }
      return true;
    },
    validateCottageComplaintGroup() {
      if (this.cottageComplaintGroup.length == 0) {
        alert("Please select a complaint type!");
        return false;
      }
      return true;
    },
    validateShipComplaintGroup() {
      if (this.shipComplaintGroup.length == 0) {
        alert("Please select a complaint type!");
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
#complaintCard {
  width: 90%;
  text-align: center;
  margin: auto;
  margin-top: 3%;
}
</style>