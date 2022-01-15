<template>
  <div>
    <h1 id="caption">Profile delete requests</h1>
    <v-card id="profileDeleteRequestsCard" justify-center>
      <div>
        <v-data-table :headers="headers" :items="requests" class="elevation-1">
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Waiting profile delete requests
              </v-toolbar-title>
              <v-spacer></v-spacer>

              <v-dialog v-model="dialogAcceptRequest" max-width="60%">
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
                    />
                    <v-spacer></v-spacer>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="green"
                      text
                      @click="acceptProfileDeleteRequest"
                    >
                      Accept</v-btn
                    >
                    <v-spacer></v-spacer>
                    <v-btn color="red" text @click="closeAcceptRequest">
                      Cancel</v-btn
                    >
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog v-model="dialogDeclineRequest" max-width="60%">
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
                    />
                    <v-spacer></v-spacer>
                  </v-card-text>

                  <v-spacer></v-spacer>

                  <v-card-actions>
                    <v-spacer></v-spacer>

                    <v-btn
                      color="green"
                      text
                      @click="declineProfileDeleteRequest"
                      >Decline</v-btn
                    >
                    <v-spacer></v-spacer>

                    <v-btn color="red" text @click="closeDeclineRequest"
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
              <v-btn color="green" text @click="acceptRequest(item)">
                ACCEPT
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn color="red" text @click="declineRequest(item)">
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
  name: "ProfileDeleteRequests",
  data: () => ({
    requests: [],
    dialogDeclineRequest: false,
    dialogAcceptRequest: false,
    requestItem: null,
    defaultItem: null,
    profileDeleteRequestId: null,
    comment: null,
    email: null,
    index: null,
    headers: [
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
        text: "E-mail",
        value: "email",
        align: "center",
      },
      {
        text: "Reason",
        value: "reason",
        align: "center",
        sortable: false,
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
          this.client = response.data;
        })
        .catch((err) => {
          alert(
            "401 Unauthorized - respected you are not logged as system administrator."
          );
          window.location.href = "http://localhost:8083/login";
          console.log(err);
        });

      this.axios
        .get("http://localhost:8091/deleteAccount/profileDeleteRequests", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          console.log(response.data);
          this.requests = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    acceptRequest(item) {
      this.index = this.requests.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogAcceptRequest = true;
      this.email = this.requestItem.email;
      this.profileDeleteRequestId = this.requestItem.profileDeleteRequestId;
    },

    declineRequest(item) {
      this.index = this.requests.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogDeclineRequest = true;
      this.email = this.requestItem.email;
      this.profileDeleteRequestId = this.requestItem.profileDeleteRequestId;
    },

    closeAcceptRequest() {
      this.dialogAcceptRequest = false;
      this.closeRequest();
    },

    closeDeclineRequest() {
      this.dialogDeclineRequest = false;
      this.closeRequest();
    },

    closeRequest() {
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
        alert("Your comment shouldn't contain special characters.");
        return false;
      }
      return true;
    },
    acceptProfileDeleteRequest() {
      if (!this.validateComment()) return;
      this.requests.splice(this.index, 1);
      this.axios
        .put(
          "http://localhost:8091/deleteAccount/acceptRequest",
          {
            profileDeleteRequestId: this.profileDeleteRequestId,
            email: this.email,
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
          alert("The profile delete request was successfully accepted.");
        });
      this.closeAcceptRequest();
    },

    declineProfileDeleteRequest() {
      if (!this.validateComment()) return;
      this.requests.splice(this.index, 1);
      this.axios
        .put(
          "http://localhost:8091/deleteAccount/declineRequest",
          {
            profileDeleteRequestId: this.profileDeleteRequestId,
            email: this.email,
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
          alert("The profile delete request was successfully declined.");
        });
      this.closeDeclineRequest();
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
#profileDeleteRequestsCard {
  width: 80%;
  text-align: center;
  margin: auto;
}
</style>