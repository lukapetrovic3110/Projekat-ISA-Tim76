<template>
  <v-container fluid fill-height class="wallpaper"/>
</template>

<script>
  export default {
    name: 'PageBackground',
  }  
</script>

<style scoped>
.wallpaper {
  background: url('../assets/wallpaper.jpg');
  background-size: cover;
  width: 100%;
  height: 100%;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: center;
}
</style>
