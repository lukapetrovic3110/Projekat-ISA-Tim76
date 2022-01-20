<template>
  <div>
    <h1 id="caption">{{ adventureInformation.name }}</h1>
    <div class="pt-1">
      <v-card id="cottageCard" v-bind:style="{ opacity: opacity }">
        <v-card-text>
          <v-form class="mx-auto ml-20 mr-20">
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Street"
                v-model="adventureInformation.street"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Street number"
                v-model="adventureInformation.streetNumber"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="City"
                v-model="adventureInformation.city"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Country"
                v-model="adventureInformation.country"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Description"
                v-model="adventureInformation.description"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Fishing instructor first name"
                v-model="adventureInformation.instructorFirstName"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Fishing instructor last name"
                v-model="adventureInformation.instructorLastName"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Fishing instructor email"
                v-model="adventureInformation.instructorEmail"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Fishing instructor phone number"
                v-model="adventureInformation.instructorPhoneNumber"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Availability start"
                v-model="adventureInformation.availabilityStart"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Availability end"
                v-model="adventureInformation.availabilityEnd"
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
  name: "ShipDetails",
  data: () => ({
    opacity: 0.9,
    instructorId: null,
    adventureId: null,
    isClient: false,
    adventureInformation: null,
    user: null,
    userType: null,
  }),
  mounted() {
    this.getShipInformation();
  },
  methods: {
    getShipInformation() {
      this.adventureId = localStorage.getItem("adventureId");
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
        .get(
          "http://localhost:8091/adventure/findAdventure/" + this.adventureId
        )
        .then((response) => {
          this.adventureInformation = response.data;
          this.instructorId = response.data.instructorId;
          this.adventureInformation.availabilityStart = new Date(
            response.data.availabilityStart
          ).toDateString();
          this.adventureInformation.availabilityEnd = new Date(
            response.data.availabilityEnd
          ).toDateString();
        })
        .catch((err) => console.log(err));
    },
    subscribe() {
      console.log(this.adventureId);
      this.axios
        .post(
          "http://localhost:8091/client/subscribeFishingInstructor/",
          {
            id: this.instructorId,
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
              "You have successfully subscribed to this fishing instructor - " +
                this.adventureInformation.instructorFirstName +
                " " +
                this.adventureInformation.instructorLastName +
                "!"
            );
          else
            alert(
              "You have already subscribed to this fishing instructor - " +
                this.adventureInformation.instructorFirstName +
                " " +
                this.adventureInformation.instructorLastName +
                "!"
            );
        })
        .catch((err) => console.log(err));
    },
    viewFastReservations() {
      window.location.href = "/clientAdventureFastReservation";
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