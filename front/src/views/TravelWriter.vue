<template>
  <div id="main">
    <write-travel/>
  </div>

</template>

<script>
import WriteTravel from '../components/WriteTravel.vue'
export default {
  name: 'TravelWriter',
  components: {
    WriteTravel
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