<template>
  <div>  
    <h1 id="homeCaption">Home</h1>
    <page-background />
  </div>
</template>

<script>
import PageBackground from "../components/PageBackground.vue";
export default {
  name: "Home",
  components: {
    PageBackground,
  },
};
</script>

<style scoped>
#homeCaption {
  margin-top: 2%;
  margin-bottom: 2%;
  color: #007acc;
  text-align: center;
  font-weight: bold;
}
</style>
