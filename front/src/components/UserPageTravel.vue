<template>
  <v-card class="mx-auto" tile>
    <v-sheet class="mx-auto" elevation="8" height="200px" max-width="800">
      <v-list-item one-line>
        <v-list-item-content>
          <v-list-item-title class="headline text-left"
            >여행 목록</v-list-item-title
          >
        </v-list-item-content>
      </v-list-item>

      <v-row class="mx-2 my-2">
        <v-btn
          outlined
          height="100px"
          weight="100px"
          v-bind="attrs"
          v-on="on"
          darks
          text
        >
          <v-icon>mdi-marker</v-icon>
          <router-link id="link" to="/travel/content"> 게시글 </router-link>
        </v-btn>
        &nbsp;
        <v-btn
          outlined
          height="100px"
          weight="100px"
          v-bind="attrs"
          v-on="on"
          darks
          text
        >
          <v-icon>mdi-marker</v-icon>
          <router-link id="link" to="/travel/content"> 게시글 </router-link>
        </v-btn>
        &nbsp;
        <v-btn
          outlined
          height="100px"
          weight="100px"
          v-bind="attrs"
          v-on="on"
          darks
          text
        >
          <v-icon>mdi-marker</v-icon>
          <router-link id="link" to="/travel/content"> 게시글 </router-link>
        </v-btn>
        &nbsp;
        <v-btn
          outlined
          height="100px"
          weight="100px"
          v-bind="attrs"
          v-on="on"
          darks
          text
        >
          <v-icon>mdi-marker</v-icon>
          <router-link id="link" to="/travel/content"> 게시글 </router-link>
        </v-btn>
        &nbsp;
      </v-row>
    </v-sheet>
  </v-card>
</template>

<script>
export default {
  data: () => ({
    model: null,
  }),
};
</script>

<style>
#link {
  color: black;
  text-decoration: none;
}
</style>
