<template>
  <div>
    <h1 id="capption">Scheduled reservations</h1>
    <v-card id="reservationHistoryCard">
      <div>
        <v-data-table
          :headers="reservationHeaders"
          :items="scheduledReservations"
          :items-per-page="10"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                My scheduled reservations
              </v-toolbar-title>
              <v-spacer></v-spacer>
              <v-dialog v-model="dialogCancelReservation" max-width="60%">
                <v-card>
                  <v-spacer></v-spacer>
                  <v-card-title class="text-h5 justify-center"
                    >Are you want to cancel this reservation?
                  </v-card-title>
                  <v-card-actions>
                    <v-spacer></v-spacer>

                    <v-btn color="green" text @click="deleteReservation">Yes</v-btn>
                    <v-spacer></v-spacer>

                    <v-btn color="red" text @click="cancel"
                      >No</v-btn
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
              <v-btn color="red" text @click="cancelReservation(item)">
                CANCEL
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
  name: "ClientScheduledReservation",
  data: () => ({
    dialogCancelReservation: false,
    reservationHeaders: [
      {
        text: "Reservation start date and time",
        value: "dateAndTime",
        align: "center",
        sortable: true,
      },
      {
        text: "Duration",
        value: "duration",
        align: "center",
        sortable: true,
      },
      {
        text: "Price (RSD)",
        value: "price",
        align: "center",
        sortable: true,
      },
      {
        text: "Number of persons",
        value: "numberOfPersons",
        align: "center",
        sortable: true,
      },
      {
        text: "Name of reservation",
        value: "nameOfReservation",
        align: "center",
        sortable: true,
      },
      {
        text: "Action",
        value: "actions",
        align: "center",
        sortable: false,
      },
    ],
    scheduledReservations: [],
    requestItem: null,
    index: null,
    reservationType: null,
    reservationId: null,
    defaultItem: null,
    editedIndex: null, 
  }),
  mounted() {
    this.viewScheduledReservations();
  },
  methods: {
    viewScheduledReservations() {
      this.axios
        .get("http://localhost:8091/client/scheduledReservation", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          console.log(response.data);
          this.scheduledReservations = response.data;
        })
        .catch((err) => {
          window.location.href = "http://localhost:8083/login";
          alert(
            "401 Unauthorized - respected you are not logged in to the system."
          );
          console.log(err);
        });
    },
    cancelReservation(item) {
      this.index = this.scheduledReservations.indexOf(item);
      this.requestItem = Object.assign({}, item);
      this.dialogCancelReservation = true;
      this.reservationId = this.requestItem.reservationId;
      this.reservationType = this.requestItem.reservationType;
    },
    deleteReservation() {
      
      this.axios
        .put(
          "http://localhost:8091/client/cancelReservation",
          {
            reservationId: this.reservationId,
            reservationType: this.reservationType,
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          if (response.data === true) {
              this.scheduledReservations.splice(this.index, 1);
              alert("The reservation was successfully canceled!");
          } else {
               alert("The reservation cannot be deleted, as there are less than 3 days left until the beginning!");
          }
          
        });
      this.cancel();
    },
    cancel() {
      this.dialogCancelReservation = false;
      this.$nextTick(() => {
        this.requestItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
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
#reservationHistoryCard {
  width: 80%;
  text-align: center;
  margin: auto;
  margin-top: 5%;
}
</style>