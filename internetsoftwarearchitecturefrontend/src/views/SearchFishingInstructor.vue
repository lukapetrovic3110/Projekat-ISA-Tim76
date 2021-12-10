<template>
  <div>
    <h1 id="caption">Search fishing instructor adventure</h1>
    <v-card id="content" justify-center>
      <v-container fluid>
        <v-data-iterator
          :items="items"
          :items-per-page.sync="itemsPerPage"
          :page.sync="page"
          :search="search"
          :sort-by="sortBy.toLowerCase()"
          :sort-desc="sortDesc"
          hide-default-footer
        >
          <template v-slot:header>
            <v-toolbar dark color="blue darken-3" class="mb-10">
              <v-text-field
                v-model="search"
                clearable
                flat
                solo-inverted
                hide-details
                prepend-inner-icon="mdi-magnify"
                label="Search"
              ></v-text-field>
              <template v-if="$vuetify.breakpoint.mdAndUp">
                <v-spacer></v-spacer>
                <v-select
                  v-model="sortBy"
                  flat
                  solo-inverted
                  hide-details
                  :items="keys"
                  prepend-inner-icon="mdi-magnify"
                ></v-select>
                <v-spacer></v-spacer>
                <v-btn-toggle v-model="sortDesc" mandatory>
                  <v-btn large depressed color="blue" :value="false">
                    <v-icon>mdi-arrow-up</v-icon>
                  </v-btn>
                  <v-btn large depressed color="blue" :value="true">
                    <v-icon>mdi-arrow-down</v-icon>
                  </v-btn>
                </v-btn-toggle>
              </template>
            </v-toolbar>
          </template>

          <template v-slot:default="props">
            <v-row>
              <v-col
                v-for="item in props.items"
                :key="item.name"
                cols="9"
                sm="1"
                md="3"
                lg="4"
              >
                <v-card class="itemCard">
                  <v-img
                    height="360"
                    alt="cottage"
                    v-bind:src="
                      require('../assets/images/' +
                        item.adventureImages[0] +
                        '.jpg')
                    "
                  ></v-img>

                  <v-card-title>
                    <h2>
                      {{ item.name }}
                    </h2>
                  </v-card-title>

                  <v-card-text>
                    <v-row align="center" class="mx-0">
                      <v-rating
                        color="amber"
                        dense
                        :value="item.rating"
                        length="5"
                        half-increments
                        readonly
                        size="31"
                      ></v-rating>
                      <div>
                        <h4 class="info--text">{{ item.rating }}</h4>
                      </div>
                    </v-row>

                    <div class="my-3 info--text">
                      <h3>{{ item.city }} , {{ item.country }}</h3>
                    </div>

                    <div class="justify-center">
                      <h4>
                        {{ item.description }}
                      </h4>
                    </div>

                    <div class="justify-center">
                      <h4>
                        Fishing instructor: {{ item.instructorFirstName }} {{ item.instructorLastName }}
                      </h4>
                    </div>

                  </v-card-text>

                  <v-divider class="mx-4"></v-divider>

                  <v-card-actions class="justify-center">
                    <v-btn
                      x-large
                      color="primary accent-3"
                      text
                      @click="reserve"
                    >
                      SEE MORE DETAILS
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-col>
            </v-row>
          </template>
          <template v-slot:footer>
            <div class="mr-2 mt-10" align="center" justify="center">
              <v-btn
                fab
                dark
                color="info darken-3"
                class="mr-1"
                @click="formerPage"
              >
                <v-icon>mdi-chevron-left</v-icon>
              </v-btn>

              <span class="justify-center info--text">
                Page {{ page }} of {{ numberOfPages }}
              </span>

              <v-btn
                fab
                dark
                color="info darken-3"
                class="ml-1"
                @click="nextPage"
              >
                <v-icon>mdi-chevron-right</v-icon>
              </v-btn>

              <v-spacer></v-spacer>

              <span class="justify-center info--text contentLeft">
                Items per page
              </span>
              <v-menu offset-x>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    dark
                    text
                    color="info"
                    class="ml-2"
                    v-bind="attrs"
                    v-on="on"
                  >
                    {{ itemsPerPage }}
                    <v-icon>mdi-chevron-down</v-icon>
                  </v-btn>
                </template>

                <v-list>
                  <v-list-item
                    v-for="(number, index) in itemsPerPageArray"
                    :key="index"
                    @click="updateItemsPerPage(number)"
                  >
                    <v-list-item-title> {{ number }}</v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>
            </div>
          </template>
        </v-data-iterator>
      </v-container>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "SearchFishingInstructor",
  data() {
    return {
      itemsPerPageArray: [3, 6, 9],
      search: "",
      filter: {},
      sortDesc: false,
      page: 1,
      itemsPerPage: 3,
      sortBy: "name",
      keys: ["name", "rating", "city", "country", "description", "instructorFirstName", "instructorLastName"],
      items: [],
    };
  },
  mounted() {
    this.displayAllAdventure();
  },
  computed: {
    numberOfPages() {
      return Math.ceil(this.items.length / this.itemsPerPage);
    },
    filteredKeys() {
      return this.keys.filter((key) => key !== "name");
    },
  },
  methods: {
    nextPage() {
      if (this.page + 1 <= this.numberOfPages) this.page += 1;
    },
    formerPage() {
      if (this.page - 1 >= 1) this.page -= 1;
    },
    updateItemsPerPage(number) {
      this.itemsPerPage = number;
    },
    displayAllAdventure() {
      this.axios.get("http://localhost:8091/adventure").then((response) => {
        console.log(response.data);
        this.items = response.data;
      });
    },
  },
};
</script>

<style scoped>
#caption {
  margin-top: 2%;
  margin-bottom: 2%;
  color: #007acc;
  text-align: center;
  font-weight: bold;
}
#content {
  width: 80%;
  text-align: center;
  margin: auto;
}
.itemCard {
  margin-left: 2%;
  margin: auto;
  height: 100%;
}
.contentLeft {
  margin-left: 80%;
}
</style>