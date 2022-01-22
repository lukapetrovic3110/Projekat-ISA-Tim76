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
          :rules="[() => !!cottageInformation.name || 'This field is required']"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="Street Name"
          v-model="cottageInformation.address.street"
          :rules="[
            () =>
              !!cottageInformation.address.street || 'This field is required',
          ]"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="Street Number"
          v-model="cottageInformation.address.streetNumber"
          :rules="[
            () =>
              !!cottageInformation.address.streetNumber ||
              'This field is required',
          ]"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="City"
          v-model="cottageInformation.address.city"
          :rules="[
            () => !!cottageInformation.address.city || 'This field is required',
          ]"
          :error-messages="errorMessages"
        />
        <v-autocomplete
          class="countryCombo"
          ref="country"
          v-model="cottageInformation.address.country"
          :rules="[
            () =>
              !!cottageInformation.address.country || 'This field is required',
          ]"
          :items="countries"
          label="Country"
          placeholder="Select..."
        />
        <v-text-field
          label="Description"
          v-model="cottageInformation.description"
          :rules="[
            () => !!cottageInformation.description || 'This field is required',
          ]"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="Number of rooms"
          v-model="cottageInformation.numberOfRooms"
          max="10"
          min="1"
          single-line
          type="number"
          style="width: 100%"
          :rules="rules"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="Number of beds per room"
          v-model="cottageInformation.numberOfBedsPerRoom"
          max="10"
          min="1"
          single-line
          type="number"
          style="width: 100%"
          :rules="rules"
          :error-messages="errorMessages"
        />
        <v-text-field
          label="Price per day"
          v-model="cottageInformation.pricePerDay"
          :rules="[
            () => !!cottageInformation.pricePerDay || 'This field is required',
          ]"
          :error-messages="errorMessages"
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
          :headers="headersCottageRule"
          :items="cottageRules"
          hide-default-footer
          no-data-text="Rules are not added"
          class="elevation-1"
        >
          <template v-slot:top>
            <v-toolbar flat>
              <v-toolbar-title style="color: gray"
                >Cottage rules</v-toolbar-title
              >
              <v-divider class="mx-4" inset vertical></v-divider>
              <v-spacer></v-spacer>
              <v-dialog v-model="dialogCottageRule" max-width="500px">
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
                    <span class="text-h5">{{ formTitleCottageRule }}</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-text-field
                        v-model="editedItemCottageRule.description"
                        label="Rule description"
                      ></v-text-field>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" text @click="closeCottageRule">
                      Cancel
                    </v-btn>
                    <v-btn color="blue darken-1" text @click="saveCottageRule">
                      Save
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
              <v-dialog v-model="dialogCottageRuleDelete" max-width="500px">
                <v-card>
                  <v-card-title class="text-h6"
                    >Are you sure you want to delete this rule?</v-card-title
                  >
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="closeCottageRuleDelete"
                      >Cancel</v-btn
                    >
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="deleteCottageRuleItemConfirm"
                      >OK</v-btn
                    >
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.actions`]="{ item }">
            <v-icon small class="mr-2" @click="editCottageRuleItem(item)">
              mdi-pencil
            </v-icon>
            <v-icon small @click="deleteCottageRuleItem(item)">
              mdi-delete
            </v-icon>
          </template>
        </v-data-table>
        <v-data-table
          :headers="headersCottagePriceTag"
          :items="cottagePriceTags"
          hide-default-footer
          no-data-text="Price tags are not added"
          class="elevation-1"
        >
          <template v-slot:top>
            <v-toolbar flat>
              <v-toolbar-title style="color: gray">Price list</v-toolbar-title>
              <v-divider class="mx-4" inset vertical></v-divider>
              <v-spacer></v-spacer>
              <v-dialog v-model="dialogCottagePriceTag" max-width="500px">
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
                    <span class="text-h5">{{ formTitleCottagePriceTag }}</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-text-field
                        v-model="editedItemCottagePriceTag.serviceDescription"
                        label="Service description"
                      ></v-text-field>
                      <v-text-field
                        v-model="editedItemCottagePriceTag.price"
                        label="Price"
                      ></v-text-field>
                    </v-container>
                  </v-card-text>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="closeCottagePriceTag"
                    >
                      Cancel
                    </v-btn>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="saveCottagePriceTag"
                    >
                      Save
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
              <v-dialog v-model="dialogCottagePriceTagDelete" max-width="500px">
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
                      @click="closeCottagePriceTagDelete"
                      >Cancel</v-btn
                    >
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="deleteCottagePriceTagItemConfirm"
                      >OK</v-btn
                    >
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <template v-slot:[`item.actions`]="{ item }">
            <v-icon small class="mr-2" @click="editCottagePriceTagItem(item)">
              mdi-pencil
            </v-icon>
            <v-icon small @click="deleteCottagePriceTagItem(item)">
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
            :data-images="cottageImagesForDisplay"
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
        color="info"
        elevation="2"
        x-large
        raised
        v-on:click="editCottageInformation"
        >Save</v-btn
      >
      <v-btn
        class="mx-auto mb-10 mt-10; color:white"
        color="info"
        elevation="2"
        x-large
        raised
        v-on:click="cancelCottageInformationEdit"
        >Cancel</v-btn
      >
    </v-card-actions>
  </v-card>
</template>

<script>
import VueUploadMultipleImage from "vue-upload-multiple-image";

export default {
  name: "EditCottageInformation",
  components: {
    VueUploadMultipleImage,
  },

  data: () => ({
    cottageId: "",
    cottageInformation: null,
    errorMessages: "",
    countries: ["Serbia"],
    datePickerFormat: "dd.MM.yyyy.",

    availabilityStartDateMenu: false,
    availabilityStartTimeMenu: false,
    availabilityStartDate: "",
    availabilityStartTime: "",
    availabilityEndDateMenu: false,
    availabilityEndTimeMenu: false,
    availabilityEndDate: "",
    availabilityEndTime: "",
    
    dialogCottageRule: false,
    dialogCottageRuleDelete: false,
    headersCottageRule: [
      {
        text: "Rule",
        align: "start",
        sortable: false,
        value: "description",
      },
      { text: "Actions", align: "end", value: "actions", sortable: false },
    ],
    cottageRules: [],
    formattedCottageRules: [],
    editedIndexCottageRule: -1,
    editedItemCottageRule: {
      description: "",
    },
    defaultItemCottageRule: {
      description: "",
    },

    dialogCottagePriceTag: false,
    dialogCottagePriceTagDelete: false,
    headersCottagePriceTag: [
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
    cottagePriceTags: [],
    formattedCottagePriceTags: [],
    editedIndexCottagePriceTag: -1,
    editedItemCottagePriceTag: {
      serviceDescription: "",
      price: "",
    },
    defaultItemCottagePriceTag: {
      serviceDescription: "",
      price: "",
    },

    cottageImages: null,
    cottageImagesForDisplay: [],
    images: {
      imagesInformation: [],
    },
    imagesFileList: [],
  }),

  mounted() {
    this.getCottageInformation();
  },

  computed: {
    formTitleCottageRule() {
      return this.editedIndexCottageRule === -1 ? "New rule" : "Edit rule";
    },

    formTitleCottagePriceTag() {
      return this.editedIndexCottagePriceTag === -1
        ? "New price tag"
        : "Edit price tag";
    },
  },

  watch: {
    dialogCottageRule(val) {
      val || this.closeCottageRule();
    },

    dialogCottageRuleDelete(val) {
      val || this.closeCottageRuleDelete();
    },

    dialogCottagePriceTag(val) {
      val || this.closeCottageRule();
    },

    dialogCottagePriceTagDelete(val) {
      val || this.closeCottageRuleDelete();
    },
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
          console.log(this.cottageInformation);
          this.availabilityStartDate = new Date(
            this.cottageInformation.availabilityStart -
              new Date().getTimezoneOffset() * 60000
          )
            .toISOString()
            .substr(0, 10);
          this.availabilityStartTime = new Date(
            this.cottageInformation.availabilityStart -
              new Date().getTimezoneOffset() * 60000
          )
            .toISOString()
            .substr(11, 5);
          this.availabilityEndDate = new Date(
            this.cottageInformation.availabilityEnd -
              new Date().getTimezoneOffset() * 60000
          )
            .toISOString()
            .substr(0, 10);
          this.availabilityEndTime = new Date(
            this.cottageInformation.availabilityEnd -
              new Date().getTimezoneOffset() * 60000
          )
            .toISOString()
            .substr(11, 5);

          this.cottageInformation.cottageRules.forEach((rule) => {
            this.cottageRules.push({
              description: rule.description,
            });
          });

          this.cottageInformation.priceList.priceTags.forEach((priceTag) => {
            this.cottagePriceTags.push({
              serviceDescription: priceTag.serviceName,
              price: priceTag.price,
            });
          });

          this.getCottageImages();
        })
        .catch((err) => console.log(err));
    },

    getCottageImages() {
      this.axios
        .get(
          "http://localhost:8091/image/getImages/cottage/" + this.cottageId,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          this.cottageImages = response.data;
          this.cottageImages.images.forEach((image) => {
            this.cottageImagesForDisplay.push({
              default: image.defaultImage,
              highlight: image.highlight,
              name: image.name + ".jpg",
              path: image.path,
            });
          });
        })
        .catch((err) => console.log(err));
    },

    editCottageInformation() {
      this.cottageInformation.cottageRules = this.formatCottageRules();
      this.cottageInformation.priceList = this.formatCottagePriceTags();
      this.cottageInformation.availabilityStart = new Date(
        this.availabilityStartDate + " " + this.availabilityStartTime + ":00"
      );
      this.cottageInformation.availabilityEnd = new Date(
        this.availabilityEndDate + " " + this.availabilityEndTime + ":00"
      );
      this.cottageInformation.cottageOwnerId =
        this.cottageInformation.cottageOwner.userId;
      this.axios
        .post(
          "http://localhost:8091/cottage/edit/" + this.cottageId,
          this.cottageInformation,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          this.uploadCottageImages(response.data);
        })
        .catch((er) => {
          alert("Error has occured!");
          console.log(er);
          (this.cottageRules = []),
            (this.formattedCottageRules = []),
            (this.cottagePriceTags = []),
            (this.formattedCottagePriceTags = []),
            (this.cottageImagesForDisplay = []),
            (this.imagesFileList = []),
            this.getCottageInformation();
        });
    },

    uploadCottageImages(data) {
      this.images = this.formatCottageImages();
      this.axios
        .post(
          "http://localhost:8091/image/edit/" +
            data.cottageId.toString() +
            "/COTTAGE_OWNER",
          this.images,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          window.location.href = "http://localhost:8083/cottageProfile";
        })
        .catch((er) => {
          alert("Error has occured during image upload!");
          console.log(er.response.data);
          this.getCottageInformation();
        });
    },

    cancelCottageInformationEdit() {
      window.location.href = "http://localhost:8083/cottageProfile";
    },

    formatCottageImages() {
      for (let i = 0; i < this.imagesFileList.length; i++) {
        let imageInformation =
          this.imagesFileList[i].name + "," + this.imagesFileList[i].path;
        this.images.imagesInformation.push(imageInformation);
      }

      return this.images;
    },

    formatCottagePriceTags() {
      for (let i = 0; i < this.cottagePriceTags.length; i++) {
        let formattedPriceTag =
          this.cottagePriceTags[i].serviceDescription +
          ";" +
          this.cottagePriceTags[i].price;
        this.formattedCottagePriceTags.push(formattedPriceTag);
      }

      return this.formattedCottagePriceTags;
    },

    formatCottageRules() {
      for (let i = 0; i < this.cottageRules.length; i++) {
        this.formattedCottageRules.push(
          this.cottageRules[i].description.toString()
        );
      }

      return this.formattedCottageRules;
    },

    editCottageRuleItem(item) {
      this.editedIndexCottageRule = this.cottageRules.indexOf(item);
      this.editedItemCottageRule = Object.assign({}, item);
      this.dialogCottageRule = true;
    },

    deleteCottageRuleItem(item) {
      this.editedIndexCottageRule = this.cottageRules.indexOf(item);
      this.editedItemCottageRule = Object.assign({}, item);
      this.dialogCottageRuleDelete = true;
    },

    deleteCottageRuleItemConfirm() {
      this.cottageRules.splice(this.editedIndexCottageRule, 1);
      this.closeCottageRuleDelete();
    },

    closeCottageRule() {
      this.dialogCottageRule = false;
      this.$nextTick(() => {
        this.editedItemCottageRule = Object.assign(
          {},
          this.defaultItemCottageRule
        );
        this.editedIndexCottageRule = -1;
      });
    },

    closeCottageRuleDelete() {
      this.dialogCottageRuleDelete = false;
      this.$nextTick(() => {
        this.editedItemCottageRule = Object.assign(
          {},
          this.defaultItemCottageRule
        );
        this.editedIndexCottageRule = -1;
      });
    },

    saveCottageRule() {
      if (this.editedIndexCottageRule > -1) {
        Object.assign(
          this.cottageRules[this.editedIndexCottageRule],
          this.editedItemCottageRule
        );
      } else {
        this.cottageRules.push(this.editedItemCottageRule);
      }
      this.closeCottageRule();
    },

    editCottagePriceTagItem(item) {
      this.editedIndexCottagePriceTag = this.cottagePriceTags.indexOf(item);
      this.editedItemCottagePriceTag = Object.assign({}, item);
      this.dialogCottagePriceTag = true;
    },

    deleteCottagePriceTagItem(item) {
      this.editedIndexCottagePriceTag = this.cottagePriceTags.indexOf(item);
      this.editedItemCottagePriceTag = Object.assign({}, item);
      this.dialogCottagePriceTagDelete = true;
    },

    deleteCottagePriceTagItemConfirm() {
      this.cottagePriceTags.splice(this.editedIndexCottagePriceTag, 1);
      this.closeCottagePriceTagDelete();
    },

    closeCottagePriceTag() {
      this.dialogCottagePriceTag = false;
      this.$nextTick(() => {
        this.editedItemCottagePriceTag = Object.assign(
          {},
          this.defaultItemCottagePriceTag
        );
        this.editedIndexCottagePriceTag = -1;
      });
    },

    closeCottagePriceTagDelete() {
      this.dialogCottagePriceTagDelete = false;
      this.$nextTick(() => {
        this.editedItemCottagePriceTag = Object.assign(
          {},
          this.defaultItemCottagePriceTag
        );
        this.editedIndexCottagePriceTag = -1;
      });
    },

    saveCottagePriceTag() {
      if (this.editedIndexCottagePriceTag > -1) {
        Object.assign(
          this.cottagePriceTags[this.editedIndexCottagePriceTag],
          this.editedItemCottagePriceTag
        );
      } else {
        this.cottagePriceTags.push(this.editedItemCottagePriceTag);
      }
      this.closeCottagePriceTag();
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
</style>