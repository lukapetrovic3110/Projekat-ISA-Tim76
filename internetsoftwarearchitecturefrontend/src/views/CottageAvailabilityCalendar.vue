<template>
  <v-card id="calendarView" justify-center>
    <div class="text-center">
      <v-chip class="ma-2" color="green" label text-color="white">
        Started reservation
      </v-chip>
      <v-chip class="ma-2" color="blue" label text-color="white">
        Scheduled reservation
      </v-chip>
      <v-chip class="ma-2" color="grey darken-1" label text-color="white">
        Waiting reservation
      </v-chip>
    </div>
    <v-row class="fill-height">
      <v-col>
        <v-sheet height="64">
          <v-toolbar flat>
            <v-btn
              outlined
              class="mr-4"
              color="grey darken-2"
              @click="setToday"
            >
              Today
            </v-btn>
            <v-btn fab text small color="grey darken-2" @click="prev">
              <v-icon small> mdi-chevron-left </v-icon>
            </v-btn>
            <v-btn fab text small color="grey darken-2" @click="next">
              <v-icon small> mdi-chevron-right </v-icon>
            </v-btn>
            <v-toolbar-title v-if="$refs.calendar">
              {{ $refs.calendar.title }}
              <v-chip
                class="ma-2"
                color="black"
                outlined
                v-html="
                  'Cottage is available from ' +
                  this.cottageAvailabilityStart +
                  ' to ' +
                  this.cottageAvailabilityEnd +
                  '.'
                "
              ></v-chip>
            </v-toolbar-title>
            <v-spacer></v-spacer>
            <v-menu bottom right>
              <template v-slot:activator="{ on, attrs }">
                <v-btn outlined color="grey darken-2" v-bind="attrs" v-on="on">
                  <span>{{ typeToLabel[type] }}</span>
                  <v-icon right> mdi-menu-down </v-icon>
                </v-btn>
              </template>
              <v-list>
                <v-list-item @click="type = 'day'">
                  <v-list-item-title>Day</v-list-item-title>
                </v-list-item>
                <v-list-item @click="type = 'week'">
                  <v-list-item-title>Week</v-list-item-title>
                </v-list-item>
                <v-list-item @click="type = 'month'">
                  <v-list-item-title>Month</v-list-item-title>
                </v-list-item>
                <v-list-item @click="type = '4day'">
                  <v-list-item-title>4 days</v-list-item-title>
                </v-list-item>
              </v-list>
            </v-menu>
          </v-toolbar>
        </v-sheet>
        <v-sheet height="600">
          <v-calendar
            ref="calendar"
            v-model="focus"
            color="primary"
            :events="events"
            :event-color="getEventColor"
            :type="type"
            @click:event="showEvent"
            @click:more="viewDay"
            @click:date="viewDay"
            @change="updateRange"
          ></v-calendar>
          <v-menu
            v-model="selectedOpen"
            :close-on-content-click="false"
            :activator="selectedElement"
            offset-x
          >
            <v-card color="grey lighten-4" min-width="350px" flat>
              <v-toolbar :color="selectedEvent.color" dark>
                <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text v-if="selectedEvent.clientFirstName !== ''">
                <span
                  v-html="
                    selectedEvent.clientFirstName +
                    ' ' +
                    selectedEvent.clientLastName +
                    ' ' +
                    '(' +
                    selectedEvent.clientEmail +
                    ')'
                  "
                ></span>
              </v-card-text>
              <v-card-text v-else>
                <span>There is no client.</span>
              </v-card-text>
              <v-card-actions>
                <v-btn text color="secondary" @click="selectedOpen = false">
                  Cancel
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-menu>
        </v-sheet>
      </v-col>
    </v-row>
  </v-card>
</template>

<script>
export default {
  name: "CottageAvailabilityCalendar",
  data: () => ({
    reservations: [],
    cottageAvailabilityStart: "",
    cottageAvailabilityEnd: "",
    focus: "",
    type: "month",
    typeToLabel: {
      month: "Month",
      week: "Week",
      day: "Day",
      "4day": "4 Days",
    },
    selectedEvent: {},
    selectedElement: null,
    selectedOpen: false,
    events: [],
    colors: [
      "blue",
      "indigo",
      "deep-purple",
      "cyan",
      "green",
      "orange",
      "grey darken-1",
    ],
  }),

  mounted() {
    this.getAvailabilityCalendarInformation();
    this.$refs.calendar.checkChange();
  },

  methods: {
    viewDay({ date }) {
      this.focus = date;
      this.type = "day";
    },
    getEventColor(event) {
      return event.color;
    },
    setToday() {
      this.focus = "";
    },
    prev() {
      this.$refs.calendar.prev();
    },
    next() {
      this.$refs.calendar.next();
    },
    showEvent({ nativeEvent, event }) {
      const open = () => {
        this.selectedEvent = event;
        this.selectedElement = nativeEvent.target;
        requestAnimationFrame(() =>
          requestAnimationFrame(() => (this.selectedOpen = true))
        );
      };

      if (this.selectedOpen) {
        this.selectedOpen = false;
        requestAnimationFrame(() => requestAnimationFrame(() => open()));
      } else {
        open();
      }

      nativeEvent.stopPropagation();
    },

    getAvailabilityCalendarInformation() {
      this.cottageId = localStorage.getItem("cottageId");
      this.axios
        .get(
          "http://localhost:8091/reservationCottage/availabilityCalendar/" +
            this.cottageId,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          this.reservations = response.data.cottageReservations;
          this.cottageAvailabilityStart = new Date(
            response.data.availabilityStart
          ).toLocaleString();
          this.cottageAvailabilityEnd = new Date(
            response.data.availabilityEnd
          ).toLocaleString();
          this.updateRange();
        })
        .catch((err) => {
          window.location.href = "http://localhost:8083/";
          alert("401 Unauthorized");
          console.log(err);
        });
    },

    updateRange() {
      const events = [];

      this.reservations.forEach((cottageReservation) => {
        events.push({
          name: cottageReservation.cottageName,
          clientFirstName: cottageReservation.clientFirstName,
          clientLastName: cottageReservation.clientLastName,
          clientEmail: cottageReservation.clientEmail,
          start: cottageReservation.reservationStartDate,
          end: cottageReservation.reservationEndDate,
          color:
            this.colors[this.selectColor(cottageReservation.reservationStatus)],
          timed: true,
        });
      });

      this.events = events;
    },

    selectColor(reservationStatus) {
      if (reservationStatus === "STARTED") {
        return 4; // green
      }
      if (reservationStatus === "SCHEDULED") {
        return 0; // blue
      }
      if (reservationStatus === "WAITING") {
        return 6; // grey darken-1
      }
    },
  },
};
</script>

<style scoped>
#calendarView {
  width: 83%;
  text-align: center;
  margin: auto;
  margin-top: 3%;
}
</style>