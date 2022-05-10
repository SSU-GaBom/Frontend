<template>
  <div id="main" class="my-3">
    <travel-comp />
    <v-divider></v-divider>
    <travel-comp />
    <v-divider></v-divider>
    <travel-comp />
    <v-divider></v-divider>
    <travel-comp />
    <v-divider></v-divider>
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
