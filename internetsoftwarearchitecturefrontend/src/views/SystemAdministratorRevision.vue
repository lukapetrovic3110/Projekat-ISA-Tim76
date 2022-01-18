<template>
  <div>
    <h1 id="caption">Waiting revision requests</h1>
    <v-card id="revisionRequestsCard" justify-center>
      <div>
        <v-data-table
          :headers="objectRevisionHeaders"
          :items="cottageRevisions"
          class="elevation-1"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Cottage revision requests
              </v-toolbar-title>
              <v-spacer></v-spacer>

              <v-dialog
                v-model="dialogAcceptCottageRevisionRequest"
                max-width="60%"
                persistent
              >
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h4 justify-center">
                    Are you want to accept this cottage revision?
                  </v-card-title>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="green"
                      text
                      @click="acceptCottageRevisionRequest"
                    >
                      Accept</v-btn
                    >
                    <v-spacer></v-spacer>
                    <v-btn
                      color="red"
                      text
                      @click="closeAcceptCottageRevisionDialog"
                    >
                      Cancel</v-btn
                    >
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog
                v-model="dialogDeclineCottageRevisionRequest"
                max-width="60%"
                persistent
              >
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h4 justify-center"
                    >Are you want to decline this cottage
                    revision?</v-card-title
                  >
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="green"
                      text
                      @click="declineCottageRevisionRequest"
                      >Decline</v-btn
                    >
                    <v-spacer></v-spacer>
                    <v-btn
                      color="red"
                      text
                      @click="closeDeclineCottageRevisionDialog"
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
              <v-btn color="green" text @click="acceptCottageRevision(item)">
                ACCEPT
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn color="red" text @click="declineCottageRevision(item)">
                DECLINE
              </v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </template>
        </v-data-table>
      </div>
    </v-card>
    <v-card id="revisionRequestsCard" justify-center>
      <div>
        <v-data-table
          :headers="objectRevisionHeaders"
          :items="shipRevisions"
          class="elevation-1"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Ship revision requests
              </v-toolbar-title>
              <v-spacer></v-spacer>

              <v-dialog
                v-model="dialogAcceptShipRevisionRequest"
                max-width="60%"
              >
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h4 justify-center">
                    Are you want to accept this ship revision?
                  </v-card-title>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="green"
                      text
                      @click="acceptShipRevisionRequest"
                    >
                      Accept</v-btn
                    >
                    <v-spacer></v-spacer>
                    <v-btn
                      color="red"
                      text
                      @click="closeAcceptShipRevisionDialog"
                    >
                      Cancel</v-btn
                    >
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog
                v-model="dialogDeclineShipRevisionRequest"
                max-width="60%"
              >
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h4 justify-center"
                    >Are you want to decline this ship revision?</v-card-title
                  >
                  <v-card-actions>
                    <v-spacer></v-spacer>

                    <v-btn
                      color="green"
                      text
                      @click="declineShipRevisionRequest"
                      >Decline</v-btn
                    >
                    <v-spacer></v-spacer>

                    <v-btn
                      color="red"
                      text
                      @click="closeDeclineShipRevisionDialog"
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
              <v-btn color="green" text @click="acceptShipRevision(item)">
                ACCEPT
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn color="red" text @click="declineShipRevision(item)">
                DECLINE
              </v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </template>
        </v-data-table>
      </div>
    </v-card>
    <v-card id="revisionRequestsCard" justify-center>
      <div>
        <v-data-table
          :headers="adventureRevisionHeaders"
          :items="adventureRevisions"
          class="elevation-1"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Adventure revision requests
              </v-toolbar-title>
              <v-spacer></v-spacer>

              <v-dialog
                v-model="dialogAcceptAdventureRevisionRequest"
                max-width="60%"
              >
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h4 justify-center">
                    Are you want to accept this adventure revision?
                  </v-card-title>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="green"
                      text
                      @click="acceptAdventureRevisionRequest"
                    >
                      Accept</v-btn
                    >
                    <v-spacer></v-spacer>
                    <v-btn
                      color="red"
                      text
                      @click="closeAcceptAdventureRevisionDialog"
                    >
                      Cancel</v-btn
                    >
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog
                v-model="dialogDeclineAdventureRevisionRequest"
                max-width="60%"
              >
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h4 justify-center"
                    >Are you want to decline this adventure
                    revision?</v-card-title
                  >
                  <v-card-actions>
                    <v-spacer></v-spacer>

                    <v-btn
                      color="green"
                      text
                      @click="declineShipRevisionRequest"
                      >Decline</v-btn
                    >
                    <v-spacer></v-spacer>

                    <v-btn
                      color="red"
                      text
                      @click="closeDeclineAdventureRevisionDialog"
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
              <v-btn color="green" text @click="acceptAdventureRevision(item)">
                ACCEPT
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn color="red" text @click="declineAdventureRevision(item)">
                DECLINE
              </v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </template>
        </v-data-table>
      </div>
    </v-card>
    <v-card id="revisionRequestsCard" justify-center>
      <div>
        <v-data-table
          :headers="userRevisionHeaders"
          :items="userRevisions"
          class="elevation-1"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                User revision requests
              </v-toolbar-title>
              <v-spacer></v-spacer>

              <v-dialog
                v-model="dialogAcceptUserRevisionRequest"
                max-width="60%"
              >
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h4 justify-center">
                    Are you want to accept this user revision?
                  </v-card-title>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="green"
                      text
                      @click="acceptUserRevisionRequest"
                    >
                      Accept</v-btn
                    >
                    <v-spacer></v-spacer>
                    <v-btn
                      color="red"
                      text
                      @click="closeAcceptUserRevisionDialog"
                    >
                      Cancel</v-btn
                    >
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog
                v-model="dialogDeclineUserRevisionRequest"
                max-width="60%"
              >
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h4 justify-center"
                    >Are you want to decline this user revision?</v-card-title
                  >
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="green"
                      text
                      @click="declineUserRevisionRequest"
                      >Decline</v-btn
                    >
                    <v-spacer></v-spacer>
                    <v-btn
                      color="red"
                      text
                      @click="closeDeclineUserRevisionDialog"
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
              <v-btn color="green" text @click="acceptUserRevision(item)">
                ACCEPT
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn color="red" text @click="declineUserRevision(item)">
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
  name: "SystemAdministratorRevision",
  data: () => ({
    cottageRevisions: [],
    shipRevisions: [],
    adventureRevisions: [],
    userRevisions: [],
    dialogAcceptCottageRevisionRequest: false,
    dialogDeclineCottageRevisionRequest: false,
    dialogAcceptShipRevisionRequest: false,
    dialogDeclineShipRevisionRequest: false,
    dialogAcceptAdventureRevisionRequest: false,
    dialogDeclineAdventureRevisionRequest: false,
    dialogAcceptUserRevisionRequest: false,
    dialogDeclineUserRevisionRequest: false,
    requestItem: null,
    defaultItem: null,
    revisionRequestId: null,
    clientEmail: null,
    userEmail: null,
    index: null,
    userType: null,
    objectRevisionHeaders: [
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
        text: "Rating",
        value: "rating",
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
    adventureRevisionHeaders: [
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
        text: "Rating",
        value: "rating",
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
        text: "Fishing instructor first name",
        value: "ownerFirstName",
        align: "center",
      },
      {
        text: "Fishing instructor last name",
        value: "ownerLastName",
        align: "center",
      },
      {
        text: "Fishing instructor email",
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
    userRevisionHeaders: [
      {
        text: "First name",
        value: "firstName",
        align: "center",
      },
      {
        text: "Last name",
        value: "lastName",
        align: "center",
      },
      {
        text: "Email",
        value: "email",
        align: "center",
      },
      {
        text: "Comment",
        value: "comment",
        align: "center",
      },
      {
        text: "Rating",
        value: "rating",
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
          if (response.data != null) this.viewAllRevision();
        })
        .catch((err) => {
          alert(
            "401 Unauthorized - respected you are not logged as system administrator."
          );
          window.location.href = "http://localhost:8083/login";
          console.log(err);
        });
    },
    viewAllRevision() {
      this.viewCottageRevisions();
      this.viewShipRevisions();
      this.viewAdventureRevisions();
      this.viewUserRevisions();
    },
    viewCottageRevisions() {
      this.axios
        .get("http://localhost:8091/revision/cottage", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => (this.cottageRevisions = response.data))
        .catch((err) => console.log(err));
    },
    viewShipRevisions() {
      this.axios
        .get("http://localhost:8091/revision/ship", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => (this.shipRevisions = response.data))
        .catch((err) => console.log(err));
    },
    viewAdventureRevisions() {
      this.axios
        .get("http://localhost:8091/revision/adventure", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => (this.adventureRevisions = response.data))
        .catch((err) => console.log(err));
    },
    viewUserRevisions() {
      this.axios
        .get("http://localhost:8091/revision/user", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => (this.userRevisions = response.data))
        .catch((err) => console.log(err));
    },
    acceptCottageRevision(item) {
      this.index = this.cottageRevisions.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogAcceptCottageRevisionRequest = true;
      this.revisionRequestId = this.requestItem.revisionId;
      (this.clientEmail = this.requestItem.clientEmail),
        (this.userEmail = this.requestItem.ownerEmail);
    },

    declineCottageRevision(item) {
      this.index = this.cottageRevisions.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogDeclineCottageRevisionRequest = true;
      this.revisionRequestId = this.requestItem.revisionId;
    },

    closeAcceptCottageRevisionDialog() {
      this.dialogAcceptCottageRevisionRequest = false;
      this.closeDialog();
    },

    closeDeclineCottageRevisionDialog() {
      this.dialogDeclineCottageRevisionRequest = false;
      this.closeDialog();
    },
    acceptCottageRevisionRequest() {
      this.cottageRevisions.splice(this.index, 1);
      this.axios
        .put(
          "http://localhost:8091/revision/acceptRevisionRequest",
          {
            revisionRequestId: this.revisionRequestId,
            clientEmail: this.clientEmail,
            userEmail: this.userEmail,
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
          alert("The cottage revision request was successfully accepted.");
        });
      this.closeAcceptCottageRevisionDialog();
    },

    declineCottageRevisionRequest() {
      this.cottageRevisions.splice(this.index, 1);
      this.axios
        .put(
          "http://localhost:8091/revision/declineRevisionRequest",
          {
            revisionRequestId: this.revisionRequestId,
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
      this.closeDeclineCottageRevisionDialog();
    },
    acceptShipRevision(item) {
      this.index = this.shipRevisions.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogAcceptShipRevisionRequest = true;
      this.revisionRequestId = this.requestItem.revisionId;
      this.clientEmail = this.requestItem.clientEmail;
      this.userEmail = this.requestItem.ownerEmail;
    },

    declineShipRevision(item) {
      this.index = this.shipRevisions.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogDeclineShipRevisionRequest = true;
      this.revisionRequestId = this.requestItem.revisionId;
    },

    closeAcceptShipRevisionDialog() {
      this.dialogAcceptShipRevisionRequest = false;
      this.closeDialog();
    },

    closeDeclineShipRevisionDialog() {
      this.dialogDeclineShipRevisionRequest = false;
      this.closeDialog();
    },
    acceptShipRevisionRequest() {
      this.shipRevisions.splice(this.index, 1);
      this.axios
        .put(
          "http://localhost:8091/revision/acceptRevisionRequest",
          {
            revisionRequestId: this.revisionRequestId,
            clientEmail: this.clientEmail,
            userEmail: this.userEmail,
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
          alert("The ship revision request was successfully accepted.");
        });
      this.closeAcceptShipRevisionDialog();
    },

    declineShipRevisionRequest() {
      this.shipRevisions.splice(this.index, 1);
      this.axios
        .put(
          "http://localhost:8091/revision/declineRevisionRequest",
          {
            revisionRequestId: this.revisionRequestId,
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
      this.closeDeclineShipRevisionDialog();
    },
    acceptAdventureRevision(item) {
      this.index = this.adventureRevisions.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogAcceptAdventureRevisionRequest = true;
      this.revisionRequestId = this.requestItem.revisionId;
      this.clientEmail = this.requestItem.clientEmail;
      this.userEmail = this.requestItem.ownerEmail;
    },
    declineAdventureRevision(item) {
      this.index = this.adventureRevisions.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogDeclineAdventureRevisionRequest = true;
      this.revisionRequestId = this.requestItem.revisionId;
    },

    closeAcceptAdventureRevisionDialog() {
      this.dialogAcceptAdventureRevisionRequest = false;
      this.closeDialog();
    },

    closeDeclineAdventureRevisionDialog() {
      this.dialogDeclineAdventureRevisionRequest = false;
      this.closeDialog();
    },
    acceptAdventureRevisionRequest() {
      this.adventureRevisions.splice(this.index, 1);
      this.axios
        .put(
          "http://localhost:8091/revision/acceptRevisionRequest",
          {
            revisionRequestId: this.revisionRequestId,
            clientEmail: this.clientEmail,
            userEmail: this.userEmail,
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
          alert("The adventure revision request was successfully accepted.");
        });
      this.closeAcceptAdventureRevisionDialog();
    },

    declineAdventureRevisionRequest() {
      this.adventureRevisions.splice(this.index, 1);
      this.axios
        .put(
          "http://localhost:8091/revision/declineRevisionRequest",
          {
            revisionRequestId: this.revisionRequestId,
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
      this.closeDeclineAdventureRevisionDialog();
    },
    acceptUserRevision(item) {
      this.index = this.userRevisions.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogAcceptUserRevisionRequest = true;
      this.revisionRequestId = this.requestItem.revisionId;
      this.clientEmail = this.requestItem.clientEmail;
      this.userEmail = this.requestItem.email;
    },

    declineUserRevision(item) {
      this.index = this.userRevisions.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogDeclineUserRevisionRequest = true;
      this.revisionRequestId = this.requestItem.revisionId;
    },

    closeAcceptUserRevisionDialog() {
      this.dialogAcceptUserRevisionRequest = false;
      this.closeDialog();
    },

    closeDeclineUserRevisionDialog() {
      this.dialogDeclineUserRevisionRequest = false;
      this.closeDialog();
    },
    acceptUserRevisionRequest() {
      this.userRevisions.splice(this.index, 1);
      this.axios
        .get("http://localhost:8091/user/findByEmail/" + this.userEmail, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => { 
          this.userType = response.data.userType;
          this.axios
        .put(
          "http://localhost:8091/revision/acceptRevisionRequest",
          {
            revisionRequestId: this.revisionRequestId,
            clientEmail: this.clientEmail,
            userEmail: this.userEmail,
            revisionType: this.userType.toString(),
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((resp) => {
          console.log(resp.data);
          alert("The revision request was successfully accepted.");
        });
      this.closeAcceptUserRevisionDialog();
        })
        .catch((err) => console.log(err));
    },

    declineUserRevisionRequest() {
      this.userRevisions.splice(this.index, 1);
      this.axios
        .put(
          "http://localhost:8091/revision/declineRevisionRequest",
          {
            revisionRequestId: this.revisionRequestId,
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
      this.closeDeclineUserRevisionDialog();
    },
    closeDialog() {
      this.$nextTick(() => {
        this.requestItem = Object.assign({}, this.defaultItem);
        this.index = -1;
      });
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
#revisionRequestsCard {
  width: 80%;
  text-align: center;
  margin: auto;
  margin-top: 3%;
}
</style>