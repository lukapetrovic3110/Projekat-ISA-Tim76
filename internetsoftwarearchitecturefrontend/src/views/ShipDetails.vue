<template>
  <div>
    <h1 id="caption">{{ shipInformation.name }}</h1>
    <div class="pt-1">
      <v-card id="shipCard" v-bind:style="{ opacity: opacity }">
        <v-card-text>
          <v-form class="mx-auto ml-20 mr-20">
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Street"
                v-model="shipInformation.street"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Street number"
                v-model="shipInformation.streetNumber"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="City"
                v-model="shipInformation.city"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Country"
                v-model="shipInformation.country"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Description"
                v-model="shipInformation.description"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Maximum of person on ship"
                v-model="shipInformation.capacity"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Maximum speed"
                v-model="shipInformation.maxSpeed"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Availability start"
                v-model="shipInformation.availabilityStart"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Availability end"
                v-model="shipInformation.availabilityEnd"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <vue-upload-multiple-image
                class="images"
                @mark-is-primary="markIsPrimary"
                :data-images="shipImagesForDisplay"
                idUpload="myIdUpload"
                idEdit="myIdEdit"
                :max-image="15"
                primary-text="Default"
                browse-text="Upload images"
                drag-text="Drag images"
                mark-is-primary-text="Set as default"
                popup-text="This image will be displayed as default"
                :multiple="true"
                :show-edit="false"
                :show-delete="false"
                :show-add="false"
              ></vue-upload-multiple-image>
            </v-row>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            v-if="isClient"
            class="mx-auto mb-10 mt-10"
            color="info"
            elevation="2"
            x-large
            raised
            v-on:click="subscribe"
            >Subscribe</v-btn
          >
          <v-spacer></v-spacer>
          <v-btn
            v-if="isClient"
            class="mb-10 mt-10"
            color="success"
            elevation="2"
            x-large
            raised
            v-on:click="viewFastReservations"
            >View available fast reservations
          </v-btn>
          <v-spacer></v-spacer>
          <v-btn
            v-if="isClient"
            class="mb-10 mt-10"
            color="info"
            elevation="2"
            x-large
            raised
            v-on:click="createReservation"
            >Create reservation
          </v-btn>
          <v-spacer></v-spacer>
        </v-card-actions>
      </v-card>
      <v-spacer></v-spacer>
      <v-dialog
        v-model="dialogSearchDesiredReservation"
        max-width="60%"
        persistent
      >
        <v-card>
          <v-spacer></v-spacer>
          <v-card-title class="text-h4 justify-center">
            Search desired reservation for ship
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-simple-table>
                <tr>
                  <v-menu
                    v-model="desiredShipReservationDateMenu"
                    :close-on-content-click="false"
                    :nudge-right="31"
                    transition="scale-transition"
                    offset-y
                    min-width="auto"
                  >
                    <template v-slot:activator="{ on, attrs }">
                      <v-text-field
                        v-model="desiredShipReservationDate"
                        label="Reservation start date"
                        prepend-icon="mdi-calendar"
                        :allowed-dates="disablePastDates"
                        v-bind:readonly="true"
                        v-bind="attrs"
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-date-picker
                      v-model="desiredShipReservationDate"
                      :allowed-dates="disablePastDates"
                      color="info"
                      header-color="primary"
                      @input="desiredShipReservationDateMenu = false"
                    ></v-date-picker>
                  </v-menu>
                  <v-spacer></v-spacer>
                </tr>
                <tr>
                  <v-menu
                    ref="desiredShipReservationTimeMenu"
                    v-model="desiredShipReservationTimeMenu"
                    :close-on-content-click="false"
                    :nudge-right="31"
                    :return-value.sync="desiredShipReservationTime"
                    transition="scale-transition"
                    offset-y
                    max-width="31%"
                    min-width="20%"
                  >
                    <template v-slot:activator="{ on, attrs }">
                      <v-text-field
                        v-model="desiredShipReservationTime"
                        label="Reservation start time"
                        prepend-icon="mdi-clock-time-four-outline"
                        v-bind:readonly="true"
                        v-bind="attrs"
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-time-picker
                      v-model="desiredShipReservationTime"
                      full-width
                      color="info"
                      header-color="primary"
                      @click:minute="
                        $refs.desiredShipReservationTimeMenu.save(
                          desiredShipReservationTime
                        )
                      "
                    ></v-time-picker>
                  </v-menu>
                </tr>
                <tr>
                  <v-text-field
                    label="Duration (days)"
                    type="number"
                    min="1"
                    v-model="desiredDuration"
                  >
                  </v-text-field>
                </tr>
                <tr>
                  <v-text-field
                    label="Number of guests"
                    type="number"
                    min="1"
                    v-model="desiredNumberOfGuests"
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
              @click="
                searchDesiredReservationDateIntervalAndNumberOfGuestsForShip
              "
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
    </div>
  </div>
