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
                v-model="shipOwner.firstName"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Last name"
                v-model="shipOwner.lastName"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Phone number"
                v-model="shipOwner.phoneNumber"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Street"
                v-model="shipOwner.address.street"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Street number"
                v-model="shipOwner.address.streetNumber"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="City"
                v-model="shipOwner.address.city"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Country"
                v-model="shipOwner.address.country"
                color="blue"
                type="text"
                v-bind:readonly="true"
              />
            </v-row>
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="E-mail"
                v-model="shipOwner.email"
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
          <v-dialog max-width="60%" persistent>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                color="error"
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
                <v-toolbar color="info" dark x-medium
                  >Delete account request</v-toolbar
                >
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
                    hint="Please enter a reason (maximum 100 characters)"
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
  name: "ShipOwnerProfile",

  data: () => ({
    opacity: 0.9,
    shipOwner: null,
    reason: "",
  }),

  mounted() {
    this.viewPersonalInfo();
  },

  methods: {
    viewPersonalInfo() {
      this.axios
        .get("http://localhost:8091/shipOwner", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          console.log(response.data);
          this.shipOwner = response.data;
        })
        .catch((err) => {
          window.location.href = "http://localhost:8083/login";
          alert(
            "401 Unauthorized - respected you are not logged in to the system."
          );
          console.log(err);
        });
    },

    editPersonalInfo() {
      window.location.href = "http://localhost:8083/editShipOwnerPersonalInfo";
    },

    changePassword() {
      window.location.href = "http://localhost:8083/changePassword";
    },

    onlySpaces(str) {
      return str.trim().length === 0;
    },

    validateReason() {
      if (this.onlySpaces(this.reason)) {
        alert("Please enter a reason!");
        return false;
      } else if (this.reason.length > 100) {
        alert("The reason is long please enter up to 100 characters!");
        return false;
      } else if (this.reason.match(/[\\#$%^&*'<>/"]/g)) {
        alert("The reason shouldn't contain special characters.");
        return false;
      }
      return true;
    },

    sendRequest() {
      if (!this.validateReason(this.reason)) return;
      this.$http
        .post(
          "http://localhost:8091/deleteAccount/owner",
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
  width: 55%;
  margin: auto;
}
</style>