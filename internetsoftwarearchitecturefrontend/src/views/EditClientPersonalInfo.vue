<template>
  <div>
    <h1 id="myPersonalInformation">Edit personal information</h1>
    <div class="pt-1">
      <v-card id="personalInfoCard" v-bind:style="{ opacity: opacity }">
        <v-card-text>
          <v-form class="mx-auto mt-5 mb-5 mr-10 ml-10">
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="First name"
                v-model="client.firstName"
                color="blue"
                type="text"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Last name"
                v-model="client.lastName"
                color="blue"
                type="text"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Phone number"
                v-model="client.phoneNumber"
                color="blue"
                type="text"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Street"
                v-model="client.address.street"
                color="blue"
                type="text"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Street number"
                v-model="client.address.streetNumber"
                color="blue"
                type="text"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="City"
                v-model="client.address.city"
                color="blue"
                type="text"
              />
            </v-row>
            <v-row class="countryCombo">
              <v-autocomplete
                ref="client.address.country"
                v-model="client.address.country"
                :items="countries"
                label="Country"
                placeholder="Select..."
              />
            </v-row>
          </v-form>
        </v-card-text>
        <v-card-actions class="justify-center">
          <v-btn
            v-on:click="save"
            color="info"
            class="mt-2 mr-10 p-5 mb-5"
            x-medium
            width="270px"
            height="40px"
            >Save</v-btn
          >
          <v-btn
            v-on:click="cancel"
            color="info"
            class="mt-2 ml-10 p-5 mb-5"
            x-medium
            width="240px"
            height="40px"
            >Cancel</v-btn
          >
        </v-card-actions>
      </v-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "EditClientPersonalInfo",
  data: () => ({
    countries: ["Serbia"],
    client: null,
  }),
  mounted() {
    this.viewPersonalInfo();
  },
  methods: {
    viewPersonalInfo() {
      this.axios
        .get("http://localhost:8091/client", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          this.client = response.data;
        })
        .catch((err) => { 
            window.location.href = "http://localhost:8083/login";
            alert("401 Unauthorized - respected you are not logged in to the system.");
            console.log(err);
            
        });
    },
    save() {
      if (
        !this.validateFirstName() ||
        !this.validateLastName() ||
        !this.validateStreet() ||
        !this.validateStreetNumber() ||
        !this.validateCity() ||
        !this.validateCountry() ||
        !this.validatePhoneNumber()
      )
        return;

      this.axios.post("http://localhost:8091/client/update", this.client, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      });
      alert("Successfully modified personal information!");
      window.location.href = "/clientProfile";
    },
    cancel() {
      alert("Canceled change personal information!");
      window.location.href = "/";
    },
    validateFirstName() {
      if (this.client.firstName.length < 2) {
        alert("Your first name should contain at least 2 characters!");
        return false;
      } else if (this.client.firstName.length > 30) {
        alert("Your first name shouldn't contain more than 30 characters!");
        return false;
      } else if (this.client.firstName.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
        alert("Your first name shouldn't contain special characters.");
        return false;
      } else if (this.client.firstName.match(/[ ]/g)) {
        alert("Your first name shouldn't contain spaces!");
        return false;
      } else if (this.client.firstName.match(/\d/g)) {
        alert("Your first name shouldn't contain numbers!");
        return false;
      } else if (!/^[A-Z][a-z]+$/.test(this.client.firstName)) {
        alert("Your first name needs to have one upper letter at the start!");
        return false;
      }
      return true;
    },
    validateLastName() {
      if (this.client.lastName.length < 2) {
        alert("Your last name should contain at least 2 characters!");
        return false;
      } else if (this.client.lastName.length > 36) {
        alert("Your last name shouldn't contain more than 36 characters!");
        return false;
      } else if (this.client.lastName.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
        alert("Your last name shouldn't contain special characters.");
        return false;
      } else if (this.client.lastName.match(/\d/g)) {
        alert("Your last name shouldn't contain numbers!");
        return false;
      } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.client.lastName)) {
        alert("Your last name needs to have one upper letter at the start!");
        return false;
      }
      return true;
    },
    validateStreet() {
      if (this.client.address.street.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
        alert("Your street name shouldn't contain special characters.");
        return false;
      } else if (this.client.address.street.match(/\d/g)) {
        alert("Your street name shouldn't contain numbers!");
        return false;
      } else if (
        !/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.client.address.street)
      ) {
        alert("Your street name needs to have one upper letter at the start!");
        return false;
      }
      return true;
    },
    validateStreetNumber() {
      if (this.client.address.streetNumber.match(/[ ]/g)) {
        alert("Your street number shouldn't contain spaces!");
        return false;
      } else if (
        this.client.address.streetNumber.match(/[!@#$%^&*.,:'<>+/\\"]/g)
      ) {
        alert("Your street number shouldn't contain special characters.");
        return false;
      } else if (this.client.address.streetNumber.length < 1) {
        alert("Your street number should contain at least one number.");
        return false;
      }
      return true;
    },
    validateCity() {
      if (this.client.address.city.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
        alert("Your city name shouldn't contain special characters.");
        return false;
      } else if (this.client.address.city.match(/\d/g)) {
        alert("Your city name shouldn't contain numbers!");
        return false;
      } else if (
        !/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.client.address.city)
      ) {
        alert("Your city name needs to have one upper letter at the start!");
        return false;
      }
      return true;
    },
    validateCountry() {
      if (this.client.address.country === "") {
        alert("Please select your country.");
        return false;
      }
      if (this.client.address.country.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
        alert("Your country name shouldn't contain special characters.");
        return false;
      } else if (this.client.address.country.match(/\d/g)) {
        alert("Your country name shouldn't contain numbers!");
        return false;
      } else if (
        !/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.client.address.country)
      ) {
        alert("Your country name needs to have one upper letter at the start!");
        return false;
      }
      return true;
    },
    validatePhoneNumber() {
      if (this.client.phoneNumber.match(/[a-zA-Z]/g)) {
        alert("Your phone number shouldn't contain letters.");
        return false;
      } else if (this.client.phoneNumber.match(/[ ]/g)) {
        alert("Your phone number shouldn't contain spaces!");
        return false;
      } else if (
        !/^[+]*[(]{0,1}[0-9]{1,3}[)]{0,1}[-\\s./0-9]*$/.test(
          this.client.phoneNumber
        )
      ) {
        alert("Your phone number is not in right form!");
        return false;
      }
      return true;
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
.countryCombo {
  width: 93%;
  margin-left: 4%;
  cursor: pointer;
}
</style>