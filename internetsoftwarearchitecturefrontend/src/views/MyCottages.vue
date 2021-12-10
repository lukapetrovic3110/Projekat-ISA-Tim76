<template>
  <div></div>
</template>

<script>
export default {
  name: "MyCottages",
  data: () => ({
    cottages: [],
    cottageOwnerId: "",
  }),

  computed: {},

  mounted() {
    this.getAllCottages();
  },
  methods: {
    getAllCottages() {
      this.cottageOwnerId = localStorage.getItem("userId");
      this.axios
        .get("http://localhost:8091/cottage/getAll/" + this.cottageOwnerId, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        })
        .then((response) => {
          console.log(response.data);
          this.cottages = response.data;
        })
        .catch((err) => console.log(err));
    },
  },
};
</script>

<style scoped>
</style>