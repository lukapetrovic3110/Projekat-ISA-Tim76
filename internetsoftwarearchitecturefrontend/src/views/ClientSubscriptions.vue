<template>
  <div>
    <h1 id="capption">My subscriptions</h1>
    <v-card id="subscriptionsCard">
      <div>
        <v-data-table
          :headers="fishingInstructorSubscriptionsHeaders"
          :items="fishingInstructorSubscriptions"
          :items-per-page="5"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Fishing instructor subscriptions
              </v-toolbar-title>
              <v-spacer></v-spacer>
              <v-dialog v-model="dialogUnsubscribeFishingInstructor" max-width="60%">
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h5 justify-center"
                    >Are you want to unsubscribe this fishing instructor?
                  </v-card-title>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="green" text @click="unsubscribeFishingInstructor">Yes</v-btn>
                    <v-spacer></v-spacer>
                    <v-btn color="red" text @click="cancelUnsubscribeFishingInstructor">No</v-btn>
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.actions`]="{ item }">
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="red" text @click="unsubscribeFI(item)">
                UNSUBSCRIBE
              </v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
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
          :items-per-page="5"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Ship subscriptions
              </v-toolbar-title>
              <v-spacer></v-spacer>
              <v-dialog v-model="dialogUnsubscribeShip" max-width="60%">
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h5 justify-center"
                    >Are you want to unsubscribe this ship?
                  </v-card-title>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="green" text @click="unsubscribeShip">Yes</v-btn>
                    <v-spacer></v-spacer>
                    <v-btn color="red" text @click="cancelUnsubscribeShip">No</v-btn>
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.actions`]="{ item }">
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="red" text @click="unsubscribeS(item)">
                UNSUBSCRIBE
              </v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
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
          :items-per-page="5"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                Cottage subscriptions
              </v-toolbar-title>
              <v-spacer></v-spacer>
              <v-dialog v-model="dialogUnsubscribeCottage" max-width="60%">
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h5 justify-center"
                    >Are you want to unsubscribe this cottage?
                  </v-card-title>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="green" text @click="unsubscribeCottage">Yes</v-btn>
                    <v-spacer></v-spacer>
                    <v-btn color="red" text @click="cancelUnsubscribeCottage">No</v-btn>
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.actions`]="{ item }">
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="red" text @click="unsubscribeC(item)">
                UNSUBSCRIBE
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
      {
        text: "Action",
        value: "actions",
        align: "center",
        sortable: false,
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
      {
        text: "Action",
        value: "actions",
        align: "center",
        sortable: false,
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
      {
        text: "Action",
        value: "actions",
        align: "center",
        sortable: false,
      },
    ],
    dialogUnsubscribeFishingInstructor: false,
    dialogUnsubscribeShip: false,
    dialogUnsubscribeCottage: false,
    fishingInstructorSubscriptions: [],
    shipSubscriptions: [],
    cottageSubscriptions: [],
    index: null,
    requestItem: null,
    defaultItem: null,
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
    unsubscribeFI(item) {
      this.index = this.fishingInstructorSubscriptions.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogUnsubscribeFishingInstructor = true;
    },
    unsubscribeFishingInstructor() {
      this.axios.put("http://localhost:8091/client/unsubscribeFishingInstructor", 
      {
        id: this.requestItem.id,
      },
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      })
      .then((response) => {
        if(response.data == true) {
          this.fishingInstructorSubscriptions.splice(this.index, 1);
          alert("You have successfully unsubscribed from this fishing instructor!")
        }
      });
      this.cancelUnsubscribeFishingInstructor();
    },
    cancelUnsubscribeFishingInstructor() {
      this.dialogUnsubscribeFishingInstructor = false;
      this.$nextTick(() => {
        this.requestItem = Object.assign({}, this.defaultItem);
      });
    },
    unsubscribeS(item) {
      this.index = this.shipSubscriptions.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogUnsubscribeShip = true;
    },
    unsubscribeShip() {
      this.axios.put("http://localhost:8091/client/unsubscribeShip", 
      {
        id: this.requestItem.id,
      },
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      })
      .then((response) => {
        if(response.data == true) {
          this.shipSubscriptions.splice(this.index, 1);
          alert("You have successfully unsubscribed from this ship!")
        }
      });
      this.cancelUnsubscribeShip();
    },
    cancelUnsubscribeShip() {
      this.dialogUnsubscribeShip = false;
      this.$nextTick(() => {
        this.requestItem = Object.assign({}, this.defaultItem);
      });
    },
    unsubscribeC(item) {
      this.index = this.cottageSubscriptions.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogUnsubscribeCottage = true;
    },
    unsubscribeCottage() {
      this.axios.put("http://localhost:8091/client/unsubscribeCottage", 
      {
        id: this.requestItem.id,
      },
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      })
      .then((response) => {
        if(response.data == true) {
          this.cottageSubscriptions.splice(this.index, 1);
          alert("You have successfully unsubscribed from this cottage!")
        }
      });
      this.cancelUnsubscribeCottage();
    },
    cancelUnsubscribeCottage() {
      this.dialogUnsubscribeCottage = false;
      this.$nextTick(() => {
        this.requestItem = Object.assign({}, this.defaultItem);
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