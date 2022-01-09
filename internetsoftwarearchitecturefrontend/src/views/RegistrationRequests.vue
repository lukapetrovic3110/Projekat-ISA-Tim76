<template>
  <div>
    <h1 id="registrationRequestsCaption">Registration requests</h1>
    <v-card id="registrationRequestsCard" justify-center>
      <div>
        <v-data-table
          :headers="headers"
          :items="registartionRequests"
          class="elevation-1"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center"
                >Waiting registration requests</v-toolbar-title
              >
              <v-spacer></v-spacer>

              <v-dialog v-model="dialogAcceptRequest" max-width="60%">
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h5 justify-center"
                    >Are you sure you want to accept this registration request?
                  </v-card-title>
                  <v-card-actions>
                    <v-spacer></v-spacer>

                    <v-btn color="green" text @click="acceptRequest"
                      >Accept</v-btn
                    >
                    <v-spacer></v-spacer>

                    <v-btn color="red" text @click="closeAcceptRequest"
                      >Cancel</v-btn
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

                    <v-btn color="green" text @click="declineRequest"
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
              <v-btn
                color="green"
                text
                @click="acceptRegistrationRequest(item)"
              >
                ACCEPT
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn color="red" text @click="declineRegistrationRequest(item)">
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
  name: "RegistrationRequests",
  data: () => ({
    registartionRequests: [],
    dialogDeclineRequest: false,
    dialogAcceptRequest: false,
    requestItem: null,
    defaultItem: null,
    comment: null,
    requestEmail: null,
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
        text: "Phone number",
        value: "phoneNumber",
        align: "center",
      },
      {
        text: "Account approval status",
        value: "accountApproval",
        align: "center",
      },
      {
        text: "Explanation",
        value: "explanation",
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
        .get("http://localhost:8091/admin/registrationRequests", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          console.log(response.data);
          this.registartionRequests = response.data;
        });
    },

    declineRegistrationRequest(item) {
      this.index = this.registartionRequests.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogDeclineRequest = true;
      this.requestEmail = this.requestItem.email;
    },

    acceptRegistrationRequest(item) {
      this.index = this.registartionRequests.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogAcceptRequest = true;
      this.requestEmail = this.requestItem.email;
    },

    acceptRequest() {
      this.registartionRequests.splice(this.index, 1);
      this.axios
        .post(
          "http://localhost:8091/admin/acceptRegistrationRequest",
          {
            requestEmail: this.requestEmail,
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          alert("The registartion request was successfully accepted!");
        });
      this.closeAcceptRequest();
    },

    declineRequest() {
      this.registartionRequests.splice(this.index, 1);
      this.axios
        .post(
          "http://localhost:8091/admin/declineRegistrationRequest",
          {
            requestEmail: this.requestEmail,
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
          alert("The registartion request was declined!");
        });
      this.closeDeclineRequest();
    },
    closeDeclineRequest() {
      this.comment = "";
      this.dialogDeclineRequest = false;
      this.$nextTick(() => {
        this.requestItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    closeAcceptRequest() {
      this.dialogAcceptRequest = false;
      this.$nextTick(() => {
        this.requestItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },
  },
};
</script>

<style scoped>
#registrationRequestsCaption {
  margin-top: 2%;
  margin-bottom: 2%;
  color: #007acc;
  text-align: center;
  font-weight: bold;
}
#registrationRequestsCard {
  width: 80%;
  text-align: center;
  margin: auto;
}
</style>