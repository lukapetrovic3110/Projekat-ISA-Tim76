<template>
  <v-card style="margin-top: 3%" width="40%" class="mx-auto">
    <v-card-title class="justify-center">
      <h1 class="display-1 mt-20">Cottage information</h1>
    </v-card-title>
    <v-card-text>
      <v-form class="mx-auto ml-20 mr-20">
        <v-text-field
          label="Name"
          v-model="cottageInformation.name"
          :rules="[() => !!cottageInformation.name || 'This field is required']"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="Street Name"
          v-model="cottageInformation.address.street"
          :rules="[
            () =>
              !!cottageInformation.address.street || 'This field is required',
          ]"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="Street Number"
          v-model="cottageInformation.address.streetNumber"
          :rules="[
            () =>
              !!cottageInformation.address.streetNumber ||
              'This field is required',
          ]"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="City"
          v-model="cottageInformation.address.city"
          :rules="[
            () => !!cottageInformation.address.city || 'This field is required',
          ]"
          :error-messages="errorMessages"
        />
        <v-autocomplete
          class="countryCombo"
          ref="country"
          v-model="cottageInformation.address.country"
          :rules="[
            () =>
              !!cottageInformation.address.country || 'This field is required',
          ]"
          :items="countries"
          label="Country"
          placeholder="Select..."
        />
        <v-text-field
          label="Description"
          v-model="cottageInformation.description"
          :rules="[
            () => !!cottageInformation.description || 'This field is required',
          ]"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="Number of rooms"
          v-model="cottageInformation.numberOfRooms"
          max="10"
          min="1"
          single-line
          type="number"
          style="width: 100%"
          :rules="rules"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="Number of beds per room"
          v-model="cottageInformation.numberOfBedsPerRoom"
          max="10"
          min="1"
          single-line
          type="number"
          style="width: 100%"
          :rules="rules"
          :error-messages="errorMessages"
        />

        <v-dialog
          ref="dialogAStart"
          v-model="dialogAvailabilityStart"
          :return-value.sync="cottageInformation.availabilityStart"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
              v-model="cottageInformation.availabilityStart"
              label="Available from"
              prepend-icon="mdi-calendar"
              readonly
              v-bind="attrs"
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker v-model="date" scrollable>
            <v-spacer></v-spacer>
            <v-btn
              text
              color="primary"
              @click="dialogAvailabilityStart = false"
            >
              Cancel
            </v-btn>
            <v-btn text color="primary" @click="$refs.dialogAStart.save(date)">
              OK
            </v-btn>
          </v-date-picker>
        </v-dialog>
        <v-dialog
          ref="dialogAEnd"
          v-model="dialogAvailabilityEnd"
          :return-value.sync="cottageInformation.availabilityEnd"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
              v-model="cottageInformation.availabilityEnd"
              label="Available from"
              prepend-icon="mdi-calendar"
              readonly
              v-bind="attrs"
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker v-model="date" scrollable>
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="dialogAvailabilityEnd = false">
              Cancel
            </v-btn>
            <v-btn text color="primary" @click="$refs.dialogAEnd.save(date)">
              OK
            </v-btn>
          </v-date-picker>
        </v-dialog>




      </v-form>
    </v-card-text>
    <div></div>
    <v-card-actions>
      <v-btn
        class="mx-auto mb-10 mt-10; color:white"
        color="info"
        elevation="2"
        x-large
        raised
        v-on:click="save"
        >Save</v-btn
      >
      <v-btn
        class="mx-auto mb-10 mt-10; color:white"
        color="info"
        elevation="2"
        x-large
        raised
        v-on:click="cancel"
        >Cancel</v-btn
      >
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: "EditCottageInformation",

  data: () => ({
    cottageId: "",
    cottageInformation: null,
    errorMessages: "",

    countries: ["Serbia"],
    datePickerFormat: "dd.MM.yyyy.",

    dialogAvailabilityStart: false,
    dialogAvailabilityEnd: false,
    date: "",

  }),

  mounted() {
    this.getCottageInformation();
  },

  methods: {
    getCottageInformation() {
      this.cottageId = localStorage.getItem("cottageId");
      this.axios
        .get("http://localhost:8091/cottage/" + this.cottageId, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          this.cottageInformation = response.data;
          this.cottageInformation.availabilityStart = new Date(
            response.data.availabilityStart
          ).toDateString();
          this.cottageInformation.availabilityEnd = new Date(
            response.data.availabilityEnd
          ).toDateString();
          console.log(this.cottageInformation);
        })
        .catch((err) => console.log(err));
    },
  },

};
</script>

<style scoped>
</style>