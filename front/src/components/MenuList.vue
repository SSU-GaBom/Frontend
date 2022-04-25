<template>
  <v-menu offset-y>
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        small
        class="mx-2"
        fab
        dark
        color="teal"
        v-bind="attrs"
        v-on="on"
      >      
        <v-icon dark>
          mdi-format-list-bulleted-square
        </v-icon>
      </v-btn>
    </template>

    <v-list>      
      <v-btn
        v-bind="attrs"
        v-on="on"
        darks
        text
      >
        <router-link id="link" to="/user">마이페이지</router-link>
      </v-btn><br>
      <write-travel></write-travel><br>
      <v-btn
          v-bind="attrs"
          v-on="on"
          darks
          text
      >
        <router-link id="link" to="/travel">TRAVEL 탐색</router-link>
      </v-btn><br>
      <v-btn
          v-bind="attrs"
          v-on="on"
          darks
          text
      >
        <router-link id="link" to="/travel">찜 목록</router-link>
      </v-btn>
      <v-list-item
        v-for="(item, index) in items"
        :key="index"
      >
        <v-list-item-title>{{ item.title }}</v-list-item-title>
      </v-list-item>
    </v-list>
  </v-menu>
</template>

<script>
import WriteTravel from './WriteTravel.vue'
export default {
    name : 'MenuList',
    components :{
        WriteTravel
    },
    data() {
      return {
        text: 'center',
      }
    },
}
</script>

<style scoped>
#link {
    color: black;
    text-decoration: none;
}
</style>