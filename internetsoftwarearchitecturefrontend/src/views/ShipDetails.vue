<template>
  <div>
    <h1 id="caption">{{ shipInformation.name }}</h1>
    <div class="pt-1">
      <v-card id="cottageCard" v-bind:style="{ opacity: opacity }">
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
          </v-form>
        </v-card-text>
        <v-card-actions>
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
        </v-card-actions>
      </v-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "ShipDetails",
  data: () => ({
    opacity: 0.9,
    shipId: null,
    isClient: false,
    shipInformation: null,
    user: null,
    userType: null,
  }),
  mounted() {
    this.getShipInformation();
  },
  methods: {
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
    subscribe() {

        console.log(this.shipId);
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