<template>
  <div>
    <h1 id="capption">All reservation ship history</h1>
    <v-card id="reservationHistoryCard">
      <div>
        <v-data-table
          :headers="reservationHistoryHeaders"
          :items="allReservationHistory"
          items-per-page="5"
        >
          <template v-slot:top>
            <v-toolbar dense dark color="light-blue darken-2">
              <v-spacer></v-spacer>
              <v-toolbar-title class="text-center">
                History of the ship reservations
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
  name: "ReservationShipHistoryByClient",
  data: () => ({
    reservationHistoryHeaders: [
      {
        text: "Reservation start date and time",
        value: "dateAndTime",
        align: "center",
        sortable: true,
      },
      {
        text: "Duration (hours)",
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
        text: "Name of ship",
        value: "nameOfShip",
        align: "center",
        sortable: true,
      },
      {
        text: "Maximum of person on ship",
        value: "maxNumberOfPersons",
        align: "center",
        sortable: true,
      },
    ],
    allReservationHistory: [],
  }),
  mounted() {
    this.viewAllReservationHistory();
  },
  methods: {
    viewAllReservationHistory() {
      this.axios
        .get("http://localhost:8091/reservationShip/clientHistory", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          console.log(response.data);
          this.allReservationHistory = response.data;
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
#reservationHistoryCard {
  width: 80%;
  text-align: center;
  margin: auto;
  margin-top: 5%;
}
</style>