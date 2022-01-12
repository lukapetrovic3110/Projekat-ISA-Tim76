<template>
  <v-card style="margin-top: 3%" width="40%" class="mx-auto">
    <v-card-title class="justify-center">
      <h1 class="display-1 mt-20">Register as cottage owner</h1>
    </v-card-title>
    <v-card-text>
      <v-form class="mx-auto ml-20 mr-20">
        <v-text-field
          label="First Name"
          v-model="firstName"
          :rules="[() => !!firstName || 'This field is required']"
        />
        <v-text-field
          label="Last Name"
          v-model="lastName"
          :rules="[() => !!lastName || 'This field is required']"
        />
         <v-text-field
          label="Street Name"
          v-model="streetName"
          :rules="[() => !!streetName || 'This field is required']"
        />
         <v-text-field
          label="Street Number"
          v-model="streetNumber"
          :rules="[() => !!streetNumber || 'This field is required']"
        />
        <v-text-field
          label="City"
          v-model="city"
          :rules="[() => !!city || 'This field is required']"
        />
        <v-autocomplete
            class = "countryCombo"
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
        />
        <v-text-field
          label="E-mail"
          v-model="email"
          :rules="[() => !!email || 'This field is required']"
        />
        <v-text-field
          :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
          :type="showPassword ? 'text' : 'password'"
          label="Password"
          v-model="password"
          :rules="[() => !!password || 'This field is required']"
          hint="Password must contain minimum 8 characters, 1 uppercase, 1 lowercase and 1 number and 1 special character"
          @click:append="showPassword = !showPassword"
        />
        <v-text-field
          :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
          :type="showPassword ? 'text' : 'password'"
          label="Repeat Password"
          v-model="repeated_password"
          :rules="[() => !!repeated_password || 'This field is required', passwordConfirmationRule]"
          hint="Password must contain minimum 8 characters, 1 uppercase, 1 lowercase and 1 number and 1 special character"
          @click:append="showPassword = !showPassword"
        />
        <v-text-field
          label="Explanation"
          v-model="explanation"
          :rules="[() => !!explanation || 'This field is required']"
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
        v-on:click="register">Register</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: "RegisterCottageOwner",
  data: () => ({
    countries: ['Serbia'],
    showPassword: false,
    email: "",
    password: "",
    firstName: "",
    lastName: "",
    streetName: "",
    streetNumber: "",
    city: "",
    country: "",
    phoneNumber: "",
    repeated_password: "",
    explanation: "",
  }),
  computed: {
    user() {
      return {  email: this.email, 
                password: this.password,
                firstName: this.firstName, 
                lastName: this.lastName,
                streetName: this.streetName, 
                city: this.city,
                country: this.country,
                phoneNumber: this.phoneNumber,
                repeated_password: this.repeated_password,
                explanation: this.explanation};
    },
    passwordConfirmationRule() {
      return () => (this.password === this.repeated_password) || 'Password must match.'
    }
  },
  methods: {
    register() {
      this.$http
        .post("http://localhost:8091/auth/signupCottageOwner", {
          email: this.email,
          password: this.password,
          firstName: this.firstName,
          lastName: this.lastName,

          address: {
            street: this.streetName,
            streetNumber: this.streetNumber,
            city: this.city,
            country: this.country
          },
          phoneNumber: this.phoneNumber,
          explanation: this.explanation,
        })
        .then(() => {
          window.location.href = "http://localhost:8083/login";
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
          this.explanation = "";
          console.log(er.response.data);
        });
    },
  },
};
</script>

<style scoped>
.countryCombo{
  cursor: pointer;
}
</style>