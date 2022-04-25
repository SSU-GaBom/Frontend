<template>
  <div id="main">
    <hello-world/>
  </div>
</template>

<script>
import HelloWorld from '../components/HelloWorld.vue'
  export default {
    name: 'HomeView',
    components: {
      HelloWorld
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