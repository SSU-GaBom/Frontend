<template>
  <div id="main">
    <travel-content-list/>
  </div>

</template>

<script>
import TravelContentList from '../components/TravelContentList.vue'
export default {
  name: 'TravelContentList',
  components: {
    TravelContentList
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