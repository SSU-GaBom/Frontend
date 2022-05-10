<template>
  <div>
    <v-dialog width="700px" height="1000px" v-model="dialog">
      <template v-slot:activator="{ on, attrs }">
        <v-btn v-bind="attrs" v-on="on" text color="grey lighten-0">
          <v-icon>mdi-comment-text</v-icon>
          <v-text class="ms-2">{{ commentNum }}</v-text>
        </v-btn>
      </template>

      <v-sheet color="white">
        <template>
          <v-card class="mx-0">
            <v-menu bottom min-width="200px" rounded offset-y>
              <template v-slot:activator="{ on }">
                <v-btn icon x-large v-on="on">
                  <v-avatar color="brown" size="48">
                    <span class="white--text text-h5">sdfs</span>
                  </v-avatar>
                </v-btn>
              </template>
            </v-menu>

            <v-card-title>작성자 이름</v-card-title>

            <v-card-text>
              <div class="my-4 text-subtitle-1">댓글내용....</div>

              <div>댓글내용....</div>
            </v-card-text>

            <v-divider class="mx-4"></v-divider>
          </v-card>
        </template>
      </v-sheet>
      <v-sheet color="white">
        <template>
          <v-card class="mx-0">
            <v-menu bottom min-width="200px" rounded offset-y>
              <template v-slot:activator="{ on }">
                <v-btn icon x-large v-on="on">
                  <v-avatar color="brown" size="48">
                    <span class="white--text text-h5">sdfs</span>
                  </v-avatar>
                </v-btn>
              </template>
            </v-menu>

            <v-card-title>작성자 이름</v-card-title>

            <v-card-text>
              <div class="my-4 text-subtitle-1">댓글내용....</div>

              <div>댓글내용....</div>
            </v-card-text>

            <v-divider class="mx-4"></v-divider>
          </v-card>
        </template>
      </v-sheet>

      <v-sheet color="white">
        <template>
          <v-card class="mx-0">
            <v-menu bottom min-width="200px" rounded offset-y>
              <template v-slot:activator="{ on }">
                <v-btn icon x-large v-on="on">
                  <v-avatar color="brown" size="48">
                    <span class="white--text text-h5">sdfs</span>
                  </v-avatar>
                </v-btn>
              </template>
            </v-menu>

            <v-card-title>작성자 이름</v-card-title>

            <v-card-text>
              <div class="my-4 text-subtitle-1">댓글내용....</div>

              <div>댓글내용....</div>
            </v-card-text>

            <v-divider class="mx-4"></v-divider>
          </v-card>
        </template>
      </v-sheet>
    </v-dialog>
  </div>
</template>

<script>
import store from "../store/index";
export default {
  name: "CommentComp",
  data() {
    return {
      commentNum: 3,
      dialog: false,
      card: {
        images: [],
        text: null,
      },
    };
  },
  methods: {
    addCard() {
      store.commit("SET_CARD", this.card);
      this.dialog = false;
    },
  },
};
</script>

<style lang=""></style>
