<template>
  <div id="main">
    <travel-detail />
  </div>
</template>

<script>
import TravelDetail from "../components/TravelDetail.vue";
export default {
  name: "TravelView",
  components: {
    TravelDetail,
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
