<template>
  <v-card style="margin-top: 10%" width="30%" class="mx-auto">
    <v-card-title class="justify-center">
      <h1 class="display-1 mt-20">Login</h1>
    </v-card-title>
    <v-card-text>
      <v-form class="mx-auto ml-20 mr-20">
        <v-text-field
          label="E-mail"
          v-model="email"
          :rules="[() => !!email || 'This field is required']"
          prepend-icon="mdi-account-circle"
        />
        <v-text-field
          :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
          :type="showPassword ? 'text' : 'password'"
          label="Password"
          v-model="password"
          :rules="[() => !!password || 'This field is required']"
          hint="At least 8 characters"
          prepend-icon="mdi-lock"
          @click:append="showPassword = !showPassword"
        ></v-text-field>
      </v-form>
    </v-card-text>
    <div></div>
    <v-card-actions>
      <v-btn
        class="mx-auto mb-5; color:white"
        color="info"
        elevation="3"
        x-large
        raised
        v-on:click="login"
        >Log in</v-btn
      >
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: "Login",
  data: () => ({
    showPassword: false,
    email: "",
    password: "",
  }),
  computed: {
    user() {
      return { email: this.email, password: this.password };
    },
  },
  methods: {
    login() {
      if (this.email === "" || this.password === "") {
        alert("Please fill in all fields!");
      } else { 
      this.$http
        .post("http://localhost:8091/auth/login", {
          email: this.email,
          password: this.password,
        })
        .then((response) => {
          console.log(response.data);
          localStorage.setItem("email", this.user.email);
          localStorage.setItem("token", response.data.accessToken);
          localStorage.setItem("userId", response.data.user.userId);
          localStorage.setItem("userType", response.data.user.userType);

          if (!response.data.user.firstLoginChangePassword && response.data.user.userType === 'SYSTEM_ADMINISTRATOR')
            window.location.href = "/changePasswordFirstLogin";
          else 
            window.location.href = "/";

        })
        .catch((error) => {
          alert("Invalid email and/or password! Please, try again!");
          this.email = "";
          this.password = "";
          console.log(error.response.data);
        });
      }
    },
  },
};
</script>