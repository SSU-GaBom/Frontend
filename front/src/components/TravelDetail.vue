<template>
  <v-card
    :loading="loading"
    id="main"
    style="width: 100%; height: 116%; position: relative; overflow: hidden"
    tile
  >
    <!-- 여행 타이틀 -->
    <v-toolbar color="green" height="50px" dark>
      <v-btn icon @click="moveback()">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <v-toolbar-title class="px-0 py-4">{{
        travelInfo.title
      }}</v-toolbar-title>
    </v-toolbar>
    <v-card-text class="mx-auto">
      <!-- 작성자, 추천 수, 저장 수, 댓글 수 -->
      <v-row align="center" class="mx-0 my-0">
        <v-icon size="28px" color="black darken-4">mdi-account</v-icon>

        <v-btn max-width="120px" text @click="toProfilePage()">
          <v-text style="text-decoration: underline">{{
            travelInfo.userNickname
          }}</v-text>
        </v-btn>

        <div v-if="travelInfo.isLike === true">
          <v-btn max-width="80px" text color="blue lighten-0">
            <v-icon>mdi-thumb-up</v-icon>
            <v-text class="ms-2">{{ travelInfo.likedCount }}</v-text>
          </v-btn>
        </div>
        <div v-else>
          <v-btn max-width="80px" text color="blue lighten-0">
            <v-icon>mdi-thumb-up-outline</v-icon>
            <v-text class="ms-2">{{ travelInfo.likedCount }}</v-text>
          </v-btn>
        </div>

        <div v-if="travelInfo.isZzim === true">
          <v-btn max-width="80px" text color="red lighten-0">
            <v-icon>mdi-cards-heart</v-icon>
            <v-text class="ms-2">{{ travelInfo.zzimCount }}</v-text>
          </v-btn>
        </div>
        <div v-else>
          <v-btn max-width="80px" text color="red lighten-0">
            <v-icon>mdi-cards-heart-outline</v-icon>
            <v-text class="ms-2">{{ travelInfo.zzimCount }}</v-text>
          </v-btn>
        </div>
        <comment-comp></comment-comp>
      </v-row>

      <!-- 지역, 일정-->
      <v-row align="center" class="mx-0 my-4">
        <v-icon size="28px" color="black darken-4">mdi-map-outline</v-icon>
        <v-text class="ms-4"
          >{{ travelInfo.state }} {{ travelInfo.city }}</v-text
        >

        <v-icon fixed class="ms-12" color="black lighten-0">
          mdi-calendar
        </v-icon>
        <v-text class="ms-4"
          >{{ travelInfo.startDate }} ~ {{ travelInfo.endDate }}</v-text
        >
      </v-row>

      <!-- 경비, 교통 -->
      <v-row align="center" class="mx-0 my-5">
        <v-icon size="26px" color="black darken-4">mdi-cash-multiple</v-icon>
        <v-text class="ms-5">₩ {{ travelInfo.expense }}</v-text>

        <v-icon size="26px" class="ms-16" color="black lighten-0">
          mdi-train-car
        </v-icon>
        <v-text class="ms-4">{{ travelInfo.transportation }}</v-text>
      </v-row>
    </v-card-text>
    <v-divider></v-divider>

    <v-card-title>
      <b>여행소개글</b>
    </v-card-title>
    <v-card-text class="mx-0">
      <v-row align="center" class="mx-0 my-0">
        <v-text>{{ travelInfo.content }}</v-text>
      </v-row>
    </v-card-text>
    <v-divider></v-divider>

    <v-card-title>
      <b>핀 리스트</b>
    </v-card-title>
    <v-card-text class="mx-auto">
      <v-row align="center" class="mx-0 my-0">
        <v-btn plain>
          <v-icon color="red lighten-0"> mdi-map-marker </v-icon>1
        </v-btn>
        <v-text>{{ travelInfo.pinList[0].location.place_name }}</v-text>
      </v-row>
      <v-row align="center" class="mx-0 my-2">
        <v-btn plain>
          <v-icon color="yellow lighten-0"> mdi-map-marker </v-icon>2
        </v-btn>
        <v-text>숭실대학교</v-text>
      </v-row>
      <v-row align="center" class="mx-0 my-2">
        <v-btn plain>
          <v-icon color="green lighten-0"> mdi-map-marker </v-icon>3
        </v-btn>
        <v-text>숭실대학교</v-text>
      </v-row>
      <v-row align="center" class="mx-0 my-2">
        <v-btn plain>
          <v-icon color="blue lighten-0"> mdi-map-marker </v-icon>4
        </v-btn>
        <v-text>숭실대학교</v-text>
      </v-row>
      <v-row align="center" class="mx-0 my-2">
        <v-btn plain>
          <v-icon color="indigo lighten-0"> mdi-map-marker </v-icon>5
        </v-btn>
        <v-text>숭실대학교</v-text>
      </v-row>
      <v-row align="center" class="mx-0 my-2">
        <v-btn plain>
          <v-icon color="purple lighten-0"> mdi-map-marker </v-icon>6
        </v-btn>
        <v-text>숭실대학교</v-text>
      </v-row>
    </v-card-text>
  </v-card>
</template>

<script>
import { mapGetters } from "vuex";
import { postLiked } from "../api/travel";
import { postZzim } from "../api/travel";
import CommentComp from "./CommentComp.vue";
export default {
  data() {
    return {};
  },
  methods: {
    moveback() {
      window.history.back();
      // window.location.href = "/";
    },
    reserve() {
      this.loading = true;
      setTimeout(() => (this.loading = false), 2000);
    },
    toProfilePage() {
      this.$router.push({
        name: "userPage",
        params: { userNickName: this.author },
      });
    },
    async clickLiked(travelInfo) {
      const responseLike = await postLiked(
        travelInfo.likedCount,
        travelInfo.isLike,
        travelInfo.travelId
      );
      console.log(responseLike);
    },
    async clickZzim(travelInfo) {
      const responseZzim = await postZzim(
        travelInfo.zzimCount,
        travelInfo.isZzim,
        travelInfo.travelId
      );
      console.log(responseZzim);
    },
    async fetchTravelInfo() {
      // title,recommednNum,wishNum,province,city,s_Date,e_Date,budget,transport,text , pinList -> 지도에 표시!!
    },
  },
  created() {
    this.fetchTravelInfo();
  },
  computed: {
    ...mapGetters(["travelInfo"]),
  },
  components: {
    CommentComp,
  },
};
</script>

<style scoped>
#main {
  position: relative;
  width: 70%;
  height: 580px;
}
</style>
