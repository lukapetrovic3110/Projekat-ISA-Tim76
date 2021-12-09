<template>
  <div>
    <page-background />
    <div id="content">
      <v-card class="imageHolder">
        <v-card-title primary-title class="justify-center">
          <h3>Cottage</h3>
        </v-card-title>
        <v-img
          src="../assets/cottage.jpg"
          class="blue lighten-2 imageSize"
        ></v-img>
        <v-card-title primary-title class="text-center">
          <h4 class="description">
            If you are looking for an accommodation,<br />you will find a large
            selection of cottages here.
          </h4>
          <h4 class="description">Enjoy a healthy vacation!</h4>
        </v-card-title>
        <v-card-actions class="justify-center">
          <v-btn
            x-large
            text
            color="primary accent-3"
            class="actionButton"
            v-on:click="searchCottage"
          >
            Explore more
          </v-btn>
        </v-card-actions>
      </v-card>
      <v-card class="imageHolder">
        <v-card-title primary-title class="justify-center">
          <h3>Ship or boat</h3>
        </v-card-title>
        <v-img
          src="../assets/ship.jpg"
          class="blue lighten-2 imageSize"
        ></v-img>
        <v-card-title primary-title class="text-center">
          <h4 class="description">
            If you are looking for a ship or boat you are on the right place,
            you can rent a boat at affordable prices.
          </h4>
          <h4 class="description">Enjoyment is guaranteed!</h4>
        </v-card-title>
        <v-card-actions class="justify-center">
          <v-btn
            x-large
            text
            color="primary accent-3"
            class="actionButton"
            v-on:click="searchShip"
          >
            Explore more
          </v-btn>
        </v-card-actions>
      </v-card>
      <v-card class="imageHolder">
        <v-card-title primary-title class="justify-center">
          <h3>Fishing instructor</h3>
        </v-card-title>
        <v-img
          src="../assets/fishing_instructor.jpg"
          class="blue lighten-2 imageSize"
        ></v-img>
        <v-card-title primary-title class="text-center">
          <h4 class="description">
            If you are looking for a fishing instructor,<br />you can find great
            experts here.
          </h4>
          <h4 class="description">They will share their knowledge with you.</h4>
        </v-card-title>
        <v-card-actions class="justify-center">
          <v-btn
            x-large
            text
            color="primary accent-3"
            class="actionButton"
            v-on:click="searchFishingInstructor"
          >
            Explore more
          </v-btn>
        </v-card-actions>
      </v-card>
    </div>
  </div>
</template>

<script>
import PageBackground from "../components/PageBackground.vue";
export default {
  name: "Home",
  components: {
    PageBackground,
  },
  methods: {
    searchCottage() {
      window.location.href = "http://localhost:8083/searchCottage";
    },
    searchShip() {
      window.location.href = "http://localhost:8083/searchShip";
    },
    searchFishingInstructor() {
      window.location.href = "http://localhost:8083/searchFishingInstructor";
    },
  },
};
</script>

<style scoped>
#content {
  margin-top: 2%;
  margin-left: 2%;
  width: 96%;
  align-self: center;
}
.imageHolder {
  margin-left: 1%;
  width: 32%;
  align-self: center;
  float: left;
  border-style: solid;
  border-width: 3%;
  border-radius: 6%;
}
.imageSize {
  width: 100%;
  min-height: 300px;
  height: 510px;
  max-height: 530px;
  background-attachment: fixed;
  background-repeat: no-repeat;
}
.description {
  overflow-wrap: break-word;
  margin-left: 3%;
  width: 94%;
}
.actionButton {
  vertical-align: center;
  margin: 1%;
}
</style>
