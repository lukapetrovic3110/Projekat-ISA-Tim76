<template>
  <div>
    <h1 id="caption">
      Available fast reservations for {{ shipInformation.name }}
    </h1>
    <div class="pt-1">
      <v-card id="fastReservationCard" justify-center>
        <div>
          <v-data-table
            :headers="headersShipFastReservation"
            :items="shipFastReservations"
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
                <v-dialog v-model="dialogScheduleShipFastReseravation" max-width="60%">
                  <v-card>
                    <v-card-title class="text-h6 justify-center"
                      >Are you want to schedule this reservation?</v-card-title>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                       <v-btn
                        color="success"
                        text
                        @click="scheduleShipFastReservation"
                        >Yes</v-btn
                      >
                      <v-spacer></v-spacer>
                      <v-btn
                        color="red"
                        text
                        @click="closeScheduledShipFastReservation"
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
                  @click="scheduleShipReservation(item)"
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
    name: "ClientShipFastReservation",
    shipFastReservations: [],
    dialogScheduleShipFastReseravation: false,
    headersShipFastReservation: [
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
        text: "Ship additional services",
        value: "shipAdditionalServices",
        align: "center",
      },
      {
        text: "Fishing equipment",
        value: "fishingEquipmentForShip",
        align: "center",
      },
      {
        text: "Actions",
        align: "center",
        value: "actions",
        sortable: false,
      },
    ],
    shipInformation: null,
    shipId: null,
    shipFastReservationItem: {
      shipFastReservationId: null,
      dateAndTime: null,
      duration: "",
      maxNumberOfPersons: "",
      shipAdditionalServices: [],
      fishingEquipmentForShip: [],
      price: "",
      discountPercentage: "",
    },
    shipFastReservationDefaultItem: {
      shipFastReservationId: null,
      dateAndTime: null,
      duration: "",
      maxNumberOfPersons: "",
      shipAdditionalServices: [],
      fishingEquipmentForShip: [],
      price: "",
      discountPercentage: "",
    },
    shipFastReservationItemIndex: -1,
  }),

  watch: {
    dialogScheduleShipFastReseravation(val) {
      val || this.closeScheduledShipFastReservation();
    },
  },

  mounted() {
    this.shipId = localStorage.getItem("shipId");
    this.findShip();
    this.getAllFastReservationsForShip();
  },

  methods: {
    findShip() {
      this.axios
        .get("http://localhost:8091/ship/findShip/" + this.shipId)
        .then((response) => (this.shipInformation = response.data))
        .catch((err) => console.log(err));
    },
    getAllFastReservationsForShip() {
      this.axios
        .get(
          "http://localhost:8091/reservationShip/shipFastReservations/" +
            this.shipId,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          this.shipFastReservations = response.data;
          console.log(this.shipFastReservations);
          this.shipFastReservations.forEach((reservation) => {
            let date = new Date(reservation.dateAndTime);
            reservation.dateAndTime = date.toLocaleDateString() + " " + date.toLocaleTimeString();
            let reservationAdditionalServices = [];
            reservation.shipAdditionalServices.forEach((element) => {
              reservationAdditionalServices.push(
                " " + element.shipAdditionalServiceType.replace("_", " ")
              );
            });
            reservation.shipAdditionalServices =
              reservationAdditionalServices;
            reservationAdditionalServices = [];
            let fishingEquipmentForShip = [];
            reservation.fishingEquipmentForShip.forEach((element) => {
              fishingEquipmentForShip.push(
                " " + element.fishingEquipmentForShipType.replace("_", " ")
              );
            });
            reservation.fishingEquipmentForShip =
              fishingEquipmentForShip;
            fishingEquipmentForShip = [];
            if (reservation.discountPercentage == null) {
              reservation.discountPercentage = "No discount";
            }
            if(reservation.shipAdditionalServices.length == 0) {
              reservation.shipAdditionalServices = "No additional service";
            } 
            if(reservation.fishingEquipmentForShip.length == 0) {
              reservation.fishingEquipmentForShip = "No additional fishing equipment";
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

    scheduleShipFastReservation() {
      this.axios
        .post(
          "http://localhost:8091/reservationShip/scheduleFastReservation",
          {
            fastReservationId: this.shipFastReservationItem.shipFastReservationId,
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          if (response.data === true) {
              this.shipFastReservations.splice(this.shipFastReservationItemIndex,1);
            alert("The fast reservation was successfully scheduled!");
          } else {
            alert("The fast reservation cannot be scheduled because it overlaps with another!");
          }
        });
      this.closeScheduledShipFastReservation();
    },

    scheduleShipReservation(item) {
      this.shipFastReservationItemIndex = this.shipFastReservations.indexOf(item);
      this.shipFastReservationItem = Object.assign({}, item);
      this.dialogScheduleShipFastReseravation = true;
    },

    closeScheduledShipFastReservation() {
      this.dialogScheduleShipFastReseravation = false;
      this.$nextTick(() => {
        this.shipFastReservationItem = Object.assign(
          {},
          this.shipFastReservationDefaultItem
        );
        this.shipFastReservationItemIndex = -1;
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