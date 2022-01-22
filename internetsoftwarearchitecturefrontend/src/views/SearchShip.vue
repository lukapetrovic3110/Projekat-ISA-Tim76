<template>
  <div>
    <h1 id="caption">Search ship or boat</h1>
    <v-card id="content" justify-center>
      <v-container fluid>
        <v-data-iterator
          :items="items"
          :items-per-page.sync="itemsPerPage"
          :page.sync="page"
          :search="search"
          :sort-by="sortBy.toLowerCase()"
          :sort-desc="sortDesc"
          hide-default-footer
        >
          <template v-slot:header>
            <v-toolbar dark color="blue darken-3" class="mb-10">
              <v-text-field
                v-model="search"
                clearable
                flat
                solo-inverted
                hide-details
                prepend-inner-icon="mdi-magnify"
                label="Search"
              ></v-text-field>
              <template v-if="$vuetify.breakpoint.mdAndUp">
                <v-spacer></v-spacer>
                <v-select
                  v-model="sortBy"
                  flat
                  solo-inverted
                  hide-details
                  :items="keys"
                  prepend-inner-icon="mdi-magnify"
                ></v-select>
                <v-spacer></v-spacer>
                <v-btn-toggle v-model="sortDesc" mandatory>
                  <v-btn large depressed color="blue" :value="false">
                    <v-icon>mdi-arrow-up</v-icon>
                  </v-btn>
                  <v-btn large depressed color="blue" :value="true">
                    <v-icon>mdi-arrow-down</v-icon>
                  </v-btn>
                </v-btn-toggle>
                <v-spacer></v-spacer>
                <v-dialog
                  v-model="dialogSearchByDate"
                  max-width="60%"
                  persistent
                >
                  <v-card>
                    <v-spacer></v-spacer>
                    <v-card-title class="text-h4 justify-center">
                      Search available ships by date
                    </v-card-title>
                    <v-card-text>
                      <v-container>
                        <v-simple-table>
                          <tr>
                            <v-menu
                              v-model="shipReservationDateMenu"
                              :close-on-content-click="false"
                              :nudge-right="31"
                              transition="scale-transition"
                              offset-y
                              min-width="auto"
                            >
                              <template v-slot:activator="{ on, attrs }">
                                <v-text-field
                                  v-model="shipReservationDate"
                                  label="Reservation start date"
                                  prepend-icon="mdi-calendar"
                                  :allowed-dates="disablePastDates"
                                  v-bind:readonly="true"
                                  v-bind="attrs"
                                  v-on="on"
                                ></v-text-field>
                              </template>
                              <v-date-picker
                                v-model="shipReservationDate"
                                :allowed-dates="disablePastDates"
                                color="info"
                                header-color="primary"
                                @input="shipReservationDateMenu = false"
                              ></v-date-picker>
                            </v-menu>
                            <v-spacer></v-spacer>
                          </tr>
                          <tr>
                            <v-menu
                              ref="shipReservationTimeMenu"
                              v-model="shipReservationTimeMenu"
                              :close-on-content-click="false"
                              :nudge-right="31"
                              :return-value.sync="shipReservationTime"
                              transition="scale-transition"
                              offset-y
                              max-width="31%"
                              min-width="20%"
                            >
                              <template v-slot:activator="{ on, attrs }">
                                <v-text-field
                                  v-model="shipReservationTime"
                                  label="Reservation start time"
                                  prepend-icon="mdi-clock-time-four-outline"
                                  v-bind:readonly="true"
                                  v-bind="attrs"
                                  v-on="on"
                                ></v-text-field>
                              </template>
                              <v-time-picker
                                v-model="shipReservationTime"
                                full-width
                                color="info"
                                header-color="primary"
                                @click:minute="
                                  $refs.shipReservationTimeMenu.save(
                                    shipReservationTime
                                  )
                                "
                              ></v-time-picker>
                            </v-menu>
                          </tr>
                          <tr>
                            <v-text-field
                              label="Duration (hours)"
                              type="number"
                              min="1"
                              v-model="duration"
                            >
                            </v-text-field>
                          </tr>
                          <tr>
                            <v-text-field
                              label="Number of guests"
                              type="number"
                              min="1"
                              v-model="numberOfGuests"
                            >
                            </v-text-field>
                          </tr>
                        </v-simple-table>
                      </v-container>
                    </v-card-text>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn
                        color="green"
                        text
                        @click="findAvailableShipsForSelectedDateInterval"
                      >
                        Search</v-btn
                      >
                      <v-spacer></v-spacer>
                      <v-btn color="red" text @click="closeSearchByDateDialog">
                        Cancel</v-btn
                      >
                      <v-spacer></v-spacer>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
                <v-btn v-if="isClientLogged && isSearchVisible" x-medium color="blue" @click="openDialogSearchByDate">
                  Search by date
                </v-btn>
                <v-btn v-if="isClientLogged && isResetVisible" x-medium color="blue" @click="resetSearchByDate">
                  Reset search by date                
                </v-btn>
              </template>
            </v-toolbar>
          </template>

          <template v-slot:default="props">
            <v-row>
              <v-col
                v-for="item in props.items"
                :key="item.name"
                cols="9"
                sm="1"
                md="3"
                lg="4"
              >
                <v-card class="itemCard">
                  <v-img
                    height="360"
                    alt="ship"
                    v-bind:src="
                      require('../assets/images/' +
                        item.shipImages[0] +
                        '.jpg')
                    "
                  ></v-img>

                  <v-card-title>
                    <h2>
                      {{ item.name }}
                    </h2>
                  </v-card-title>

                  <v-card-text>
                    <v-row align="center" class="mx-0">
                      <v-rating
                        color="amber"
                        dense
                        :value="item.rating"
                        length="5"
                        half-increments
                        readonly
                        size="31"
                      ></v-rating>
                      <div>
                        <h4 class="info--text">{{ item.rating }}</h4>
                      </div>
                      <div id="priceDiv" class="info--text">
                        <h3>{{ item.price }} RSD</h3>
                      </div>
                    </v-row>
                    <div class="my-3 info--text">
                      <h3>{{ item.street }} {{ item.streetNumber }}</h3>
                    </div>
                    <div class="my-3 info--text">
                      <h2>{{ item.city }} , {{ item.country }}</h2>
                    </div>
                    <div class="justify-center">
                      <h4>
                        {{ item.description }}
                      </h4>
                    </div>
                  </v-card-text>

                  <v-divider class="mx-4"></v-divider>

                  <v-card-actions class="justify-center">
                    <v-btn
                      x-large
                      color="primary accent-3"
                      text
                      @click="seeMoreDetails(item)"
                    >
                      SEE MORE DETAILS
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-col>
            </v-row>
          </template>
          <template v-slot:footer>
            <div class="mr-2 mt-10" align="center" justify="center">
              <v-btn
                fab
                dark
                color="info darken-3"
                class="mr-1"
                @click="formerPage"
              >
                <v-icon>mdi-chevron-left</v-icon>
              </v-btn>

              <span class="justify-center info--text">
                Page {{ page }} of {{ numberOfPages }}
              </span>

              <v-btn
                fab
                dark
                color="info darken-3"
                class="ml-1"
                @click="nextPage"
              >
                <v-icon>mdi-chevron-right</v-icon>
              </v-btn>

              <v-spacer></v-spacer>

              <span class="justify-center info--text contentLeft">
                Items per page
              </span>
              <v-menu offset-x>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    dark
                    text
                    color="info"
                    class="ml-2"
                    v-bind="attrs"
                    v-on="on"
                  >
                    {{ itemsPerPage }}
                    <v-icon>mdi-chevron-down</v-icon>
                  </v-btn>
                </template>

                <v-list>
                  <v-list-item
                    v-for="(number, index) in itemsPerPageArray"
                    :key="index"
                    @click="updateItemsPerPage(number)"
                  >
                    <v-list-item-title> {{ number }}</v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>
            </div>
          </template>
        </v-data-iterator>
      </v-container>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "SearchShip",
  data: () => ({
    itemsPerPageArray: [3, 6, 9],
    search: "",
    filter: {},
    sortDesc: false,
    page: 1,
    itemsPerPage: 3,
    items: [],
    sortBy: "name",
    keys: ["name", "rating", "price", "city", "country", "description"],
    dialogSearchByDate: false,
    shipReservationDateMenu: false,
    shipReservationTimeMenu: false,
    shipReservationDate: new Date(
      Date.now() - new Date().getTimezoneOffset() * 60000
    )
      .toISOString()
      .substr(0, 10),
    shipReservationTime: null,
    duration: null,
    numberOfGuests: null,
    client: null,
    isClientLogged: false,
    isSearchVisible: true,
    isResetVisible: false,
    allActiveShip: [],
  }),
  mounted() {
    this.init();
    this.displayAllShip();
  },
  computed: {
    numberOfPages() {
      return Math.ceil(this.items.length / this.itemsPerPage);
    },
    filteredKeys() {
      return this.keys.filter((key) => key !== "name");
    },
  },
  methods: {
    nextPage() {
      if (this.page + 1 <= this.numberOfPages) this.page += 1;
    },
    formerPage() {
      if (this.page - 1 >= 1) this.page -= 1;
    },
    updateItemsPerPage(number) {
      this.itemsPerPage = number;
    },
    disablePastDates(val) {
      return (
        val >=
        new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
          .toISOString()
          .substr(0, 10)
      );
    },
    init() {
      this.axios
        .get("http://localhost:8091/client", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          this.client = response.data;
          this.isClientLogged = true;
        });
    },
    displayAllShip() {
      this.axios
        .get("http://localhost:8091/ship")
        .then((response) => {
          this.items = response.data;
          this.allActiveShip = response.data;
        });
    },
    seeMoreDetails(item) {
      localStorage.setItem("shipId", item.shipId);
      window.location.href = "http://localhost:8083/shipDetails";
    },
    openDialogSearchByDate() {
      this.dialogSearchByDate = true;
    },
    findAvailableShipsForSelectedDateInterval() {
      if (
        this.shipReservationDate == null ||
        this.shipReservationTime == null ||
        this.duration == null ||
        this.numberOfGuests == null
      ) {
        alert(
          "The fields used for searching availible dates for ships must not be empty!"
        );
      } else if (this.duration <= 0) {
        alert("Enter valid duration!");
      } else if (this.numberOfGuests <= 0) {
        alert("Enter the correct number of guests!");
      } else {
        let strTime = this.shipReservationTime + ":00";
        let shipReservationDateAndTime = new Date(
          this.shipReservationDate.toString() + " " + strTime
        );
        console.log(shipReservationDateAndTime);
        console.log(this.duration);
        console.log(this.numberOfGuests);
        this.axios
          .get(
            "http://localhost:8091/ship/findAvailableShipsForSelectedDateIntervalAndNumberOfGuests/" + shipReservationDateAndTime + "/" + this.duration + "/" + this.numberOfGuests, 
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            }
          )
          .then((response) => {
            console.log(response.data);
            if(response.data.length > 0) {
              this.items = response.data;
              if (response.data.length > 1) {
                alert("There are exist ships available for the desired date interval and number of guests, so you can choose one and schedule a reservation.");
              } else {
                alert("There is a ship available for the desired date interval and number of guests, so you can schedule a reservation.");
              }
            } else {
              alert("There are not exist ships available for the desired date interval and number of guests."); 
              this.items = response.data;
            }
          });
          this.closeSearchByDateDialog();
          this.isSearchVisible = false;
          this.isResetVisible = true;
      }
    },
    closeSearchByDateDialog() {
      this.dialogSearchByDate = false;
      this.shipReservationTime = null;
      this.shipReservationDate = new Date(
        Date.now() - new Date().getTimezoneOffset() * 60000
      )
        .toISOString()
        .substr(0, 10);
      this.duration = null;
      this.numberOfGuests = null;
    },
    resetSearchByDate() {
      this.items = this.allActiveShip;
      this.isResetVisible = false;
      this.isSearchVisible = true;
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
#priceDiv {
  margin-left: 36%;
}
#priceDiv h2 {
  text-align: right;
}
#content {
  width: 80%;
  text-align: center;
  margin: auto;
}
.itemCard {
  margin-left: 2%;
  margin: auto;
  height: 100%;
}
.contentLeft {
  margin-left: 80%;
}
</style>