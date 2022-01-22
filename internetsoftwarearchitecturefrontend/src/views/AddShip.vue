<template>
  <v-card style="margin-top: 3%" width="40%" class="mx-auto">
    <v-card-title class="justify-center">
      <h1 class="display-1 mt-20">Register new ship</h1>
    </v-card-title>
    <v-card-text>
      <v-form class="mx-auto ml-20 mr-20">
        <v-text-field
          label="Name"
          v-model="shipInformation.name"
          :rules="[() => !!shipInformation.name || 'This field is required']"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="Street Name"
          v-model="shipInformation.address.street"
          :rules="[
            () =>
              !!shipInformation.address.street || 'This field is required',
          ]"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="Street Number"
          v-model="shipInformation.address.streetNumber"
          :rules="[
            () =>
              !!shipInformation.address.streetNumber ||
              'This field is required',
          ]"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="City"
          v-model="shipInformation.address.city"
          :rules="[
            () => !!shipInformation.address.city || 'This field is required',
          ]"
          :error-messages="errorMessages"
        />
        <v-autocomplete
          class="countryCombo"
          ref="country"
          v-model="shipInformation.address.country"
          :rules="[
            () =>
              !!shipInformation.address.country || 'This field is required',
          ]"
          :items="countries"
          label="Country"
          placeholder="Select..."
        />
        <v-text-field
          label="Ship length"
          v-model="shipInformation.shipLength"
          :rules="[() => !!shipInformation.shipLength || 'This field is required']"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="Engine number"
          v-model="shipInformation.engineNumber"
          :rules="[() => !!shipInformation.engineNumber || 'This field is required']"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="Engine power"
          v-model="shipInformation.enginePower"
          :rules="[() => !!shipInformation.enginePower || 'This field is required']"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="Max speed"
          v-model="shipInformation.maxSpeed"
          :rules="[() => !!shipInformation.maxSpeed || 'This field is required']"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="Capacity"
          v-model="shipInformation.capacity"
          :rules="[() => !!shipInformation.capacity || 'This field is required']"
          :error-messages="errorMessages"
        />
        <v-autocomplete
          class="shipCombo"
          ref="shipType"
          v-model="shipInformation.shipType"
          :rules="[
            () =>
              !!shipInformation.shipType || 'This field is required',
          ]"
          :items="shipTypes"
          label="Ship type"
          placeholder="Select..."
        />
        <v-card-text>
          <h2 class="text-h6 mb-2">Ship navigation equipment</h2>
          <v-chip-group
            v-model="shipInformation.shipNavigationEquipment"
            column
            multiple
          >
            <v-chip filter outlined> GPS </v-chip>
            <v-chip filter outlined> Radar </v-chip>
            <v-chip filter outlined> VHF radio </v-chip>
            <v-chip filter outlined> Fishfinder </v-chip>
          </v-chip-group>
        </v-card-text>
        <v-text-field
          label="Description"
          v-model="shipInformation.description"
          :rules="[
            () => !!shipInformation.description || 'This field is required',
          ]"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="Price per hour"
          v-model="shipInformation.pricePerHour"
          :rules="[
            () => !!shipInformation.pricePerHour || 'This field is required',
          ]"
          :error-messages="errorMessages"
        />
       <v-autocomplete
          class="shipReservationCancelTermCombo"
          ref="shipReservationCancelTerm"
          v-model="shipInformation.shipReservationCancelTerm"
          :rules="[
            () =>
              !!shipInformation.shipReservationCancelTerm || 'This field is required',
          ]"
          :items="shipReservationCancelTerms"
          label="Ship reservation cancel term"
          placeholder="Select..."
        />
        <v-simple-table>
          <tr>
            <td>
              <v-menu
                v-model="availabilityStartDateMenu"
                :close-on-content-click="false"
                :nudge-right="40"
                transition="scale-transition"
                min-width="auto"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="availabilityStartDate"
                    label="Availability start date"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-model="availabilityStartDate"
                  @input="availabilityStartDateMenu = false"
                ></v-date-picker>
              </v-menu>
              <v-spacer></v-spacer>
            </td>
            <td>
              <v-menu
                ref="availabilityStartTimeMenu"
                v-model="availabilityStartTimeMenu"
                :close-on-content-click="false"
                :nudge-right="40"
                transition="scale-transition"
                max-width="290px"
                min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="availabilityStartTime"
                    label="Availability start time"
                    prepend-icon="mdi-clock-time-four-outline"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-time-picker
                  v-model="availabilityStartTime"
                  full-width
                  @click:minute="
                    $refs.availabilityStartTimeMenu.save(availabilityStartTime)
                  "
                ></v-time-picker>
              </v-menu>
            </td>
          </tr>
        </v-simple-table>
        <v-simple-table>
          <tr>
            <td>
              <v-menu
                v-model="availabilityEndDateMenu"
                :close-on-content-click="false"
                :nudge-right="40"
                transition="scale-transition"
                min-width="auto"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="availabilityEndDate"
                    label="Availability end date"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-model="availabilityEndDate"
                  @input="availabilityEndDateMenu = false"
                ></v-date-picker>
              </v-menu>
              <v-spacer></v-spacer>
            </td>
            <td>
              <v-menu
                ref="availabilityEndTimeMenu"
                v-model="availabilityEndTimeMenu"
                :close-on-content-click="false"
                :nudge-right="40"
                transition="scale-transition"
                max-width="290px"
                min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="availabilityEndTime"
                    label="Availability end time"
                    prepend-icon="mdi-clock-time-four-outline"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-time-picker
                  v-model="availabilityEndTime"
                  full-width
                  @click:minute="
                    $refs.availabilityEndTimeMenu.save(availabilityEndTime)
                  "
                ></v-time-picker>
              </v-menu>
            </td>
          </tr>
        </v-simple-table>
        <v-data-table
          :headers="headersShipRule"
          :items="shipRules"
          hide-default-footer
          no-data-text="Rules are not added"
          class="elevation-1"
        >
          <template v-slot:top>
            <v-toolbar flat>
              <v-toolbar-title style="color: gray"
                >Ship rules</v-toolbar-title
              >
              <v-divider class="mx-4" inset vertical></v-divider>
              <v-spacer></v-spacer>
              <v-dialog v-model="dialogShipRule" max-width="500px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="purple"
                    dark
                    class="mb-2"
                    v-bind="attrs"
                    v-on="on"
                  >
                    New rule
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span class="text-h5">{{ formTitleShipRule }}</span>
                  </v-card-title>

                  <v-card-text>
                    <v-container>
                      <v-text-field
                        v-model="editedItemShipRule.shipRuleName"
                        label="Rule description"
                      ></v-text-field>
                    </v-container>
                  </v-card-text>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" text @click="closeShipRule">
                      Cancel
                    </v-btn>
                    <v-btn color="blue darken-1" text @click="saveShipRule">
                      Save
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
              <v-dialog v-model="dialogShipRuleDelete" max-width="500px">
                <v-card>
                  <v-card-title class="text-h6"
                    >Are you sure you want to delete this rule?</v-card-title
                  >
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="closeShipRuleDelete"
                      >Cancel</v-btn
                    >
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="deleteShipRuleItemConfirm"
                      >OK</v-btn
                    >
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.actions`]="{ item }">
            <v-icon small class="mr-2" @click="editShipRuleItem(item)">
              mdi-pencil
            </v-icon>
            <v-icon small @click="deleteShipRuleItem(item)">
              mdi-delete
            </v-icon>
          </template>
        </v-data-table>
        <v-data-table
          :headers="headersShipPriceTag"
          :items="shipPriceTags"
          hide-default-footer
          no-data-text="Price tags are not added"
          class="elevation-1"
        >
          <template v-slot:top>
            <v-toolbar flat>
              <v-toolbar-title style="color: gray">Price list</v-toolbar-title>
              <v-divider class="mx-4" inset vertical></v-divider>
              <v-spacer></v-spacer>
              <v-dialog v-model="dialogShipPriceTag" max-width="500px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="purple"
                    dark
                    class="mb-2"
                    v-bind="attrs"
                    v-on="on"
                  >
                    New price tag
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span class="text-h5">{{ formTitleShipPriceTag }}</span>
                  </v-card-title>

                  <v-card-text>
                    <v-container>
                      <v-text-field
                        v-model="editedItemShipPriceTag.serviceDescription"
                        label="Service description"
                      ></v-text-field>
                      <v-text-field
                        v-model="editedItemShipPriceTag.price"
                        label="Price"
                      ></v-text-field>
                    </v-container>
                  </v-card-text>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="closeShipPriceTag"
                    >
                      Cancel
                    </v-btn>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="saveShipPriceTag"
                    >
                      Save
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
              <v-dialog v-model="dialogShipPriceTagDelete" max-width="500px">
                <v-card>
                  <v-card-title class="text-h6"
                    >Are you sure you want to delete this price
                    tag?</v-card-title
                  >
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="closeShipPriceTagDelete"
                      >Cancel</v-btn
                    >
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="deleteShipPriceTagItemConfirm"
                      >OK</v-btn
                    >
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.actions`]="{ item }">
            <v-icon small class="mr-2" @click="editShipPriceTagItem(item)">
              mdi-pencil
            </v-icon>
            <v-icon small @click="deleteShipPriceTagItem(item)">
              mdi-delete
            </v-icon>
          </template>
        </v-data-table>
        <div style="display: flex; justify-content: center; margin-top: 7%">
          <vue-upload-multiple-image
            @upload-success="uploadImageSuccess"
            @edit-image="editImage"
            @mark-is-primary="markIsPrimary"
            @limit-exceeded="limitExceeded"
            @before-remove="beforeRemove"
            idUpload="myIdUpload"
            idEdit="myIdEdit"
            :max-image="15"
            primary-text="Default"
            browse-text="Upload images"
            drag-text="Drag images"
            mark-is-primary-text="Set as default"
            popup-text="This image will be displayed as default"
            :multiple="true"
            :show-edit="true"
            :show-delete="true"
            :show-add="true"
          ></vue-upload-multiple-image>
        </div>
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
        v-on:click="registerShip"
        >Register ship</v-btn
      >
    </v-card-actions>
  </v-card>
