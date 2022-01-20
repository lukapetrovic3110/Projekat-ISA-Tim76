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
    path: "/systemAdministratorProfile",
    name: "SystemAdministratorProfile",
    component: () => import("../views/SystemAdministratorProfile.vue"),
  },
  {
    path: "/clientProfile",
    name: "ClientProfile",
    component: () => import("../views/ClientProfile.vue"),
  },
  {
    path: "/clientReservationHistory",
    name: "ClientReservationHistory",
    component: () => import("../views/ClientReservationHistory.vue"),
  },
  {
    path: "/clientSubscriptions",
    name: "ClientSubscriptions",
    component: () => import("../views/ClientSubscriptions.vue"),
  },
  {
    path: "/clientComplaint",
    name: "ClientComplaint",
    component: () => import("../views/ClientComplaint.vue"),
  },
  {
    path: "/editClientPersonalInfo",
    name: "EditClientPersonalInfo",
    component: () => import("../views/EditClientPersonalInfo.vue"),
  },
  {
    path: "/editSystemAdministratorPersonalInfo",
    name: "EditSystemAdministratorPersonInfo",
    component: () => import("../views/EditSystemAdministratorPersonalInfo.vue"),
  },
  {
    path: "/systemAdministratorComplaint",
    name: "SystemAdministratorComplaint",
    component: () => import("../views/SystemAdministratorComplaint.vue"),
  },
  {
    path: "/systemAdministratorRevision",
    name: "SystemAdministratorRevision",
    component: () => import("../views/SystemAdministratorRevision.vue"),
  },
  {
    path: "/changePassword",
    name: "ChangePassword",
    component: () => import("../views/ChangePassword.vue"),
  },
  {
    path: "/changePasswordFirstLogin",
    name: "ChangePasswordFirstLogin",
    component: () => import("../views/ChangePasswordFirstLogin.vue"),
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
    path: "/cottageDetails",
    name: "CottageDetails",
    component: () => import("../views/CottageDetails.vue"),
  },
  {
    path: "/clientCottageFastReservation",
    name: "ClientCottageFastReservation",
    component: () => import("../views/ClientCottageFastReservation.vue"),
  },
  {
    path: "/searchShip",
    name: "SearchShip",
    component: () => import("../views/SearchShip.vue"),
  },
  {
    path: "/shipDetails",
    name: "ShipDetails",
    component: () => import("../views/ShipDetails.vue"),
  },
  {
    path: "/clientShipFastReservation",
    name: "ClientShipFastReservation",
    component: () => import("../views/ClientShipFastReservation.vue"),
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
    path: "/adventureDetails",
    name: "AdventureDetails",
    component: () => import("../views/AdventureDetails.vue"),
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
  {
    path: "/cottageFastReservation",
    name: "CottageFastReservation",
    component: () => import("../views/CottageFastReservation.vue"),
  },
];
const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
