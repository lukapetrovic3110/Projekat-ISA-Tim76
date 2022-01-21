<template>
  <div>
    <h1 id="caption">Waiting complaint requests</h1>
    <v-card id="complaintRequestsCard" justify-center>
      <div>
        <v-data-table :headers="objectComplaintHeaders" :items="cottageComplaints" class="elevation-1">
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Cottage complaint requests
              </v-toolbar-title>
              <v-spacer></v-spacer>

              <v-dialog v-model="dialogAcceptCottageComplaintRequest" max-width="60%" persistent>
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h4 justify-center">
                    Enter a comment
                  </v-card-title>
                  <v-card-text>
                    <v-spacer></v-spacer>
                    <v-text-field
                      class="ml-5 mr-5"
                      label="Comment"
                      v-model="comment"
                      color="blue"
                      type="text"
                      :rules="[() => !!comment || 'This field is required']"
                      hint="Please enter a comment (maximum 100 characters)"
                    />
                    <v-spacer></v-spacer>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="green"
                      text
                      @click="acceptCottageComplaintRequest"
                    >
                      Accept</v-btn
                    >
                    <v-spacer></v-spacer>
                    <v-btn color="red" text @click="closeAcceptCottageComplaintDialog">
                      Cancel</v-btn
                    >
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog v-model="dialogDeclineCottageComplaintRequest" max-width="60%" persistent>
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h4 justify-center"
                    >Enter a comment</v-card-title
                  >
                  <v-card-text>
                    <v-spacer></v-spacer>
                    <v-text-field
                      class="ml-5 mr-5"
                      label="Comment"
                      v-model="comment"
                      color="blue"
                      type="text"
                      :rules="[() => !!comment || 'This field is required']"
                      hint="Please enter a comment (maximum 100 characters)"
                    />
                    <v-spacer></v-spacer>
                  </v-card-text>

                  <v-spacer></v-spacer>

                  <v-card-actions>
                    <v-spacer></v-spacer>

                    <v-btn
                      color="green"
                      text
                      @click="declineCottageComplaintRequest"
                      >Decline</v-btn
                    >
                    <v-spacer></v-spacer>

                    <v-btn color="red" text @click="closeDeclineCottageComplaintDialog"
                      >Cancel</v-btn
                    >
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.actions`]="{ item }">
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green" text @click="acceptCottageComplaint(item)">
                ACCEPT
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn color="red" text @click="declineCottageComplaint(item)">
                DECLINE
              </v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </template>
        </v-data-table>
      </div>
    </v-card>
    <v-card id="complaintRequestsCard" justify-center>
      <div>
        <v-data-table :headers="objectComplaintHeaders" :items="shipComplaints" class="elevation-1">
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Ship complaint requests
              </v-toolbar-title>
              <v-spacer></v-spacer>

              <v-dialog v-model="dialogAcceptShipComplaintRequest" max-width="60%">
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h4 justify-center">
                    Enter a comment
                  </v-card-title>
                  <v-card-text>
                    <v-spacer></v-spacer>
                    <v-text-field
                      class="ml-5 mr-5"
                      label="Comment"
                      v-model="comment"
                      color="blue"
                      type="text"
                      :rules="[() => !!comment || 'This field is required']"
                      hint="Please enter a comment (maximum 100 characters)"
                    />
                    <v-spacer></v-spacer>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="green"
                      text
                      @click="acceptShipComplaintRequest"
                    >
                      Accept</v-btn
                    >
                    <v-spacer></v-spacer>
                    <v-btn color="red" text @click="closeAcceptShipComplaintDialog">
                      Cancel</v-btn
                    >
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog v-model="dialogDeclineShipComplaintRequest" max-width="60%">
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h4 justify-center"
                    >Enter a comment</v-card-title
                  >
                  <v-card-text>
                    <v-spacer></v-spacer>
                    <v-text-field
                      class="ml-5 mr-5"
                      label="Comment"
                      v-model="comment"
                      color="blue"
                      type="text"
                      :rules="[() => !!comment || 'This field is required']"
                      hint="Please enter a comment (maximum 100 characters)"
                    />
                    <v-spacer></v-spacer>
                  </v-card-text>

                  <v-spacer></v-spacer>

                  <v-card-actions>
                    <v-spacer></v-spacer>

                    <v-btn
                      color="green"
                      text
                      @click="declineShipComplaintRequest"
                      >Decline</v-btn
                    >
                    <v-spacer></v-spacer>

                    <v-btn color="red" text @click="closeDeclineShipComplaintDialog"
                      >Cancel</v-btn
                    >
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.actions`]="{ item }">
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green" text @click="acceptShipComplaint(item)">
                ACCEPT
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn color="red" text @click="declineShipComplaint(item)">
                DECLINE
              </v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </template>
        </v-data-table>
      </div>
    </v-card>
    <v-card id="complaintRequestsCard" justify-center>
      <div>
        <v-data-table :headers="userComplaintHeaders" :items="userComplaints" class="elevation-1">
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                User complaint requests
              </v-toolbar-title>
              <v-spacer></v-spacer>

              <v-dialog v-model="dialogAcceptUserComplaintRequest" max-width="60%">
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h4 justify-center">
                    Enter a comment
                  </v-card-title>
                  <v-card-text>
                    <v-spacer></v-spacer>
                    <v-text-field
                      class="ml-5 mr-5"
                      label="Comment"
                      v-model="comment"
                      color="blue"
                      type="text"
                      :rules="[() => !!comment || 'This field is required']"
                      hint="Please enter a comment (maximum 100 characters)"
                    />
                    <v-spacer></v-spacer>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="green"
                      text
                      @click="acceptUserComplaintRequest"
                    >
                      Accept</v-btn
                    >
                    <v-spacer></v-spacer>
                    <v-btn color="red" text @click="closeAcceptUserComplaintDialog">
                      Cancel</v-btn
                    >
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog v-model="dialogDeclineUserComplaintRequest" max-width="60%">
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h4 justify-center"
                    >Enter a comment</v-card-title
                  >
                  <v-card-text>
                    <v-spacer></v-spacer>
                    <v-text-field
                      class="ml-5 mr-5"
                      label="Comment"
                      v-model="comment"
                      color="blue"
                      type="text"
                      :rules="[() => !!comment || 'This field is required']"
                      hint="Please enter a comment (maximum 100 characters)"
                    />
                    <v-spacer></v-spacer>
                  </v-card-text>

                  <v-spacer></v-spacer>

                  <v-card-actions>
                    <v-spacer></v-spacer>

                    <v-btn
                      color="green"
                      text
                      @click="declineUserComplaintRequest"
                      >Decline</v-btn
                    >
                    <v-spacer></v-spacer>

                    <v-btn color="red" text @click="closeDeclineUserComplaintDialog"
                      >Cancel</v-btn
                    >
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.actions`]="{ item }">
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green" text @click="acceptUserComplaint(item)">
                ACCEPT
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn color="red" text @click="declineUserComplaint(item)">
                DECLINE
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
  name: "SystemAdministratorComplaint",
  data: () => ({
    cottageComplaints: [],
    shipComplaints: [],
    userComplaints: [],
    dialogAcceptCottageComplaintRequest: false,
    dialogDeclineCottageComplaintRequest: false,
    dialogAcceptShipComplaintRequest: false,
    dialogDeclineShipComplaintRequest: false,
    dialogAcceptUserComplaintRequest: false,
    dialogDeclineUserComplaintRequest: false,
    requestItem: null,
    defaultItem: null,
    complaintRequestId: null,
    comment: null,
    clientEmail: null,
    userEmail: null,
    index: null,
    objectComplaintHeaders: [
      {
        text: "Name",
        value: "name",
        align: "center",
      },
      {
        text: "Comment",
        value: "comment",
        align: "center",
      },
      {
        text: "Client first name",
        value: "clientFirstName",
        align: "center",
      },
      {
        text: "Client last name",
        value: "clientLastName",
        align: "center",
      },
      {
        text: "Client email",
        value: "clientEmail",
        align: "center",
      },
      {
        text: "Owner first name",
        value: "ownerFirstName",
        align: "center",
      },
      {
        text: "Owner last name",
        value: "ownerLastName",
        align: "center",
      },
      {
        text: "Owner email",
        value: "ownerEmail",
        align: "center",
      },
      {
        text: "Actions",
        value: "actions",
        align: "center",
        sortable: false,
      },
    ],
    userComplaintHeaders: [
      {
        text: "Accused first name",
        value: "accusedFirstName",
        align: "center",
      },
      {
        text: "Accused last name",
        value: "accusedLastName",
        align: "center",
      },
      {
        text: "Accused email",
        value: "accusedEmail",
        align: "center",
      },
      {
        text: "Comment",
        value: "comment",
        align: "center",
      },
      {
        text: "Client first name",
        value: "clientFirstName",
        align: "center",
      },
      {
        text: "Client last name",
        value: "clientLastName",
        align: "center",
      },
      {
        text: "Client email",
        value: "clientEmail",
        align: "center",
      },
      {
        text: "Actions",
        value: "actions",
        align: "center",
        sortable: false,
      },
    ],
  }),
  mounted() {
    this.init();
  },
  methods: {
    init() {
      this.axios
        .get("http://localhost:8091/admin", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          if (response.data != null) this.viewAllComplaint();
        })
        .catch((err) => {
          alert(
            "401 Unauthorized - respected you are not logged as system administrator."
          );
          window.location.href = "http://localhost:8083/login";
          console.log(err);
        });

    },
    viewAllComplaint() {    
        this.viewCottageComplaints();
        this.viewShipComplaints();
        this.viewUserComplaints();
    },
    viewCottageComplaints() {
      this.axios
        .get("http://localhost:8091/complaint/cottage", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => this.cottageComplaints = response.data)
        .catch((err) => console.log(err));
    },
    viewShipComplaints() {
        this.axios
        .get("http://localhost:8091/complaint/ship", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => this.shipComplaints = response.data)
        .catch((err) => console.log(err));
    },
    viewUserComplaints() {  
        this.axios
        .get("http://localhost:8091/complaint/user", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => this.userComplaints = response.data)
        .catch((err) => console.log(err));
    },
    acceptCottageComplaint(item) {
      this.index = this.cottageComplaints.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogAcceptCottageComplaintRequest = true;
      this.complaintRequestId = this.requestItem.complaintId;
      this.clientEmail = this.requestItem.clientEmail;
      this.userEmail = this.requestItem.ownerEmail;
    },

    declineCottageComplaint(item) {
      this.index = this.cottageComplaints.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogDeclineCottageComplaintRequest = true;
      this.complaintRequestId = this.requestItem.complaintId;
      this.clientEmail = this.requestItem.clientEmail;
      this.userEmail = this.requestItem.ownerEmail;
    },

    closeAcceptCottageComplaintDialog() {
      this.dialogAcceptCottageComplaintRequest = false;
      this.closeDialog();
    },

    closeDeclineCottageComplaintDialog() {
      this.dialogDeclineCottageComplaintRequest = false;
      this.closeDialog();
    },
    acceptCottageComplaintRequest() {
      if (!this.validateComment()) return;
      this.cottageComplaints.splice(this.index, 1);
      this.axios
        .post(
          "http://localhost:8091/complaint/acceptCottageComplaintRequest",
          {
            complaintRequestId: this.complaintRequestId,
            clientEmail: this.clientEmail,
            userEmail: this.userEmail,
            comment: this.comment,
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          alert("The cottage complaint request was successfully accepted.");
        });
      this.closeAcceptCottageComplaintDialog();
    },

    declineCottageComplaintRequest() {
      if (!this.validateComment()) return;
      this.cottageComplaints.splice(this.index, 1);
      this.axios
        .post(
          "http://localhost:8091/complaint/declineCottageComplaintRequest",
          {
            complaintRequestId: this.complaintRequestId,
            clientEmail: this.clientEmail,
            userEmail: this.userEmail,
            comment: this.comment,
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          alert("The cottage complaint request was successfully declined.");
        });
      this.closeDeclineCottageComplaintDialog();
    },
    acceptShipComplaint(item) {
      this.index = this.shipComplaints.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogAcceptShipComplaintRequest = true;
      this.complaintRequestId = this.requestItem.complaintId;
      this.clientEmail = this.requestItem.clientEmail;
      this.userEmail = this.requestItem.ownerEmail;
    },

    declineShipComplaint(item) {
      this.index = this.shipComplaints.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogDeclineShipComplaintRequest = true;
      this.complaintRequestId = this.requestItem.complaintId;
      this.clientEmail = this.requestItem.clientEmail;
      this.userEmail = this.requestItem.ownerEmail;
    },

    closeAcceptShipComplaintDialog() {
      this.dialogAcceptShipComplaintRequest = false;
      this.closeDialog();
    },

    closeDeclineShipComplaintDialog() {
      this.dialogDeclineShipComplaintRequest = false;
      this.closeDialog();
    },
    acceptShipComplaintRequest() {
      if (!this.validateComment()) return;
      this.shipComplaints.splice(this.index, 1);
      this.axios
        .post(
          "http://localhost:8091/complaint/acceptShipComplaintRequest",
          {
            complaintRequestId: this.complaintRequestId,
            clientEmail: this.clientEmail,
            userEmail: this.userEmail,
            comment: this.comment,
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          alert("The ship complaint request was successfully accepted.");
        });
      this.closeAcceptShipComplaintDialog();
    },

    declineShipComplaintRequest() {
      if (!this.validateComment()) return;
      this.shipComplaints.splice(this.index, 1);
      this.axios
        .post(
          "http://localhost:8091/complaint/declineShipComplaintRequest",
          {
            complaintRequestId: this.complaintRequestId,
            clientEmail: this.clientEmail,
            userEmail: this.userEmail,
            comment: this.comment,
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          alert("The ship complaint request was successfully declined.");
        });
      this.closeDeclineShipComplaintDialog();
    },
    acceptUserComplaint(item) {
      this.index = this.userComplaints.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogAcceptUserComplaintRequest = true;
      this.complaintRequestId = this.requestItem.complaintId;
      this.clientEmail = this.requestItem.clientEmail;
      this.userEmail = this.requestItem.accusedEmail;
    },

    declineUserComplaint(item) {
      this.index = this.userComplaints.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogDeclineUserComplaintRequest = true;
      this.complaintRequestId = this.requestItem.complaintId;
      this.clientEmail = this.requestItem.clientEmail;
      this.userEmail = this.requestItem.accusedEmail;
    },

    closeAcceptUserComplaintDialog() {
      this.dialogAcceptUserComplaintRequest = false;
      this.closeDialog();
    },

    closeDeclineUserComplaintDialog() {
      this.dialogDeclineUserComplaintRequest = false;
      this.closeDialog();
    },
     acceptUserComplaintRequest() {
      if (!this.validateComment()) return;
      this.userComplaints.splice(this.index, 1);
      this.axios
        .post(
          "http://localhost:8091/complaint/acceptUserComplaintRequest",
          {
            complaintRequestId: this.complaintRequestId,
            clientEmail: this.clientEmail,
            userEmail: this.userEmail,
            comment: this.comment,
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          alert("The complaint request was successfully accepted.");
        });
      this.closeAcceptUserComplaintDialog();
    },

    declineUserComplaintRequest() {
      if (!this.validateComment()) return;
      this.userComplaints.splice(this.index, 1);
      this.axios
        .post(
          "http://localhost:8091/complaint/declineUserComplaintRequest",
          {
            complaintRequestId: this.complaintRequestId,
            clientEmail: this.clientEmail,
            userEmail: this.userEmail,
            comment: this.comment,
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          alert("The complaint request was successfully declined.");
        });
      this.closeDeclineUserComplaintDialog();
    },
    closeDialog() {
      this.comment = "";
      this.$nextTick(() => {
        this.requestItem = Object.assign({}, this.defaultItem);
        this.index = -1;
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
  },
};
</script>

<style scoped>
#caption {
  margin-top: 2%;
  margin-bottom: 2%;
  color: #007acc;
  text-align: center;
  font-weight: bold;
}
#complaintRequestsCard {
  width: 80%;
  text-align: center;
  margin: auto;
  margin-top: 3%;
}
</style>