</template>

<script>
import VueUploadMultipleImage from "vue-upload-multiple-image";

export default {
  name: "AddShip",
  components: {
    VueUploadMultipleImage,
  },
  data: () => ({
    availabilityStartDateMenu: false,
    availabilityStartTimeMenu: false,
    availabilityStartDate: "",
    availabilityStartTime: "",
    availabilityEndDateMenu: false,
    availabilityEndTimeMenu: false,
    availabilityEndDate: "",
    availabilityEndTime: "",

    shipInformation: {
      name: "",
      description: "",
      address: {
        street: "",
        streetNumber: "",
        city: "",
        country: "",
      },
      shipLength: "",
      engineNumber: "",
      enginePower: "",
      maxSpeed: "",
      capacity: "",
      shipType: "",
      shipNavigationEquipment: [],
      pricePerHour: "",
      shipOwnerId: localStorage.getItem("userId"),
      availabilityStart: "",
      availabilityEnd: "",
      priceList: [],
      shipRules: [],
      shipReservationCancelTerm: "",
    },

    countries: ["Serbia"],
    shipTypes: ["Inflatible boat", "Kayak", "Catamaran", "Bay boat", "Speed boat", "Sailing boat", "Flats boat", "Yacht"],
    shipReservationCancelTerms: ["Free", "Owner retains the percentage"],
    datePickerFormat: "dd.MM.yyyy.",
    errorMessages: "",

    rules: [
      (value) => !!value || "This field is required",
      (value) => {
        const pattern = /[1-9]+/;
        return pattern.test(value) || "Only numbers from 1 to 10 are allowed";
      },
    ],

    dialogShipRule: false,
    dialogShipRuleDelete: false,
    headersShipRule: [
      {
        text: "Rule",
        align: "start",
        sortable: false,
        value: "shipRuleName",
      },
      { text: "Actions", align: "end", value: "actions", sortable: false },
    ],
    shipRules: [],
    formattedShipRules: [],
    editedIndexShipRule: -1,
    editedItemShipRule: {
      shipRuleName: "",
    },
    defaultItemShipRule: {
      shipRuleName: "",
    },

    dialogShipPriceTag: false,
    dialogShipPriceTagDelete: false,
    headersShipPriceTag: [
      {
        text: "Service description",
        align: "start",
        sortable: false,
        value: "serviceDescription",
      },
      {
        text: "Price",
        sortable: false,
        value: "price",
      },
      { text: "Actions", align: "end", value: "actions", sortable: false },
    ],
    shipPriceTags: [],
    formattedShipPriceTags: [],
    editedIndexShipPriceTag: -1,
    editedItemShipPriceTag: {
      serviceDescription: "",
      price: 0,
    },
    defaultItemShipPriceTag: {
      serviceDescription: "",
      price: 0,
    },

    images: {
      imagesInformation: [],
    },
    imagesFileList: [],
  }),

  computed: {
    formTitleShipRule() {
      return this.editedIndexShipRule === -1 ? "New rule" : "Edit rule";
    },

    formTitleShipPriceTag() {
      return this.editedIndexShipPriceTag === -1
        ? "New price tag"
        : "Edit price tag";
    },
  },

  watch: {
    dialogShipRule(val) {
      val || this.closeShipRule();
    },

    dialogShipRuleDelete(val) {
      val || this.closeShipRuleDelete();
    },

    dialogShipPriceTag(val) {
      val || this.closeShipRule();
    },

    dialogShipPriceTagDelete(val) {
      val || this.closeShipRuleDelete();
    },
  },

  methods: {
    registerShip() {
      this.shipInformation.shipRules = this.formatShipRules();
      this.shipInformation.priceList = this.formatShipPriceTags();
      this.shipInformation.availabilityStart = new Date(
        this.availabilityStartDate + " " + this.availabilityStartTime + ":00"
      );
      this.shipInformation.availabilityEnd = new Date(
        this.availabilityEndDate + " " + this.availabilityEndTime + ":00"
      );
      console.log("REQUEST OBJECT");
      console.log(this.shipInformation);
      this.axios
        .post("http://localhost:8091/ship/add", this.shipInformation, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          this.uploadCottageImages(response.data);
        })
        .catch((er) => {
          alert("Error has occured!");
          this.shipInformation.name = "";
          this.shipInformation.description = "";
          this.shipInformation.address.street = "";
          this.shipInformation.address.streetNumber = "";
          this.shipInformation.address.city = "";
          this.shipInformation.address.country = "";
          this.shipInformation.shipLength = "";
          this.shipInformation.engineNumber = "";
          this.shipInformation.enginePower = "";
          this.shipInformation.maxSpeed = "";
          this.shipInformation.capacity = "";
          this.shipInformation.shipType = "";
          this.shipInformation.shipNavigationEquipment = [];
          this.shipInformation.pricePerHour = "";
          this.shipInformation.availabilityStart = "";
          this.shipInformation.availabilityEnd = "";
           this.shipInformation.shipReservationCancelTerm = "";
          this.shipRules = [];
          this.shipPriceTags = [];
          this.availabilityStartDate = "";
          this.availabilityStartTime = "";
          this.availabilityEndDate = "";
          this.availabilityEndTime = "";
          console.log(er);
        });
    },

    uploadCottageImages(data) {
      this.images = this.formatShipImages();
      this.axios
        .post(
          "http://localhost:8091/image/upload/" +
            data.shipId.toString() +
            "/SHIP_OWNER",
          this.images,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          window.location.href = "http://localhost:8083/myShips";
        })
        .catch((er) => {
          alert("Error has occured during image upload!");
          console.log(er.response.data);
        });
    },

    formatShipImages() {
      for (let i = 0; i < this.imagesFileList.length; i++) {
        let imageInformation =
          this.imagesFileList[i].name + "," + this.imagesFileList[i].path;
        this.images.imagesInformation.push(imageInformation);
      }

      return this.images;
    },

    formatShipPriceTags() {
      for (let i = 0; i < this.shipPriceTags.length; i++) {
        let formattedPriceTag =
          this.shipPriceTags[i].serviceDescription +
          ";" +
          this.shipPriceTags[i].price;
        this.formattedShipPriceTags.push(formattedPriceTag);
      }

      return this.formattedShipPriceTags;
    },

    formatShipRules() {
      for (let i = 0; i < this.shipRules.length; i++) {
        this.formattedShipRules.push(
          this.shipRules[i].shipRuleName.toString()
        );
      }

      return this.formattedShipRules;
    },

    editShipRuleItem(item) {
      this.editedIndexShipRule = this.shipRules.indexOf(item);
      this.editedItemShipRule = Object.assign({}, item);
      this.dialogShipRule = true;
    },

    deleteShipRuleItem(item) {
      this.editedIndexShipRule = this.shipRules.indexOf(item);
      this.editedItemShipRule = Object.assign({}, item);
      this.dialogShipRuleDelete = true;
    },

    deleteShipRuleItemConfirm() {
      this.shipRules.splice(this.editedIndexShipRule, 1);
      this.closeShipRuleDelete();
    },

    closeShipRule() {
      this.dialogShipRule = false;
      this.$nextTick(() => {
        this.editedItemShipRule = Object.assign(
          {},
          this.defaultItemShipRule
        );
        this.editedIndexShipRule = -1;
      });
    },

    closeShipRuleDelete() {
      this.dialogShipRuleDelete = false;
      this.$nextTick(() => {
        this.editedItemShipRule = Object.assign(
          {},
          this.defaultItemShipRule
        );
        this.editedIndexShipRule = -1;
      });
    },

    saveShipRule() {
      if (this.editedIndexShipRule > -1) {
        Object.assign(
          this.shipRules[this.editedIndexShipRule],
          this.editedItemShipRule
        );
      } else {
        this.shipRules.push(this.editedItemShipRule);
      }
      this.closeShipRule();
    },

    editShipPriceTagItem(item) {
      this.editedIndexShipPriceTag = this.shipPriceTags.indexOf(item);
      this.editedItemShipPriceTag = Object.assign({}, item);
      this.dialogShipPriceTag = true;
    },

    deleteShipPriceTagItem(item) {
      this.editedIndexShipPriceTag = this.shipPriceTags.indexOf(item);
      this.editedItemShipPriceTag = Object.assign({}, item);
      this.dialogShipPriceTagDelete = true;
    },

    deleteShipPriceTagItemConfirm() {
      this.shipPriceTags.splice(this.editedIndexShipPriceTag, 1);
      this.closeShipPriceTagDelete();
    },

    closeShipPriceTag() {
      this.dialogShipPriceTag = false;
      this.$nextTick(() => {
        this.editedItemShipPriceTag = Object.assign(
          {},
          this.defaultItemShipPriceTag
        );
        this.editedIndexShipPriceTag = -1;
      });
    },

    closeShipPriceTagDelete() {
      this.dialogShipPriceTagDelete = false;
      this.$nextTick(() => {
        this.editedItemShipPriceTag = Object.assign(
          {},
          this.defaultItemShipPriceTag
        );
        this.editedIndexShipPriceTag = -1;
      });
    },

    saveShipPriceTag() {
      if (this.editedIndexShipPriceTag > -1) {
        Object.assign(
          this.shipPriceTags[this.editedIndexShipPriceTag],
          this.editedItemShipPriceTag
        );
      } else {
        this.shipPriceTags.push(this.editedItemShipPriceTag);
      }
      this.closeShipPriceTag();
    },

    uploadImageSuccess(formData, index, fileList) {
      console.log("data", formData, index, fileList);
      this.imagesFileList = fileList;
    },

    beforeRemove(index, removeCallBack, fileList) {
      console.log(fileList);
      removeCallBack();
      this.imagesFileList = fileList;
    },

    editImage(formData, index, fileList) {
      console.log("edit data", formData, index, fileList);
      this.imagesFileList = fileList;
    },

    markIsPrimary(index, fileList) {
      console.log("markIsPrimary data", index, fileList);
    },

    limitExceeded(amount) {
      console.log("limitExceeded data", amount);
    },
  },
};
</script>

<style scoped>
.countryCombo {
  cursor: pointer;
}

.shipCombo {
  cursor: pointer;
}

.shipReservationCancelTermCombo {
  cursor: pointer;
}
</style>