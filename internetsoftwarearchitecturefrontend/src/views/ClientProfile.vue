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
            class="mt-2 mr-1 p-5 mb-5"
            x-medium
            width="250px"
            height="40px"
            >Edit personal information</v-btn
          >
          <v-btn
            v-on:click="changePassword"
            color="info"
            class="mt-2 ml-5 p-5 mb-5"
            x-medium
            width="200px"
            height="40px"
            >Change password</v-btn
          >
          <v-dialog max-width="600" persistent>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                color="red darken-1"
                x-medium
                width="200px"
                height="40px"
                class="mt-2 ml-6 p-5 mb-5"
                v-bind="attrs"
                v-on="on"
                >Delete Account</v-btn
              >
            </template>
            <template v-slot:default="dialog">
              <v-card>
                <v-toolbar color="info" dark>Delete account request</v-toolbar>
                <v-spacer></v-spacer>
                <v-card-title class="text-h4 justify-center"
                  >Enter a reason</v-card-title
                >
                <v-card-text>
                  <v-spacer></v-spacer>
                  <v-text-field
                    class="ml-5 mr-5"
                    label="Reason"
                    v-model="reason"
                    color="info"
                    type="text"
                    :rules="[() => !!reason || 'This field is required']"
                  />
                  <v-spacer></v-spacer>
                </v-card-text>

                <v-spacer></v-spacer>

                <v-card-actions>
                  <v-spacer></v-spacer>

                  <v-btn color="green" text @click="sendRequest">Send</v-btn>
                  <v-spacer></v-spacer>

                  <v-btn
                    color="red"
                    text
                    @click="(reason = ''), (dialog.value = false)"
                    >Cancel</v-btn
                  >
                  <v-spacer></v-spacer>
                </v-card-actions>
              </v-card>
            </template>
          </v-dialog>
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
    firstName: "",
    lastName: "",
    phoneNumber: "",
    street: "",
    streetNumber: "",
    city: "",
    country: "",
    email: "",
    reason: "",
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
        .catch((err) => { 
            window.location.href = "http://localhost:8083/login";
            alert("401 Unauthorized - respected you are not logged in to the system.");
            console.log(err);
        });
    },
    editPersonalInfo() {
      window.location.href = "http://localhost:8083/editClientPersonalInfo";
    },
    changePassword() {
      window.location.href = "http://localhost:8083/changePassword";
    },
    sendRequest() {
      if(this.reason === "") {
        alert("Please enter a reason.");
        return;
      }
      this.$http
        .post(
          "http://localhost:8091/deleteAccount/client",
          {
            reason: this.reason,
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then(() => {
          alert("Successfully send delete account request!");
          window.location.href = "http://localhost:8083/";
        })
        .catch((err) => console.log(err));
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