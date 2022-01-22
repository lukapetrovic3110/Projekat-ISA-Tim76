<template>
  <v-card style="margin-top: 3%" width="40%" class="mx-auto">
    <v-card-title class="justify-center">
      <h1 class="display-1 mt-20">Cottage information</h1>
    </v-card-title>
    <v-card-text>
      <v-form class="mx-auto ml-20 mr-20">
        <v-text-field
          label="Name"
          v-model="cottageInformation.name"
          type="text"
          v-bind:readonly="true"
        />
        <v-text-field
          label="Street Name"
          v-model="cottageInformation.address.street"
          v-bind:readonly="true"
        />
        <v-text-field
          label="Street Number"
          v-model="cottageInformation.address.streetNumber"
          v-bind:readonly="true"
        />
        <v-text-field
          label="City"
          v-model="cottageInformation.address.city"
          v-bind:readonly="true"
        />
        <v-text-field
          label="Country"
          v-model="cottageInformation.address.country"
          v-bind:readonly="true"
        />
        <v-text-field
          label="Description"
          v-model="cottageInformation.description"
          v-bind:readonly="true"
        />
        <v-text-field
          label="Number of rooms"
          v-model="cottageInformation.numberOfRooms"
          v-bind:readonly="true"
        />
        <v-text-field
          label="Number of beds per room"
          v-model="cottageInformation.numberOfBedsPerRoom"
          v-bind:readonly="true"
        />
        <v-text-field
          label="Price per day (RSD)"
          v-model="cottageInformation.pricePerDay"
          type="text"
          v-bind:readonly="true"
        />
        <v-text-field
          label="Availability start"
          v-model="cottageInformation.availabilityStart"
          v-bind:readonly="true"
        />
        <v-text-field
          label="Availability end"
          v-model="cottageInformation.availabilityEnd"
          v-bind:readonly="true"
        />
        <v-card>
          <v-card-title style="color: gray">
            Cottage rules
            <v-spacer></v-spacer>
            <v-text-field
              v-model="searchCottageRule"
              append-icon="mdi-magnify"
              label="Search"
              single-line
              hide-details
            ></v-text-field>
          </v-card-title>
          <v-data-table
            :headers="headersCottageRule"
            :items="cottageInformation.cottageRules"
            :search="searchCottageRule"
          ></v-data-table>
        </v-card>
        <v-card>
          <v-card-title style="color: gray">
            Price list
            <v-spacer></v-spacer>
            <v-text-field
              v-model="searchCottagePriceTag"
              append-icon="mdi-magnify"
              label="Search"
              single-line
              hide-details
            ></v-text-field>
          </v-card-title>
          <v-data-table
            :headers="headersCottagePriceTag"
            :items="cottageInformation.priceList.priceTags"
            :search="searchCottagePriceTag"
          ></v-data-table>
        </v-card>
        <div style="display: flex; justify-content: center; margin-top: 7%">
          <vue-upload-multiple-image
            @mark-is-primary="markIsPrimary"
            :data-images="cottageImages"
            idUpload="myIdUpload"
            idEdit="myIdEdit"
            :max-image="15"
            primary-text="Default"
            browse-text="Upload images"
            drag-text="Drag images"
            mark-is-primary-text="Set as default"
            popup-text="This image will be displayed as default."
            :multiple="true"
            :show-edit="false"
            :show-delete="false"
            :show-add="false" 
          ></vue-upload-multiple-image>
        </div>
      </v-form>
    </v-card-text>
    <div></div>
    <v-dialog v-model="dialogDeleteCottage" max-width="60%">
      <v-card>
        <v-spacer></v-spacer>
        <v-card-title class="text-h5 justify-center"
          >Are you sure you want to delete this cottage?
        </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green" text @click="deleteCottage">Delete</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="red" text @click="dialogDeleteCottage = false">Cancel</v-btn>
          <v-spacer></v-spacer>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-card-actions>
      <v-btn
        class="mx-auto mb-10 mt-10; color:white"
        color="info"
        elevation="2"
        x-large
        raised
        @click="editCottageInformation"
        >Edit cottage information</v-btn
      >
      <v-btn
        class="mx-auto mb-10 mt-10; color:white"
        color="error"
        elevation="2"
        x-large
        raised
        @click="dialogDeleteCottage = true"
        >Delete cottage</v-btn
      >
    </v-card-actions>
  </v-card>
</template>

<script>
import VueUploadMultipleImage from "vue-upload-multiple-image";

export default {
  name: "CottageProfile",
  components: {
    VueUploadMultipleImage,
  },

  data: () => ({
    cottageId: "",
    cottageInformation: null,
    cottageImages: [],
    images: null,
    searchCottageRule: "",
    searchCottagePriceTag: "",
    dialogDeleteCottage: false,
    errorMessages: "",
    headersCottageRule: [
      {
        text: "Rule",
        align: "start",
        sortable: false,
        value: "description",
      },
    ],
    headersCottagePriceTag: [
      {
        text: "Service description",
        align: "start",
        sortable: false,
        value: "serviceName",
      },
      {
        text: "Price",
        value: "price",
      },
    ],
  }),

  mounted() {
    this.getCottageInformation();
  },

  methods: {
    getCottageInformation() {
      this.cottageId = localStorage.getItem("cottageId");
      this.axios
        .get("http://localhost:8091/cottage/" + this.cottageId, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          this.cottageInformation = response.data;
          this.cottageInformation.availabilityStart = new Date(
            response.data.availabilityStart
          ).toLocaleString();
          this.cottageInformation.availabilityEnd = new Date(
            response.data.availabilityEnd
          ).toLocaleString();
          console.log("COTTAGE INFORMATION")
          console.log(this.cottageInformation);
          this.getCottageImages();
        })
        .catch((err) => console.log(err));
    },

    getCottageImages() {
      this.axios
        .get("http://localhost:8091/image/getImages/cottage/" + this.cottageId, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          this.images = response.data;
          this.images.images.forEach(image => {
            this.cottageImages.push({
              default: image.defaultImage,
              highlight: image.highlight,
              name: image.name + ".jpg",
              path: image.path,
            });
          });
          console.log("COTTAGE IMAGES");
          console.log(this.cottageImages);
        })
        .catch((err) => console.log(err));
    },

    deleteCottage() {
      this.axios
        .post(
          "http://localhost:8091/cottage/delete",
          {
            cottageId: this.cottageId,
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          this.dialogDeleteCottage = false;
          window.location.href = "http://localhost:8083/myCottages";
        });
    },

    markIsPrimary(index, fileList) {
      console.log("markIsPrimary data", index, fileList);
    },

    editCottageInformation() {
      window.location.href = "http://localhost:8083/editCottageInformation";
    },
  },
};
</script>

<style scoped>
</style>