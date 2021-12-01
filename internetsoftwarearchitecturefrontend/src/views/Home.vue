<template>
  <div>
    <page-background/>
    <h1 id = "caption">Home page</h1>
  </div>
</template>

<script>
import PageBackground from '../components/PageBackground.vue'
  export default {
    name: 'Home',
    components: {
        PageBackground,
    },
  }
</script>

<style scoped>
#caption {
  margin-left: 46%;
  color: white;
}
</style>
