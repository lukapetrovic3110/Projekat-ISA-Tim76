import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/login",
    name: "Login",
    component: () => import("../views/Login.vue"),
  },
  {
    path: "/registerClient",
    name: "RegisterClient",
    component: () => import("../views/RegisterClient.vue"),
  },
  {
    path: "/confirmRegistration/:id",
    name: "ConfirmRegistration",
    component: () => import("../views/ConfirmRegistration.vue"),
  },
  {
    path: "/registerCottageOwner",
    name: "RegisterCottageOwner",
    component: () => import("../views/RegisterCottageOwner.vue"),
  },
  {
    path: "/registerShipOwner",
    name: "RegisterShipOwner",
    component: () => import("../views/RegisterShipOwner.vue"),
  },
  {
    path: "/registerFishingInstructor",
    name: "RegisterFishingInstructor",
    component: () => import("../views/RegisterFishingInstructor.vue"),
  },
  {
    path: "/registerSystemAdministrator",
    name: "RegisterSystemAdministrator",
    component: () => import("../views/RegisterSystemAdministrator.vue"),
  },
  {
    path: "/registrationRequests",
    name: "RegistrationRequests",
    component: () => import("../views/RegistrationRequests.vue"),
  },
  {
    path: "/clientProfile",
    name: "ClientProfile",
    component: () => import("../views/ClientProfile.vue"),
  },
  {
    path: "/clientSubscriptions",
    name: "ClientSubscriptions",
    component: () => import("../views/ClientSubscriptions.vue"),
  },
  {
    path: "/editClientPersonalInfo",
    name: "EditClientPersonalInfo",
    component: () => import("../views/EditClientPersonalInfo.vue"),
  },
  {
    path: "/changePassword",
    name: "ChangePassword",
    component: () => import("../views/ChangePassword.vue"),
  },
  {
    path: "/profileDeleteRequests",
    name: "ProfileDeleteRequests",
    component: () => import("../views/ProfileDeleteRequests.vue"),
  },
  {
    path: "/clientScheduledReservation",
    name: "ClientScheduledReservation",
    component: () => import("../views/ClientScheduledReservation.vue"),
  },
  {
    path: "/addCottage",
    name: "AddCottage",
    component: () => import("../views/AddCottage.vue"),
  },
  {
    path: "/myCottages",
    name: "MyCottages",
    component: () => import("../views/MyCottages.vue"),
  },
  {
    path: "/searchCottage",
    name: "SearchCottage",
    component: () => import("../views/SearchCottage.vue"),
  },
  {
    path: "/reservationCottageHistoryByClient",
    name: "ReservationCottageHistoryByClient",
    component: () => import("../views/ReservationCottageHistoryByClient.vue"),
  },
  {
    path: "/searchShip",
    name: "SearchShip",
    component: () => import("../views/SearchShip.vue"),
  },
  {
    path: "/reservationShipHistoryByClient",
    name: "ReservationShipHistoryByClient",
    component: () => import("../views/ReservationShipHistoryByClient.vue"),
  },
  {
    path: "/searchFishingInstructor",
    name: "SearchFishingInstructor",
    component: () => import("../views/SearchFishingInstructor.vue"),
  },
  {
    path: "/cottageProfile",
    name: "CottageProfile",
    component: () => import("../views/CottageProfile.vue"),
  },
  {
    path: "/editCottageInformation",
    name: "EditCottageInformation",
    component: () => import("../views/EditCottageInformation.vue"),
  },
  {
    path: "/reservationAdventureHistoryByClient",
    name: "ReservationAdventureHistoryByClient",
    component: () => import("../views/ReservationAdventureHistoryByClient.vue"),
  },
  {
    path: "/cottageOwnerReservationHistory",
    name: "CottageOwnerReservationHistory",
    component: () => import("../views/CottageOwnerReservationHistory.vue"),
  },
  {
    path: "/cottageOwnerReservationReport",
    name: "CottageOwnerReservationReport",
    component: () => import("../views/CottageOwnerReservationReport.vue"),
  },
];
const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
