<template>
  <span>
    <v-navigation-drawer
      app
      temporary
      v-model="drawer"
      class="blue lighten-2"
      dark
      disable-resize-watcher
    >
      <v-list>
        <template v-for="(item, index) in items">
          <v-list-tile :key="index">
            <v-list-tile-content>
              <v-card
                height="40px"
                class="ma-1 pt-2 text-m-button btn-block text-center"
              >
                <router-link class="router" :to="item.path">{{
                  item.title
                }}</router-link>
              </v-card>
            </v-list-tile-content>
          </v-list-tile>
          <v-divider :key="`divider-${index}`"></v-divider>
        </template>
      </v-list>
    </v-navigation-drawer>
    <v-toolbar app dark class="blue lighten-2">
      <v-app-bar-nav-icon
        class="hidden-md-and-up"
        @click="drawer = !drawer"
      ></v-app-bar-nav-icon>
      <v-spacer class="hidden-md-and-up"></v-spacer>
      <v-btn flat class="hidden-sm-and-down ma-1" @click="drawer = !drawer"
        >Menu</v-btn
      >
      <v-toolbar-title class="applicationTitlePos"
        ><router-link class="applicationTitle" to="/">{{
          applicationTitle
        }}</router-link></v-toolbar-title
      >
      <v-spacer class="hidden-sm-and-down"></v-spacer>
      <div v-if="!isLogged">
        <v-btn flat class="hidden-sm-and-down">
          <router-link class="router" to="/login">Login</router-link>
        </v-btn>
      </div>
      <div v-if="isLogged" id="myProfile">
        <v-btn flat class="hidden-sm-and-down" v-on:click="myProfile"
          >My profile</v-btn
        >
      </div>
      <div v-if="isLogged">
        <v-btn flat class="hidden-sm-and-down" v-on:click="logOff"
          >Log off</v-btn
        >
      </div>
      <div class="text-center" id="registerDropdown" v-if="!isLogged">
        <v-menu offset-y>
          <template v-slot:activator="{ on, attrs }">
            <v-btn color="primary" dark v-bind="attrs" v-on="on">
              Register
            </v-btn>
          </template>
          <v-list>
            <v-list-item v-for="(item, index) in itemsRegister" :key="index">
              <v-list-item-title>
                <v-list-tile-content>
                  <v-container fluid fill-height>
                    <v-layout align-center justify-center>
                      <v-flex>
                        <v-card height="30px" flat class="m-3 text-xs-center">
                          <router-link class="dropDown" :to="item.path">{{
                            item.title
                          }}</router-link>
                        </v-card>
                      </v-flex>
                    </v-layout>
                  </v-container>
                </v-list-tile-content>
              </v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </div>
    </v-toolbar>
  </span>
</template>

<script>
export default {
  name: "NavigationBar",
  itemsRegister: [],
  firstName: "",
  lastName: "",
  computed: {
    isLogged: function () {
      var token = localStorage.getItem("token");
      return token !== "";
    },
  },
  data() {
    return {
      applicationTitle: "Ship, cottage and fishing",
      drawer: false,
      userType: "",
      items: [{ title: "Home", path: "/" }],
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      this.userType = localStorage.getItem("userType");
      this.itemsRegister = [
        { title: "Register as client", path: "/registerClient" },
        { title: "Register as cottage owner", path: "/registerCottageOwner" },
        { title: "Register as ship owner", path: "/registerShipOwner" },
        { title: "Register as fishing instructor", path: "/registerFishingInstructor" },
      ];
      if (this.userType === "CLIENT") {
        this.items = [
          { title: "Home", path: "/" },
          { title: "My profile", path: "/clientProfile" },
          { title: "Search cottage", path: "/searchCottage" },
          { title: "Search ship or boat", path: "/searchShip" },
          { title: "Search fishing instructor", path: '/searchFishingInstructor'},
          { title: "View reservation history", path: '/clientReservationHistory'},
          { title: "View scheduled reservations", path: '/clientScheduledReservation'},
          { title: "View subscriptions", path: "/clientSubscriptions"},
          { title: "Write a complaint", path: "/clientComplaint"},
        ];
      } else if (this.userType === "COTTAGE_OWNER") {
        this.items = [
          { title: "Home", path: "/" },
          { title: "My cottages", path: "/myCottages" },
          { title: "Register new cottage", path: "/addCottage" },
          { title: "Reservation history", path: "/cottageOwnerReservationHistory" },
          { title: "Write a report for reservation", path: "/cottageOwnerReservationReport" },
        ];
      } else if (this.userType === "SHIP_OWNER") {
        this.items = [
          { title: "Home", path: "/" },
          { title: "My ships", path: "/myShips" },
          { title: "Register new ship", path: "/addShip" },
        ];
      } else if (this.userType === "FISHING_INSTRUCTOR") {
        this.items = [{ title: "Home", path: "/" }];
      } else if (this.userType === "SYSTEM_ADMINISTRATOR") {
        this.items = [
          { title: "Home", path: "/" },
          { title: "My profile", path: "/systemAdministratorProfile"},
          { title: "Registration requests", path: "/registrationRequests" },
          { title: "Profile delete requests", path: "/profileDeleteRequests"},
          { title: "Complaint requests", path: "/systemAdministratorComplaint"},
          { title: "Revision requests", path: "/systemAdministratorRevision"},
          { title: "Register new adminstrator", path: "/registerSystemAdministrator"},
        ];
      } else {
        this.items = [
          { title: "Home", path: "/" },
          { title: "Search cottage", path: "/searchCottage" },
          { title: "Search ship or boat", path: "/searchShip" },
          { title: "Search fishing instructor", path: '/searchFishingInstructor'},
        ];
      }
    },
    myProfile() {
      if(this.userType === "CLIENT")
        window.location.href = "/clientProfile";
      else if (this.userType === "COTTAGE_OWNER")
        window.location.href = "/cottageOwnerProfile";
      else if (this.userType === "SHIP_OWNER")
        window.location.href = "/shipOwnerProfile";
      else if (this.userType === "SYSTEM_ADMINISTRATOR")
        window.location.href = "/systemAdministratorProfile";
      else
        window.location.href = "/";
    },
    logOff() {
      localStorage.setItem("email", "");
      localStorage.setItem("userId", "");
      localStorage.setItem("token", "");
      localStorage.setItem("userType", "");
      window.location.href = "/login";
    },
  },
};
</script>

<style scoped>
.applicationTitlePos {
  margin-left: 36%;
}

.applicationTitle {
  font-weight: bolder;
  font-size: 1.5em;
  height: 50px;
  text-decoration: none;
  color: white;
}

.router {
  text-decoration: none;
  color: white;
}

.dropDown {
  text-decoration: none;
  color: black;
}

#registerDropdown {
  margin-left: 1%;
}

#myProfile {
  margin-right: 1%;
}
</style>