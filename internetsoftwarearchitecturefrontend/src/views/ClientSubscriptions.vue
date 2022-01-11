<template>
  <div>
    <h1 id="capption">My subscriptions</h1>
    <v-card id="subscriptionsCard">
      <div>
        <v-data-table
          :headers="reservationHistoryHeaders"
          :items="subscriptions"
          items-per-page="5"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Subscriptions
              </v-toolbar-title>
              <v-spacer></v-spacer>
            </v-toolbar>
          </template>
        </v-data-table>
      </div>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "ClientSubscriptions",
  data: () => ({
    reservationHistoryHeaders: [
      {
        text: "First name",
        value: "firstName",
        align: "center",
        sortable: true,
      },
      {
        text: "Last name",
        value: "lastName",
        align: "center",
        sortable: true,
      },
      {
        text: "Email",
        value: "email",
        align: "center",
        sortable: true,
      },
      {
        text: "Phone number",
        value: "phoneNumber",
        align: "center",
        sortable: true,
      },
    ],
    subscriptions: [],
  }),
  mounted() {
    this.viewAllSubscription();
  },
  methods: {
    viewAllSubscription() {
      this.axios
        .get("http://localhost:8091/client/subscriptions", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          console.log(response.data);
          this.subscriptions = response.data;
        })
        .catch((err) => { 
            window.location.href = "http://localhost:8083/login";
            alert("401 Unauthorized - respected you are not logged in to the system.");
            console.log(err);
            
        });
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
#subscriptionsCard {
  width: 80%;
  text-align: center;
  margin: auto;
  margin-top: 5%;
}
</style>