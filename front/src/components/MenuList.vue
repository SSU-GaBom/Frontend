<template>
  <v-menu offset-y>
    <template v-slot:activator="{ on, attrs }">
      <v-btn small class="mx-2" fab dark color="teal" v-bind="attrs" v-on="on">
        <v-icon dark> mdi-format-list-bulleted-square </v-icon>
      </v-btn>
    </template>

    <v-list>
      <v-btn v-bind="attrs" v-on="on" darks text>
        <router-link id="link" to="/user">마이페이지</router-link>
      </v-btn>
      <br />
      <v-btn v-bind="attrs" v-on="on" darks text>
        <router-link id="link" to="/travel/list/like">좋아요 목록</router-link>
      </v-btn>
      <br />
      <v-btn v-bind="attrs" v-on="on" darks text>
        <router-link id="link" to="/travel/list/zzim">찜 목록</router-link>
      </v-btn>
      <br />
      <v-btn v-bind="attrs" v-on="on" darks text>
        <router-link id="link" to="/travel/list"> TRAVEL 탐색 </router-link>
      </v-btn>
      <br />
      <v-btn v-bind="attrs" v-on="on" darks text>
        <router-link id="link" to="/travel/write">TRAVEL 작성</router-link>
      </v-btn>
    </v-list>
  </v-menu>
</template>

<script>
export default {
  name: "MenuList",
  components: {},
  data() {
    return {
      text: "center",
    };
  },
};
</script>

<style scoped>
#link {
  color: black;
  text-decoration: none;
}
</style>
