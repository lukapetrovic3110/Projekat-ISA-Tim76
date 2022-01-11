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
            class="mr-10 mt-2 p-5 mb-5"
            x-medium
            width="250px"
            height="40px"
            >Edit personal information</v-btn
          >
          <v-btn
            v-on:click="changePassword"
            color="info"
            class="ml-10 mt-2 p-5 mb-5"
            x-medium
            width="200px"
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
  name: "SystemAdministratorProfile",
  data: () => ({
    opacity: 0.9,
    firstName: "",
    lastName: "",
    phoneNumber: "",
    street: "",
    streetNumber: "",
    city: "",
    country: "",
    email: "",
    systemAdministrator: null,
  }),
  mounted() {
    this.viewPersonalInfo();
  },
  methods: {
    viewPersonalInfo() {
      this.axios
        .get("http://localhost:8091/admin", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          console.log(response.data);
          this.systemAdministrator = response.data;
          this.firstName = this.systemAdministrator.firstName;
          this.lastName = this.systemAdministrator.lastName;
          this.phoneNumber = this.systemAdministrator.phoneNumber;
          this.street = this.systemAdministrator.address.street;
          this.streetNumber = this.systemAdministrator.address.streetNumber;
          this.city = this.systemAdministrator.address.city;
          this.country = this.systemAdministrator.address.country;
          this.email = this.systemAdministrator.email;
        })
        .catch((err) => { 
            window.location.href = "http://localhost:8083/login";
            alert("401 Unauthorized - respected you are not logged in to the system.");
            console.log(err);
        });
    },
    editPersonalInfo() {
      // window.location.href = "http://localhost:8083/editSystemAdministratorPersonalInfo";
    },
    changePassword() {
      // window.location.href = "http://localhost:8083/changePassword";
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