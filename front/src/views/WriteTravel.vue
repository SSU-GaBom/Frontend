<template>
  <div id="main">
      <select-location/>
  </div>
</template>

<script>
import SelectLocation from '../components/SelectLocation.vue'

export default {
  name: 'WriteTravel',
  components: {
    SelectLocation
  },
}
</script>

<style scoped>
#main {
    position: relative;
    width: 30%;
    height: 580px;
}
#content {
  margin: 10px auto;
}
</style>