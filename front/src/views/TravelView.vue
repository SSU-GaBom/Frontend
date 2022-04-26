<template>
  <div id="main">
    <travel-content/>
    <travel-content/>
  </div>

</template>

<script>
import TravelContent from '../components/TravelContent.vue'
export default {
  name: 'TravelView',
  components: {
    TravelContent
  },
}
</script>

<style scoped>
#main {
    position: relative;
    width: 30%;
    height: 780px;
}
</style>