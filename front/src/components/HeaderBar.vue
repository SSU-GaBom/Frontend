<template>
  <v-div>
    <v-sheet class="mx-auto" elevation="8">
      <v-app-bar dense flat color="light-green">
        <menu-list />
        <v-toolbar-title
          >&nbsp;&nbsp;
          <router-link id="link" to="/">GaBom</router-link>
        </v-toolbar-title>
        <v-spacer />
        <!-- <v-btn @click="test()">
                test
            </v-btn> -->
        <rank-comp></rank-comp>
        <template v-if="!isLoggedIn">
          <join-comp />
          <login-comp />
        </template>
        <template v-else>
          <logout-comp></logout-comp>
        </template>
      </v-app-bar>
    </v-sheet>
  </v-div>
</template>

<script>
import { mapGetters } from "vuex";
import LoginComp from "./LoginComp.vue";
import JoinComp from "./JoinComp.vue";
import RankComp from "./RankComp.vue";
import MenuList from "./MenuList.vue";
import LogoutComp from "./LogoutComp.vue";

export default {
  name: "HeaderBar",
  computed: {
    ...mapGetters(["isLoggedIn"]),
  },
  data() {
    return {};
  },
  components: {
    LoginComp,
    JoinComp,
    RankComp,
    MenuList,
    LogoutComp,
  },
};
</script>

<style scoped>
#link {
  color: black;
  text-decoration: none;
}
</style>
