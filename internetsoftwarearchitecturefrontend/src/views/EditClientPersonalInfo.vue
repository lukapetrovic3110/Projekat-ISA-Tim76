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
            <v-row>
              <v-text-field
                class="ml-10 mr-10"
                label="Country"
                v-model="client.address.country"
                color="blue"
                type="text"
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
      client: null,
      clientId: "",
  }),
  mounted() {
    this.viewPersonalInfo();
  },
  methods: {
    viewPersonalInfo() {
      this.clientId = localStorage.getItem("userId");

      this.axios
        .get("http://localhost:8091/client/" + this.clientId, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          this.client = response.data;
        })
        .catch((err) => console.log(err));
    }, save() {
        this.axios
        .post("http://localhost:8091/client/update", this.client, {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token"),
            }
        });
        alert("Successfully modified personal information!");
        window.location.href = "/clientProfile";
    }, cancel() {
        alert("Canceled change personal information!");
        window.location.href = "/";
    }
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
  width: 40%;
  margin: auto;
}
</style>