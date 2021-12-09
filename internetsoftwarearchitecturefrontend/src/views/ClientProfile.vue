<template>
  <div>
    <h1 id="myPersonalInformation">My personal information</h1>
    <div class="pt-1">
      <v-card id="personalInfoCard" v-bind:style="{ opacity: opacity }">
        <v-card-text>
          <v-form class="mx-auto mt-5 mb-5 mr-10 ml-10">
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="First name"
                v-model="firstName"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Last name"
                v-model="lastName"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Phone number"
                v-model="phoneNumber"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Street"
                v-model="street"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Street number"
                v-model="streetNumber"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="City"
                v-model="city"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Country"
                v-model="country"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="E-mail"
                v-model="email"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
          </v-form>
        </v-card-text>
        <v-card-actions class="justify-center">
          <v-btn
            v-on:click="editPersonalInfo"
            color="info"
            class="mt-2 mr-10 p-5 mb-5"
            x-medium
            width="270px"
            height="40px"
            >Edit personal information</v-btn
          >
          <v-btn
            v-on:click="changePassword"
            color="info"
            class="mt-2 ml-10 p-5 mb-5"
            x-medium
            width="240px"
            height="40px"
            >Change password</v-btn
          >
        </v-card-actions>
      </v-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "ClientProfile",
  data: () => ({
    opacity: 0.9,
    clientId: "",
    firstName: "",
    lastName: "",
    phoneNumber: "",
    street: "",
    streetNumber: "",
    city: "",
    country: "",
    email: "",
    client: null,
  }),
  mounted() {
    this.viewPersonalInfo();
  },
  methods: {
    viewPersonalInfo() {
      this.clientId = localStorage.getItem("userId");
      console.log(this.clientId);
      this.axios
        .get("http://localhost:8091/client/" + this.clientId, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          console.log(response.data);
          this.client = response.data;
          this.firstName = this.client.firstName;
          this.lastName = this.client.lastName;
          this.phoneNumber = this.client.phoneNumber;
          this.street = this.client.address.street;
          this.streetNumber = this.client.address.streetNumber;
          this.city = this.client.address.city;
          this.country = this.client.address.country;
          this.email = this.client.email;
        })
        .catch((err) => console.log(err));
    },
    editPersonalInfo() {
      window.location.href = "http://localhost:8083/editClientPersonalInfo";
    },
    changePassword() {
      window.location.href = "http://localhost:8083/changePassword";
    },
  },
};
</script>

<style scoped>
#myPersonalInformation {
  margin-top: 2%;
  margin-bottom: 2%;
  color: #007acc;
  text-align: center;
  font-weight: bold;
}
#personalInfoCard {
  width: 45%;
  margin: auto;
}
</style>