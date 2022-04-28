<template>
  <v-app>
    <header-bar></header-bar>
    <v-main>
      <router-view></router-view>
      <!-- <div class="flex">
        <router-view></router-view>
        <travel-map></travel-map>
      </div> -->
    </v-main>
  </v-app>
</template>

<script>
import HeaderBar from './components/HeaderBar.vue'
import TravelMap from './components/TravelMap.vue'


export default {
  name: 'app',
  components:{
    HeaderBar,
  //  TravelMap
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
.flex {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
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