</template>

<script>
import VueUploadMultipleImage from "vue-upload-multiple-image";
export default {
  components: {
    VueUploadMultipleImage,
  },
  data: () => ({
    opacity: 0.9,
    shipId: null,
    isClient: false,
    shipInformation: null,
    user: null,
    userType: null,
    shipReservationDateAndTime: null,
    duration: "",
    numberOfGuests: "",
    priceReservation: null,
    shipImages: null,
    shipImagesForDisplay: [],
    images: {
      imagesInformation: [],
    },
    imagesFileList: [],
    dialogSearchDesiredReservation: false,
    desiredShipReservationDateMenu: false,
    desiredShipReservationTimeMenu: false,
    desiredShipReservationDate: new Date(
      Date.now() - new Date().getTimezoneOffset() * 60000
    )
      .toISOString()
      .substr(0, 10),
    desiredShipReservationTime: null,
    desiredDuration: null,
    desiredNumberOfGuests: null,
    client: null,
    desiredShipReservationDateAndTime: null,
  }),
  mounted() {
    this.getShipInformation();
  },
  methods: {
    disablePastDates(val) {
      return (
        val >=
        new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
          .toISOString()
          .substr(0, 10)
      );
    },
    getShipInformation() {
      this.shipId = localStorage.getItem("shipId");
      this.axios
        .get("http://localhost:8091/auth", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          this.user = response.data;
          this.userType = response.data.userType;
          if (this.userType === "CLIENT") this.isClient = true;
          this.getShipImages();
        });

      this.axios
        .get("http://localhost:8091/ship/findShip/" + this.shipId)
        .then((response) => {
          this.shipInformation = response.data;
          this.shipInformation.availabilityStart = new Date(
            response.data.availabilityStart
          ).toDateString();
          this.shipInformation.availabilityEnd = new Date(
            response.data.availabilityEnd
          ).toDateString();
        })
        .catch((err) => console.log(err));
    },
    getShipImages() {
      this.axios
        .get("http://localhost:8091/image/getImages/ship/" + this.shipId, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          this.shipImages = response.data;
          this.shipImages.images.forEach((image) => {
            this.shipImagesForDisplay.push({
              default: image.defaultImage,
              highlight: image.highlight,
              name: image.name + ".jpg",
              path: image.path,
            });
          });
        })
        .catch((err) => console.log(err));
    },
    subscribe() {
      this.axios
        .post(
          "http://localhost:8091/client/subscribeShip/",
          {
            id: this.shipId,
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          if (response.data)
            alert(
              "You have successfully subscribed to this ship - " +
                this.shipInformation.name +
                "!"
            );
          else
            alert(
              "You have already subscribed to this ship - " +
                this.shipInformation.name +
                "!"
            );
        })
        .catch((err) => console.log(err));
    },
    viewFastReservations() {
      window.location.href = "/clientShipFastReservation";
    },
    createReservation() {
      this.shipReservationDateAndTime = localStorage.getItem(
        "shipReservationDateAndTime"
      );
      this.duration = localStorage.getItem("duration");
      this.numberOfGuests = localStorage.getItem("numberOfGuests");

      if (
        this.shipReservationDateAndTime === "" ||
        this.duration === "" ||
        this.numberOfGuests === ""
      ) {
        console.log(this.shipReservationDateAndTime);
        this.dialogSearchDesiredReservation = true;
      } else {
        this.priceReservation = this.duration * this.shipInformation.price;
        this.axios
          .post(
            "http://localhost:8091/reservationShip/createReservation/",
            {
              id: this.shipId,
              reservationDateAndTime: new Date(
                this.shipReservationDateAndTime.toString()
              ),
              duration: this.duration,
              numberOfGuests: this.numberOfGuests,
              reservationPrice: this.priceReservation,
            },
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
              },
            }
          )
          .then((response) => {
            if (response.data.isCreated) {
              alert(response.data.answer);
              window.location.href = "/clientScheduledReservation";
            } else {
              alert(response.data.answer);
            }
          })
          .catch((err) => console.log(err));
        localStorage.setItem("shipReservationDateAndTime", "");
        localStorage.setItem("duration", "");
        localStorage.setItem("numberOfGuests", "");
      }
    },
    markIsPrimary(index, fileList) {
      console.log("markIsPrimary data", index, fileList);
    },
    closeSearchByDateDialog() {
      this.dialogSearchDesiredReservation = false;
      this.desiredShipReservationTime = "";
      this.desiredShipReservationDate = new Date(
        Date.now() - new Date().getTimezoneOffset() * 60000
      )
        .toISOString()
        .substr(0, 10);
      this.desiredDuration = "";
      this.desiredNumberOfGuests = "";
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
#shipCard {
  width: 45%;
  margin: auto;
}
</style>