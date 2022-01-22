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
              <vue-upload-multiple-image class="images"
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
  }),
  mounted() {
    this.getCottageInformation();
  },
  methods: {
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

      console.log(this.cottageReservationDateAndTime);
      console.log(this.duration);
      console.log(this.numberOfGuests);

      if (
        this.cottageReservationDateAndTime != null &&
        this.duration != null &&
        this.numberOfGuests != null
      ) {
        this.priceReservation = this.duration * this.cottageInformation.price;
        this.axios
          .post(
            "http://localhost:8091/reservationCottage/createReservation/",
            {
              cottageId: this.cottageId,
              cottageReservationDateAndTime: new Date(
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
      }
    },
    markIsPrimary(index, fileList) {
      console.log("markIsPrimary data", index, fileList);
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