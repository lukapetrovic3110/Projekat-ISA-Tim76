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
            </v-toolbar>
          </template>
          <template v-slot:[`item.actions`]="{ item }">
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                    color="green"
                    text
                    @click="acceptRequest(item)"
                    >
                    ACCEPT
                </v-btn>
                <v-spacer></v-spacer>
                <v-btn
                    color="red"
                    text
                    @click="declineRequest(item)"
                    >
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
    comment: null,
    userEmail: null,
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
            window.location.href = "http://localhost:8083/login";
            alert("401 Unauthorized - respected you are not logged in to the system.");
            console.log(err);
        });
      },
  }
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