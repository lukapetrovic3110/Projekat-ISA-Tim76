<template>
  <div>
    <h1 id="caption">{{ cottageInformation.name }}</h1>
    <div class="pt-1">
      <v-card id="cottageCard" v-bind:style="{ opacity: opacity }">
        <v-card-text>
          <v-form class="mx-auto ml-20 mr-20">
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Street"
                v-model="cottageInformation.street"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Street number"
                v-model="cottageInformation.streetNumber"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="City"
                v-model="cottageInformation.city"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Country"
                v-model="cottageInformation.country"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Description"
                v-model="cottageInformation.description"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Number of rooms"
                v-model="cottageInformation.numberOfRooms"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Number of beds per room"
                v-model="cottageInformation.numberOfBedsPerRoom"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Price per day (RSD)"
                v-model="cottageInformation.price"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Availability start"
                v-model="cottageInformation.availabilityStart"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Availability end"
                v-model="cottageInformation.availabilityEnd"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-card class="cottageAdditionalInformationCard">
                <v-card-title class="grey lighten-3">
                  Cottage rules
                  <v-spacer></v-spacer>
                  <v-text-field
                    v-model="searchCottageRule"
                    append-icon="mdi-magnify"
                    label="Search"
                    single-line
                    hide-details
                  ></v-text-field>
                </v-card-title>
                <v-data-table
                  :headers="headersCottageRule"
                  :items="cottageInformation.cottageRules"
                  :search="searchCottageRule"
                  x-medium
                ></v-data-table>
              </v-card>
            </v-row>
            <v-row>
              <v-card class="cottageAdditionalInformationCard">
                <v-card-title class="grey lighten-3">
                  Price List for additional payments
                  <v-spacer></v-spacer>
                  <v-text-field
                    v-model="searchCottagePriceTag"
                    append-icon="mdi-magnify"
                    label="Search"
                    single-line
                    hide-details
                  ></v-text-field>
                </v-card-title>
                <v-data-table
                  :headers="headersCottagePriceTag"
                  :items="cottageInformation.priceList.priceTags"
                  :search="searchCottagePriceTag"
                ></v-data-table>
              </v-card>
            </v-row>
            <v-row>
              <vue-upload-multiple-image
                class="images"
                @mark-is-primary="markIsPrimary"
                :data-images="cottageImagesForDisplay"
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
            Search desired reservation for cottage
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-simple-table>
                <tr>
                  <v-menu
                    v-model="desiredCottageReservationDateMenu"
                    :close-on-content-click="false"
                    :nudge-right="31"
                    transition="scale-transition"
                    offset-y
                    min-width="auto"
                  >
                    <template v-slot:activator="{ on, attrs }">
                      <v-text-field
                        v-model="desiredCottageReservationDate"
                        label="Reservation start date"
                        prepend-icon="mdi-calendar"
                        :allowed-dates="disablePastDates"
                        v-bind:readonly="true"
                        v-bind="attrs"
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-date-picker
                      v-model="desiredCottageReservationDate"
                      :allowed-dates="disablePastDates"
                      color="info"
                      header-color="primary"
                      @input="desiredCottageReservationDateMenu = false"
                    ></v-date-picker>
                  </v-menu>
                  <v-spacer></v-spacer>
                </tr>
                <tr>
                  <v-menu
                    ref="desiredCottageReservationTimeMenu"
                    v-model="desiredCottageReservationTimeMenu"
                    :close-on-content-click="false"
                    :nudge-right="31"
                    :return-value.sync="desiredCottageReservationTime"
                    transition="scale-transition"
                    offset-y
                    max-width="31%"
                    min-width="20%"
                  >
                    <template v-slot:activator="{ on, attrs }">
                      <v-text-field
                        v-model="desiredCottageReservationTime"
                        label="Reservation start time"
                        prepend-icon="mdi-clock-time-four-outline"
                        v-bind:readonly="true"
                        v-bind="attrs"
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-time-picker
                      v-model="desiredCottageReservationTime"
                      full-width
                      color="info"
                      header-color="primary"
                      @click:minute="
                        $refs.desiredCottageReservationTimeMenu.save(
                          desiredCottageReservationTime
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
                searchDesiredReservationDateIntervalAndNumberOfGuestsForCottage
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
    cottageId: null,
    isClient: false,
    cottageInformation: null,
    searchCottageRule: "",
    searchCottagePriceTag: "",
    user: null,
    userType: null,
    headersCottageRule: [
      {
        text: "Rule",
        align: "start",
        sortable: false,
        value: "description",
      },
    ],
    headersCottagePriceTag: [
      {
        text: "Service description",
        align: "start",
        value: "serviceName",
      },
      {
        text: "Price (RSD)",
        value: "price",
      },
    ],
    cottageReservationDateAndTime: null,
    duration: "",
    numberOfGuests: "",
    priceReservation: null,
    cottageImages: null,
    cottageImagesForDisplay: [],
    images: {
      imagesInformation: [],
    },
    imagesFileList: [],

    dialogSearchDesiredReservation: false,
    desiredCottageReservationDateMenu: false,
    desiredCottageReservationTimeMenu: false,
    desiredCottageReservationDate: new Date(
      Date.now() - new Date().getTimezoneOffset() * 60000
    )
      .toISOString()
      .substr(0, 10),
    desiredCottageReservationTime: null,
    desiredDuration: null,
    desiredNumberOfGuests: null,
    client: null,
    desiredCottageReservationDateAndTime: null,
  }),
  mounted() {
    this.getCottageInformation();
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
    getCottageInformation() {
      this.cottageId = localStorage.getItem("cottageId");
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
          this.getCottageImages();
        });

      this.axios
        .get("http://localhost:8091/cottage/findCottage/" + this.cottageId)
        .then((response) => {
          this.cottageInformation = response.data;
          this.cottageInformation.availabilityStart = new Date(
            response.data.availabilityStart
          ).toLocaleString();
          this.cottageInformation.availabilityEnd = new Date(
            response.data.availabilityEnd
          ).toLocaleString();
        })
        .catch((err) => console.log(err));
    },
    getCottageImages() {
      this.axios
        .get(
          "http://localhost:8091/image/getImages/cottage/" + this.cottageId,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          this.cottageImages = response.data;
          this.cottageImages.images.forEach((image) => {
            this.cottageImagesForDisplay.push({
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
          "http://localhost:8091/client/subscribeCottage",
          {
            id: this.cottageId,
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
              "You have successfully subscribed to this cottage - " +
                this.cottageInformation.name +
                "!"
            );
          else
            alert(
              "You have already subscribed to this cottage - " +
                this.cottageInformation.name +
                "!"
            );
        })
        .catch((err) => console.log(err));
    },
    viewFastReservations() {
      window.location.href = "/clientCottageFastReservation";
    },
    createReservation() {
       this.cottageReservationDateAndTime = localStorage.getItem(
          "cottageReservationDateAndTime"
        );
        this.duration = localStorage.getItem("duration");
        this.numberOfGuests = localStorage.getItem("numberOfGuests");

      if (this.cottageReservationDateAndTime === "" || this.duration === "" || this.numberOfGuests === "")
      {
        alert("Please first do a search by date interval and number of guests on the previous page.");
        window.location.href = "/searchCottage";
        //this.dialogSearchDesiredReservation = true;
      } else {
        this.priceReservation = this.duration * this.cottageInformation.price;
        this.axios
          .post(
            "http://localhost:8091/reservationCottage/createReservation/",
            {
              id: this.cottageId,
              reservationDateAndTime: new Date(
                this.cottageReservationDateAndTime.toString()
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
        localStorage.setItem("cottageReservationDateAndTime", "");
        localStorage.setItem("duration", "");
        localStorage.setItem("numberOfGuests", "");
      }
    },
    markIsPrimary(index, fileList) {
      console.log("markIsPrimary data", index, fileList);
    },
    closeSearchByDateDialog() {
      this.dialogSearchDesiredReservation = false;
      this.desiredCottageReservationTime = "";
      this.desiredCottageReservationDate = new Date(
        Date.now() - new Date().getTimezoneOffset() * 60000
      )
        .toISOString()
        .substr(0, 10);
      this.desiredDuration = "";
      this.desiredNumberOfGuests = "";
    }
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
#cottageCard {
  width: 45%;
  margin: auto;
}
.cottageAdditionalInformationCard {
  margin: auto;
  margin-top: 2%;
  width: 90%;
}
.images {
  width: 60%;
  margin-left: 36%;
  margin-top: 3%;
  height: 300px;
}
</style>