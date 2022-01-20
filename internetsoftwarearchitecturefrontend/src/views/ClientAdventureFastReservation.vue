<template>
  <div>
    <h1 id="caption">
      Available fast reservations for {{ adventureInformation.name }}
    </h1>
    <div class="pt-1">
      <v-card id="fastReservationCard" justify-center>
        <div>
          <v-data-table
            :headers="headersAdventureFastReservation"
            :items="adventureFastReservations"
            hide-default-footer
            class="elevation-1"
          >
            <template v-slot:top>
              <v-toolbar dense dark color="light-blue darken-2">
                <v-spacer></v-spacer>
                <v-toolbar-title class="text-center">
                  Choose fast reservation
                </v-toolbar-title>
                <v-spacer></v-spacer>
                <v-dialog v-model="dialogScheduleAdventureFastReseravation" max-width="60%">
                  <v-card>
                    <v-card-title class="text-h6 justify-center"
                      >Are you want to schedule this reservation?</v-card-title>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                       <v-btn
                        color="success"
                        text
                        @click="scheduleAdventureFastReservation"
                        >Yes</v-btn
                      >
                      <v-spacer></v-spacer>
                      <v-btn
                        color="red"
                        text
                        @click="closeScheduledAdventureFastReservation"
                       >No</v-btn>
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
                  color="success"
                  @click="scheduleAdventureReservation(item)"
                >
                  Schedule
                </v-btn>
                <v-spacer></v-spacer>
              </v-card-actions>
            </template>
          </v-data-table>
        </div>
      </v-card>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    name: "ClientAdventureFastReservation",
    adventureFastReservations: [],
    dialogScheduleAdventureFastReseravation: false,
    headersAdventureFastReservation: [
      {
        text: "Start date and time",
        value: "dateAndTime",
        align: "center",
      },
      {
        text: "Duration (hours)",
        value: "duration",
        align: "center",     
      },
      {
        text: "Max number of persons",
        value: "maxNumberOfPersons",
        align: "center",
      },
      {
        text: "Price (RSD)",
        value: "price",
        align: "center",
      },
      {
        text: "Discount (%)",
        value: "discountPercentage",
        align: "center",
      },
      {
        text: "Adventure additional services",
        value: "adventureAdditionalServices",
        align: "center",
      },
      {
        text: "Fishing equipment",
        value: "fishingEquipmentForAdventure",
        align: "center",
      },
      {
        text: "Actions",
        align: "center",
        value: "actions",
        sortable: false,
      },
    ],
    adventureInformation: null,
    adventureId: null,
    adventureFastReservationItem: {
      adventureFastReservationId: null,
      dateAndTime: null,
      duration: "",
      maxNumberOfPersons: "",
      adventureAdditionalServices: [],
      fishingEquipmentForAdventure: [],
      price: "",
      discountPercentage: "",
    },
    adventureFastReservationDefaultItem: {
      adventureFastReservationId: null,
      dateAndTime: null,
      duration: "",
      maxNumberOfPersons: "",
      adventureAdditionalServices: [],
      fishingEquipmentForAdventure: [],
      price: "",
      discountPercentage: "",
    },
    adventureFastReservationItemIndex: -1,
  }),

  watch: {
    dialogScheduleAdventureFastReseravation(val) {
      val || this.closeScheduledAdventureFastReservation();
    },
  },

  mounted() {
    this.adventureId = localStorage.getItem("adventureId");
    this.findAdventure();
    this.getAllFastReservationsForAdventure();
  },

  methods: {
    findAdventure() {
      this.axios
        .get("http://localhost:8091/adventure/findAdventure/" + this.adventureId)
        .then((response) => (this.adventureInformation = response.data))
        .catch((err) => console.log(err));
    },
    getAllFastReservationsForAdventure() {
      this.axios
        .get(
          "http://localhost:8091/reservationAdventure/adventureFastReservations/" +
            this.adventureId,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          this.adventureFastReservations = response.data;
          console.log(this.adventureFastReservations);
          this.adventureFastReservations.forEach((reservation) => {
            let date = new Date(reservation.dateAndTime);
            reservation.dateAndTime = date.toLocaleDateString() + " " + date.toLocaleTimeString();
            let reservationAdditionalServices = [];
            reservation.adventureAdditionalServices.forEach((element) => {
              let strData = element.adventureAdditionalServiceType.split('_');
              let adventureAdditionalServiceType = " ";
              strData.forEach(s => {
                  adventureAdditionalServiceType += s;
                  adventureAdditionalServiceType += " ";
              });
              reservationAdditionalServices.push(adventureAdditionalServiceType);
            });
            reservation.adventureAdditionalServices =
              reservationAdditionalServices;
            reservationAdditionalServices = [];
            let fishingEquipmentForAdventure = [];
            reservation.fishingEquipmentForAdventure.forEach((element) => {
              fishingEquipmentForAdventure.push(
                " " + element.fishingEquipmentForAdventureType.replace("_", " ")
              );
            });
            reservation.fishingEquipmentForAdventure =
              fishingEquipmentForAdventure;
            fishingEquipmentForAdventure = [];
            if (reservation.discountPercentage == null) {
              reservation.discountPercentage = "No discount";
            }
            if(reservation.adventureAdditionalServices.length == 0) {
              reservation.adventureAdditionalServices = "No additional service";
            } 
            if(reservation.fishingEquipmentForAdventure.length == 0) {
              reservation.fishingEquipmentForAdventure = "No additional fishing equipment";
            }
          });
        })
        .catch((err) => {
          window.location.href = "http://localhost:8083/login";
          alert(
            "401 Unauthorized - respected you are not logged in to the system."
          );
          console.log(err);
        });
    },

    scheduleAdventureFastReservation() {
      this.axios
        .post(
          "http://localhost:8091/reservationAdventure/scheduleFastReservation",
          {
            fastReservationId: this.adventureFastReservationItem.adventureFastReservationId,
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          if (response.data === true) {
              this.adventureFastReservations.splice(this.adventureFastReservationItemIndex,1);
            alert("The fast reservation was successfully scheduled!");
          } else {
            alert("The fast reservation cannot be scheduled because it overlaps with another!");
          }
        });
      this.closeScheduledAdventureFastReservation();
    },

    scheduleAdventureReservation(item) {
      this.adventureFastReservationItemIndex = this.adventureFastReservations.indexOf(item);
      this.adventureFastReservationItem = Object.assign({}, item);
      this.dialogScheduleAdventureFastReseravation = true;
    },

    closeScheduledAdventureFastReservation() {
      this.dialogScheduleAdventureFastReseravation = false;
      this.$nextTick(() => {
        this.adventureFastReservationItem = Object.assign(
          {},
          this.adventureFastReservationDefaultItem
        );
        this.adventureFastReservationItemIndex = -1;
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
#fastReservationCard {
  width: 80%;
  text-align: center;
  margin: auto;
}
</style>