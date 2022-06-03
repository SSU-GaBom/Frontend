<template>
  <v-app>
    <header-bar
      style="position: sticky; top: 0px; z-index: 2147483647"
    ></header-bar>
    <v-main>
      <!-- <router-view></router-view> -->
      <div class="flex">
        <router-view style="position: relative"></router-view>
        <write-travel-map v-if="this.$route.name === 'travel-writer'" style="position: fixed; right: 0px"></write-travel-map>
        <detail-travel-map v-else-if="this.$route.name === 'travel-view'" style="position: fixed; right: 0px"></detail-travel-map>
        <profile-user-map v-else-if="this.$route.name === 'userPage' || this.$route.name === 'myPage'" style="position: fixed; right: 0px"></profile-user-map>
        <simple-map v-else-if="this.$route.name === 'travel-list'" style="position: fixed; right: 0px"></simple-map>
        <travel-map v-else-if="this.myUserId" style="position: fixed; right: 0px"></travel-map>
        <simple-map v-else style="position: fixed; right: 0px"></simple-map>
        
      </div>
    </v-main>
  </v-app>
</template>

<script>
import HeaderBar from "./components/HeaderBar.vue";
import TravelMap from "./components/TravelMap.vue";
import WriteTravelMap from "./components/WriteTravelMap.vue";
import DetailTravelMap from './components/DetailTravelMap.vue'
import SimpleMap from './components/SimpleMap.vue'
import ProfileUserMap from './components/ProfileMap.vue'

import { mapGetters } from "vuex";


export default {
  name: "app",
  components: {
    HeaderBar,
    WriteTravelMap,
    TravelMap,
    DetailTravelMap,
    ProfileUserMap,
    SimpleMap
  },
  computed: {
    ...mapGetters([
      "myUserId"
    ]),
  },
};

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
