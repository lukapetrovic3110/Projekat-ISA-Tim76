<template>
  <div>
    <h1 id="caption">
      Available fast reservations for {{ cottageInformation.name }}
    </h1>
    <div class="pt-1">
      <v-card id="tableView" justify-center>
        <div>
          <v-data-table
            :headers="headersCottageFastReservation"
            :items="cottageFastReservations"
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
                <v-dialog v-model="dialogScheduleCottageFastReseravation" max-width="60%">
                  <v-card>
                    <v-card-title class="text-h6 justify-center"
                      >Are you want to schedule this reservation?</v-card-title>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                       <v-btn
                        color="success"
                        text
                        @click="scheduleCottageFastReservation"
                        >Yes</v-btn
                      >
                      <v-spacer></v-spacer>
                      <v-btn
                        color="red"
                        text
                        @click="closeScheduledCottageFastReservation"
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
                  @click="scheduleCottageReservation(item)"
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
    name: "ClientCottageFastReservation",
    cottageFastReservations: [],
    dialogCottageReservationStartDateAndTime: false,
    dialogScheduleCottageFastReseravation: false,
    headersCottageFastReservation: [
      {
        text: "Start date and time",
        align: "center",
        value: "dateAndTime",
      },
      {
        text: "Duration (days)",
        align: "center",
        value: "duration",
      },
      {
        text: "Max number of persons",
        align: "center",
        value: "maxNumberOfPersons",
      },
      {
        text: "Price (RSD)",
        align: "center",
        value: "price",
      },
      {
        text: "Discount (%)",
        align: "center",
        value: "discountPercentage",
      },
      {
        text: "Cottage additional services",
        align: "center",
        value: "cottageAdditionalServices",
      },
      {
        text: "Actions",
        align: "center",
        value: "actions",
        sortable: false,
      },
    ],
    cottageInformation: null,
    cottageId: null,
    cottageFastReservationItem: {
      cottageFastReservationId: null,
      dateAndTime: null,
      duration: "",
      maxNumberOfPersons: "",
      cottageAdditionalServices: [],
      price: "",
      discountPercentage: "",
    },
    cottageFastReservationDefaultItem: {
      cottageFastReservationId: null,
      dateAndTime: null,
      duration: "",
      maxNumberOfPersons: "",
      cottageAdditionalServices: [],
      price: "",
      discountPercentage: "",
    },
    cottageFastReservationItemIndex: -1,
  }),

  watch: {
    dialogScheduleCottageFastReseravation(val) {
      val || this.closeScheduledCottageFastReservation();
    },
  },

  mounted() {
    this.cottageId = localStorage.getItem("cottageId");
    this.findCottage();
    this.getAllFastReservationsForCottage();
  },

  methods: {
    findCottage() {
      this.axios
        .get(
          "http://localhost:8091/cottage/findCottage/" + this.cottageId,
        )
        .then((response) => (this.cottageInformation = response.data))
        .catch((err) => console.log(err));
    },
    getAllFastReservationsForCottage() {
      this.axios
        .get(
          "http://localhost:8091/reservationCottage/cottageFastReservations/" +
            this.cottageId,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          this.cottageFastReservations = response.data;
          console.log(this.cottageFastReservations);
          this.cottageFastReservations.forEach((reservation) => {
            let date = new Date(reservation.dateAndTime);
            reservation.dateAndTime = date
              .toISOString()
              .replace("T", " ")
              .substring(0, date.toISOString().length - 5);
            let reservationAdditionalServices = [];
            reservation.cottageAdditionalServices.forEach((element) => {
              reservationAdditionalServices.push(
                " " + element.cottageAdditionalServiceType.replace("_", " ")
              );
            });
            reservation.cottageAdditionalServices =
              reservationAdditionalServices;
            reservationAdditionalServices = [];
            if (reservation.discountPercentage === null) {
              reservation.discountPercentage = "No discount";
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

    scheduleCottageFastReservation() {
      this.axios
        .post(
          "http://localhost:8091/reservationCottage/scheduleFastReservation",
          {
            fastReservationId: this.cottageFastReservationItem.cottageFastReservationId,
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          if (response.data === true) {
              this.cottageFastReservations.splice(this.cottageFastReservationItemIndex,1);
            alert("The fast reservation was successfully scheduled!");
          } else {
            alert("The fast reservation cannot be scheduled because it overlaps with another!");
          }
        });
      this.closeScheduledCottageFastReservation();
    },

    scheduleCottageReservation(item) {
      this.cottageFastReservationItemIndex = this.cottageFastReservations.indexOf(item);
      this.cottageFastReservationItem = Object.assign({}, item);
      this.dialogScheduleCottageFastReseravation = true;
    },

    closeScheduledCottageFastReservation() {
      this.dialogScheduleCottageFastReseravation = false;
      this.$nextTick(() => {
        this.cottageFastReservationItem = Object.assign(
          {},
          this.cottageFastReservationDefaultItem
        );
        this.cottageFastReservationItemIndex = -1;
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
#tableView {
  width: 80%;
  text-align: center;
  margin: auto;
}
</style>