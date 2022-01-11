<template>
  <v-card style="margin-top: 3%" width="40%" class="mx-auto">
    <v-card-title class="justify-center">
      <h1 class="display-1 mt-20">Register new system administrator</h1>
    </v-card-title>
    <v-card-text>
      <v-form class="mx-auto ml-20 mr-20" ref="form">
        <v-text-field
          label="First Name"
          v-model="firstName"
          :rules="[() => !!firstName || 'This field is required']"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="Last Name"
          v-model="lastName"
          :rules="[() => !!lastName || 'This field is required']"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="Street Name"
          v-model="streetName"
          :rules="[() => !!streetName || 'This field is required']"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="Street Number"
          v-model="streetNumber"
          :rules="[() => !!streetNumber || 'This field is required']"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="City"
          v-model="city"
          :rules="[() => !!city || 'This field is required']"
          :error-messages="errorMessages"
        />
        <v-autocomplete
          class="countryCombo"
          ref="country"
          v-model="country"
          :rules="[() => !!country || 'This field is required']"
          :items="countries"
          label="Country"
          placeholder="Select..."
        />
        <v-text-field
          label="Phone Number"
          v-model="phoneNumber"
          :rules="[() => !!phoneNumber || 'This field is required']"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="E-mail"
          v-model="email"
          :rules="[() => !!email || 'This field is required']"
          :error-messages="errorMessages"
        />
        <v-text-field
          :append-icon="showPassword1 ? 'mdi-eye' : 'mdi-eye-off'"
          :type="showPassword1 ? 'text' : 'password'"
          label="Password"
          v-model="password"
          :rules="[() => !!password || 'This field is required']"
          :error-messages="errorMessages"
          hint="Password must contain minimum 8 characters, 1 uppercase, 1 lowercase and 1 number and 1 special character."
          @click:append="showPassword1 = !showPassword1"
        />
        <v-text-field
          :append-icon="showPassword2 ? 'mdi-eye' : 'mdi-eye-off'"
          :type="showPassword2 ? 'text' : 'password'"
          label="Repeat Password"
          v-model="repeatedPassword"
          :rules="[
            () => !!repeatedPassword || 'This field is required',
            passwordConfirmationRule,
          ]"
          :error-messages="errorMessages"
          hint="Password must contain minimum 8 characters, 1 uppercase, 1 lowercase and 1 number and 1 special character."
          @click:append="showPassword2 = !showPassword2"
        />
      </v-form>
    </v-card-text>
    <div></div>
    <v-card-actions>
      <v-btn
        class="mx-auto mb-10 mt-10; color:white"
        color="primary"
        elevation="2"
        x-large
        raised
        v-on:click="register"
        >Register</v-btn
      >
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: "RegisterSystemAdministrator",
  data: () => ({
    countries: ["Serbia"],
    errorMessages: "",
    showPassword1: false,
    showPassword2: false,
    email: "",
    password: "",
    firstName: "",
    lastName: "",
    streetName: "",
    streetNumber: "",
    city: "",
    country: "",
    phoneNumber: "",
    repeatedPassword: "",
  }),
  mounted(){
    this.init();
  },
  computed: {
    user() {
      return {
        email: this.email,
        password: this.password,
        firstName: this.firstName,
        lastName: this.lastName,
        streetName: this.streetName,
        city: this.city,
        country: this.country,
        phoneNumber: this.phoneNumber,
        repeatedPassword: this.repeatedPassword,
      };
    },
    passwordConfirmationRule() {
      return () =>
        this.password === this.repeatedPassword || "Password must match.";
    },
  },
  methods: {
    init() {
        this.axios
        .get("http://localhost:8091/admin", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          this.client = response.data;
        })
        .catch((err) => { 
            alert("401 Unauthorized - respected you are not logged as system administrator.");
            window.location.href = "http://localhost:8083/login";
            console.log(err);
        });
    },
    register() {
      if (
        !this.validateFirstName() ||
        !this.validateLastName() ||
        !this.validateStreet() ||
        !this.validateStreetNumber() ||
        !this.validateCity() ||
        !this.validateCountry() ||
        !this.validatePhoneNumber() ||
        !this.validateEmail() ||
        !this.validatePassword()
      )
        return;

      this.$http
        .post("http://localhost:8091/auth/registerSystemAdministrator", {
          email: this.email,
          password: this.password,
          firstName: this.firstName,
          lastName: this.lastName,

          address: {
            street: this.streetName,
            streetNumber: this.streetNumber,
            city: this.city,
            country: this.country,
          },
          phoneNumber: this.phoneNumber,
        },
        {
            headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
            },
        })
        .then(() => {
          window.location.href = "/";
        })
        .catch((er) => {
          alert("Email already exists!");
          this.email = "";
          this.password = "";
          this.firstName = "";
          this.lastName = "";
          this.streetName = "";
          this.streetNumber = "";
          this.city = "";
          this.country = "";
          this.phoneNumber = "";
          console.log(er.response.data);
        });
    },
    validateFirstName() {
      if (this.firstName.length < 2) {
        alert("Your first name should contain at least 2 characters!");
        return false;
      } else if (this.firstName.length > 30) {
        alert("Your first name shouldn't contain more than 30 characters!");
        return false;
      } else if (this.firstName.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
        alert("Your first name shouldn't contain special characters.");
        return false;
      } else if (this.firstName.match(/[ ]/g)) {
        alert("Your first name shouldn't contain spaces!");
        return false;
      } else if (this.firstName.match(/\d/g)) {
        alert("Your first name shouldn't contain numbers!");
        return false;
      } else if (!/^[A-Z][a-z]+$/.test(this.firstName)) {
        alert("Your first name needs to have one upper letter at the start!");
        return false;
      }
      return true;
    },
    validateLastName() {
      if (this.lastName.length < 2) {
        alert("Your last name should contain at least 2 characters!");
        return false;
      } else if (this.lastName.length > 36) {
        alert("Your last name shouldn't contain more than 36 characters!");
        return false;
      } else if (this.lastName.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
        alert("Your last name shouldn't contain special characters.");
        return false;
      } else if (this.lastName.match(/\d/g)) {
        alert("Your last name shouldn't contain numbers!");
        return false;
      } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.lastName)) {
        alert("Your last name needs to have one upper letter at the start!");
        return false;
      }
      return true;
    },
    validateStreet() {
      if (this.streetName.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
        alert("Your street name shouldn't contain special characters.");
        return false;
      } else if (this.streetName.match(/\d/g)) {
        alert("Your street name shouldn't contain numbers!");
        return false;
      } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.streetName)) {
        alert("Your street name needs to have one upper letter at the start!");
        return false;
      }
      return true;
    },
    validateStreetNumber() {
      if (this.streetNumber.match(/[ ]/g)) {
        alert("Your street number shouldn't contain spaces!");
        return false;
      } else if (this.streetNumber.match(/[!@#$%^&*.,:'<>+/\\"]/g)) {
        alert("Your street number shouldn't contain special characters.");
        return false;
      } else if (this.streetNumber.length < 1) {
        alert("Your street number should contain at least one number.");
        return false;
      }
      return true;
    },
    validateCity() {
      if (this.city.match(/[!@#$%^&*.,:'<>+-/\\"]/g)) {
        alert("Your city name shouldn't contain special characters.");
        return false;
      } else if (this.city.match(/\d/g)) {
        alert("Your city name shouldn't contain numbers!");
        return false;
      } else if (!/^[A-Z][a-z]+[ ]?[A-Z]*[a-z]*$/.test(this.city)) {
        alert("Your city name needs to have one upper letter at the start!");
        return false;
      }
      return true;
    },
    validateCountry() {
      if (this.country === "") {
        alert("Please select your country.");
        return false;
      }
      return true;
    },
    validatePhoneNumber() {
      if (this.phoneNumber.match(/[a-zA-Z]/g)) {
        alert("Your phone number shouldn't contain letters.");
        return false;
      } else if (this.phoneNumber.match(/[ ]/g)) {
        alert("Your phone number shouldn't contain spaces!");
        return false;
      } else if (
        !/^[+]*[(]{0,1}[0-9]{1,3}[)]{0,1}[-\\s./0-9]*$/.test(this.phoneNumber)
      ) {
        alert("Your phone number is not in right form!");
        return false;
      }
      return true;
    },
    validateEmail() {
      if (this.email == "") {
         alert("Please enter your email.");
        return false;
      } else if (
        !/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(this.email)
      ) {
        alert("You have entered an invalid email!");
        return false;
      }
      return true;
    },
    validatePassword() {
      if (this.password.length < 8) {
        alert("Your password should contain at least 8 character!");
        return false;
      } else if (this.password.length > 25) {
        alert("Your password shouldn't contain more than 25 characters!");
        return false;
      } else if (!this.password.match(/[a-z]/g)) {
        this.repeatedPassword = "";
        alert("Your password should contain at least one small letter.");
        return false;
      } else if (!this.password.match(/[A-Z]/g)) {
        this.repeatedPassword = "";
        alert("Your password should contain at least one big letter.");
        return false;
      } else if (!this.password.match(/[0-9]/g)) {
        this.repeatedPassword = "";
        alert("Your password should contain at least one number.");
        return false;
      } else if (!this.password.match(/[!@#$%^&*.,:'+-/\\"]/g)) {
        alert(
          "Your password should contain at least one special character (other than <>)."
        );
        return false;
      } else if (this.password.match(/[<>]/g)) {
        alert("Your password shouldn't contain special character < or >.");
        return false;
      } else if (this.password.match(/[ ]/g)) {
        alert("Your password shouldn't contain spaces!");
        return false;
      } else if (this.password !== this.repeatedPassword) {
        this.repeatedPassword = "";
        return false;
      }
      return true;
    },
  },
};
</script>

<style scoped>
.countryCombo {
  cursor: pointer;
}
</style>