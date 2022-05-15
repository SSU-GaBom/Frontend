<template>
<<<<<<< HEAD
  <div id="main">
    <travel-comp />
=======
  <div id="main" class="my-3">
    <travel-comp />
    <v-divider></v-divider>
    <travel-comp />
    <v-divider></v-divider>
    <travel-comp />
    <v-divider></v-divider>
    <travel-comp />
    <v-divider></v-divider>
>>>>>>> c8a368701192bec0b1d91b2570f7e2cddbeed586
  </div>
</template>

<script>
import TravelComp from "../components/TravelComp.vue";
export default {
  name: "TravelList",
  components: {
    TravelComp,
  },
};
</script>

<style scoped>
#main {
  position: relative;
  width: 30%;
  height: 780px;
}
</style>
