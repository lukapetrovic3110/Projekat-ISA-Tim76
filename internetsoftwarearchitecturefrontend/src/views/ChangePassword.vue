<template>
  <div>
    <h1 id="changePasswordCaption">Change password</h1>
    <div class="pt-1">
      <v-card
        width="40%"
        class="mx-auto mt-3"
        v-bind:style="{ opacity: opacity }"
      >
        <v-card-text>
          <v-form class="mx-auto mt-5 mb-5 mr-10 ml-10">
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="E-mail"
                v-model="email"
                color="blue"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                :append-icon="showPassword1 ? 'mdi-eye' : 'mdi-eye-off'"
                :type="showPassword1 ? 'text' : 'password'"
                v-model="oldPass"
                :rules="[rules.required]"
                class="ml-10 mr-10"
                label="Old password"
                color="blue"
                @click:append="showPassword1 = !showPassword1"
              />
            </v-row>
            <v-row>
              <v-text-field
                :append-icon="showPassword2 ? 'mdi-eye' : 'mdi-eye-off'"
                :type="showPassword2 ? 'text' : 'password'"
                class="ml-10 mr-10"
                label="New password"
                v-model="newPass"
                color="blue"
                hint="At least 8 characters"
                :rules="[rules.required, rules.min]"
                @click:append="showPassword2 = !showPassword2"
              />
            </v-row>
            <v-row>
              <v-text-field
                :append-icon="showPassword3 ? 'mdi-eye' : 'mdi-eye-off'"
                :type="showPassword3 ? 'text' : 'password'"
                class="ml-10 mr-10"
                label="Rewrite new password"
                v-model="rewritePass"
                color="blue"
                hint="At least 8 characters"
                :rules="[rules.required, rules.min]"
                @click:append="showPassword3 = !showPassword3"
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
  name: "ChangePassword",
  data: () => ({
    opacity: 0.9,
    oldPass: "",
    newPass: "",
    rewritePass: "",
    email: "",
    showPassword1: false,
    showPassword2: false,
    showPassword3: false,
    user: null,
    rules: {
      required: (value) => !!value || "Required.",
      min: (v) => v.length >= 8 || "Min 8 characters",
    },
  }),
  mounted() {
    this.init();
  },
  methods: {
    init() {
      this.axios
      .get("http://localhost:8091/auth", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      })
      .then((response) => {
        this.user = response.data;
        this.email = response.data.email;
      })
      .catch((err) => {
        window.location.href = "http://localhost:8083/login";
        alert("401 Unauthorized - respected you are not logged in to the system.");
        console.log(err);
      });
    },
    save() {
      if (
        this.oldPass == "" ||
        this.newPass == "" ||
        this.rewritePassword == ""
      ) {
        alert("Please fill all the required fields!");
      } else if (this.oldPass === this.newPass) {
        alert("Password can not be the same as the old one!");
      } else if (this.rewritePass !== this.newPass) {
        alert("New password and rewrite password must be the same!");
      } else {
        const passwordChanger = {
          oldPassword: this.oldPass,
          newPassword: this.newPass,
          email: this.email,
          rewritePassword: this.rewritePass,
        };

        this.axios
          .post("http://localhost:8091/auth/changePassword", passwordChanger, {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          })
          .then((resp) => {
            alert("Successfully changed password!");
            console.log(resp.data);
            this.logOff();
          })
          .catch((err) => { 
            console.log(err);
        });
      }
    },
    logOff() {
      alert("Please log in with your new password.");
      localStorage.setItem("token", "");
      localStorage.setItem("token", "");
      localStorage.setItem("userId", "");
      localStorage.setItem("email", "");
      localStorage.setItem("userType", null);
      window.location.href = "http://localhost:8083/login";
    },
    cancel() {
      window.location.href = "/";
    },
  },
};
</script>

<style scoped>
#changePasswordCaption {
  margin-top: 2%;
  margin-bottom: 2%;
  text-align: center;
  color: #007acc;
  font-weight: bold;
}
#personal_info {
  text-align: center;
}
th {
  text-align: justify;
  padding: 7px;
}
input[type="text"] {
  text-align: justify;
}
input[type="password"] {
  text-align: justify;
}
</style>