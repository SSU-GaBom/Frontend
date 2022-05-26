<template>
  <v-row justify="center">
    <v-dialog
      scrollable
      width="700px"
      light
      overlay-color="black"
      v-model="dialog"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          v-bind="attrs"
          v-on="on"
          max-width="80px"
          text
          color="grey lighten-0"
          @click="getTravelComment()"
        >
          <v-icon>mdi-comment-text</v-icon>
          <v-text class="ms-2"> {{ travelInfo.commentCount }} </v-text>
        </v-btn>
      </template>
      <v-card>
        <v-card flat>
          <v-card-title>
            <span class="text-h5">Comments</span>
            <v-spacer></v-spacer>
            <v-btn @click="dialog = false" icon>
              <v-icon>mdi-close</v-icon>
            </v-btn>
          </v-card-title>
        </v-card>
        <v-card flat>
          <v-container fluid>
            <v-textarea
              clearable
              outlined
              clear-icon="mdi-close-circle"
              label="Comment"
              :rules="rules"
              v-model="content"
              auto-grow
              hide-details
            ></v-textarea>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="postTravelComment()">
                작성
              </v-btn>
            </v-card-actions>
          </v-container>
        </v-card>
        <v-divider class="pa-0 ma-0"></v-divider>
        <v-card>
          <v-container max-height="3000px">
            <v-row dense>
              <div v-for="comment in commentList" :key="comment.commentId">
                <div class="d-flex flex-no-wrap">
                  <v-card flat>
                    <v-avatar
                      color="grey"
                      size="128px"
                      style="position: relative"
                      class="ma-3 pa-3"
                    >
                      <img
                        v-bind:src="
                          'data:image/jpeg;base64,' + comment.profileImage
                        "
                      />
                    </v-avatar>
                  </v-card>
                  <v-card
                    flat
                    class="mx-4 my-0 pa-0"
                    style="position: relative"
                    width="450px"
                  >
                    <v-row class="pa-0 ma-0">
                      <v-text style="font-size: 20px; position: relative">
                        @{{ comment.nickname }}
                      </v-text>
                    </v-row>
                    <v-row class="pa-0 ma-0 my-2">
                      <v-text style="position: relative">
                        {{ comment.content }}
                      </v-text>
                    </v-row>
                  </v-card>
                </div>
                <v-divider width="100%" class="mb-2"></v-divider>
              </div>
            </v-row>
          </v-container>
        </v-card>
      </v-card>
    </v-dialog>
  </v-row>
</template>
<script>
import { mapGetters } from "vuex";
import { getComment, postComment } from "../api/travel";
import store from "../store/index";

export default {
  name: "CommentComp",
  data: () => ({
    rules: [(v) => v.length <= 100 || "댓글은 200자 이내로 작성해주세요."],
    content: "",
    dialog: false,
  }),
  methods: {
    async postTravelComment() {
      const data = { content: this.content };
      const comment = await postComment(this.travelInfo.travelId, data);
      this.content = "";
      this.getTravelComment();
    },
    async getTravelComment() {
      const response = await getComment(this.travelInfo.travelId);
      let data = [];
      for (let index = 0; index < response.data.length; index++) {
        let commentId = response.data[index].commentId;
        let content = response.data[index].content;
        let isMyComment = response.data[index].isMyComment;
        let nickname = response.data[index].nickname;
        let profileImage = response.data[index].profileImage;
        data.push({ commentId, content, isMyComment, nickname, profileImage });
      }
      store.commit("SET_COMMENT_LIST", data);
    },
  },
  created() {
    this.getTravelComment();
  },
  computed: {
    ...mapGetters(["travelInfo", "commentList"]),
  },
};
</script>

<style lang=""></style>
