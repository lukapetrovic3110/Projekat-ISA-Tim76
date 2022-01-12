<template>
  <div>
    <h1 id="capption">My subscriptions</h1>
    <v-card id="subscriptionsCard">
      <div>
        <v-data-table
          :headers="fishingInstructorSubscriptionsHeaders"
          :items="fishingInstructorSubscriptions"
          items-per-page="5"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Fishing instructor subscriptions
              </v-toolbar-title>
              <v-spacer></v-spacer>
            </v-toolbar>
          </template>
        </v-data-table>
      </div>
    </v-card>
    <v-spacer></v-spacer>
    <v-card id="subscriptionsCard">
      <div>
        <v-data-table
          :headers="shipSubscriptionsHeaders"
          :items="shipSubscriptions"
          items-per-page="5"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Ship subscriptions
              </v-toolbar-title>
              <v-spacer></v-spacer>
            </v-toolbar>
          </template>
        </v-data-table>
      </div>
    </v-card>
    <v-spacer></v-spacer>
    <v-card id="subscriptionsCard">
      <div>
        <v-data-table
          :headers="cottageSubscriptionsHeaders"
          :items="cottageSubscriptions"
          items-per-page="5"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Cottage subscriptions
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
    fishingInstructorSubscriptionsHeaders: [
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
        text: "Phone number",
        value: "phoneNumber",
        align: "center",
      },
    ],
    shipSubscriptionsHeaders: [
      {
        text: "Name",
        value: "name",
        align: "center",
      },
      {
        text: "Address",
        value: "address",
        align: "center",
      },
      {
        text: "Description",
        value: "description",
        align: "center",
      },
      {
        text: "Maximum of person on ship",
        value: "capacity",
        align: "center",
      },
      {
        text: "Ship type",
        value: "shipType",
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
        text: "Owner phone number",
        value: "ownerPhoneNumber",
        align: "center",
      },
    ],
    cottageSubscriptionsHeaders: [
      {
        text: "Name",
        value: "name",
        align: "center",
      },
      {
        text: "Address",
        value: "address",
        align: "center",
      },
      {
        text: "Description",
        value: "description",
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
        text: "Owner phone number",
        value: "ownerPhoneNumber",
        align: "center",
      },
    ],
    fishingInstructorSubscriptions: [],
    shipSubscriptions: [],
    cottageSubscriptions: [],
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
          if (response.data != null) this.viewAllSubscription();
        })
        .catch((error) => {
          window.location.href = "http://localhost:8083/login";
          alert(
            "401 Unauthorized - respected you are not logged in to the system."
          );
          console.log(error);
        });
    },
    viewAllSubscription() {
      this.viewFishingInstructorSubscriptions();
      this.viewShipSubscriptions();
      this.viewCottageSubscriptions();
    },
    viewFishingInstructorSubscriptions() {
      this.axios
        .get("http://localhost:8091/client/fishingInstructorSubscriptions", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          console.log(response.data);
          this.fishingInstructorSubscriptions = response.data;
        });
    },
    viewShipSubscriptions() {
      this.axios
        .get("http://localhost:8091/client/shipSubscriptions", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          console.log(response.data);
          this.shipSubscriptions = response.data;
        });
    },
    viewCottageSubscriptions() {
      this.axios
        .get("http://localhost:8091/client/cottageSubscriptions", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          console.log(response.data);
          this.cottageSubscriptions = response.data;
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
  width: 90%;
  text-align: center;
  margin: auto;
  margin-top: 3%;
}
</style>