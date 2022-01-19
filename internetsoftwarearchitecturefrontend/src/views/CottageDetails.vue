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
        </v-card-actions>
      </v-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "CottageDetails",
  data: () => ({
    opacity: 0.9,
    cottageId: null,
    isClient: false,
    cottageInformation: null,
    user: null,
    userType: null,
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
        });

      this.axios
        .get("http://localhost:8091/cottage/findCottage/" + this.cottageId)
        .then((response) => {
          this.cottageInformation = response.data;
          this.cottageInformation.availabilityStart = new Date(
            response.data.availabilityStart
          ).toDateString();
          this.cottageInformation.availabilityEnd = new Date(
            response.data.availabilityEnd
          ).toDateString();
        })
        .catch((err) => console.log(err));
    },
    subscribe() {
      this.axios
        .post(
          "http://localhost:8091/client/subscribeCottage/",
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
</style>