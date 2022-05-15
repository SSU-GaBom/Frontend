<template>
  <div id="main">
    <travel-comp />
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
