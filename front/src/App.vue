<template>
  <v-app>
    <header-bar></header-bar> 

    <v-content>
      <main-screen></main-screen>
      <travel-map></travel-map>
    </v-content>

  </v-app>
</template>

<script>
import HeaderBar from './components/HeaderBar.vue'
import MainScreen from './components/MainScreen.vue'
import TravelMap from './components/TravelMap.vue'

export default {
  name: 'app',
  components:{
    HeaderBar,
    TravelMap,
    MainScreen
  }

}

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
