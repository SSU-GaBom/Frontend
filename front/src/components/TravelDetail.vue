<template>
  <v-card
    :loading="loading"
    id="main"
    style="width: 100%; height: 116%; position: relative; overflow: hidden"
    tile
  >
    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      ></v-progress-linear>
    </template>

    <!-- 여행 타이틀 -->
    <v-card-title>
      <b>{{ title }}</b>
    </v-card-title>

    <v-card-text class="mx-auto">
      <!-- 작성자, 추천 수, 저장 수, 댓글 수 -->
      <v-row align="center" class="mx-0 my-0">
        <v-icon size="28px" color="black darken-4">mdi-account</v-icon>
<<<<<<< HEAD
        <v-btn max-width="120px" text>
=======
        <v-btn max-width="120px" text @click="toProfilePage()">
>>>>>>> c8a368701192bec0b1d91b2570f7e2cddbeed586
          <v-text style="text-decoration: underline">{{ author }}</v-text>
        </v-btn>

        <v-btn max-width="80px" text color="blue lighten-0">
          <v-icon>mdi-thumb-up</v-icon>
          <v-text class="ms-2">{{ recommendNum }}</v-text>
        </v-btn>

<<<<<<< HEAD
        <v-btn text color="red lighten-0">
=======
        <v-btn max-width="80px" text color="red lighten-0">
>>>>>>> c8a368701192bec0b1d91b2570f7e2cddbeed586
          <v-icon>mdi-cards-heart</v-icon>
          <v-text class="ms-2">{{ wishNum }}</v-text>
        </v-btn>
        <comment-comp></comment-comp>
      </v-row>

      <!-- 지역, 일정-->
      <v-row align="center" class="mx-0 my-4">
        <v-icon size="28px" color="black darken-4">mdi-map-outline</v-icon>
        <v-text class="ms-4">{{ province }} {{ city }}</v-text>

        <v-icon fixed class="ms-12" color="black lighten-0">
          mdi-calendar
        </v-icon>
        <v-text class="ms-4">{{ s_date }} ~ {{ e_date }}</v-text>
      </v-row>

      <!-- 경비, 교통 -->
      <v-row align="center" class="mx-0 my-5">
        <v-icon size="26px" color="black darken-4">mdi-cash-multiple</v-icon>
        <v-text class="ms-5">₩ {{ budget }}</v-text>

        <v-icon size="26px" class="ms-16" color="black lighten-0">
          mdi-train-car
        </v-icon>
        <v-text class="ms-4">{{ transport }}</v-text>
      </v-row>
    </v-card-text>
    <v-divider></v-divider>

    <v-card-text class="mx-0">
      <v-row align="center" class="mx-0 my-0">
        <v-text>여행소개글<br /><br />{{ text }}</v-text>
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
        <v-text>숭실대학교</v-text>
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
import CommentComp from "./CommentComp.vue";
export default {
  data: () => ({
    title: "올해 최고의 제주도 여행 !!!",
<<<<<<< HEAD
    author: "username",
=======
    author: "ex1",
>>>>>>> c8a368701192bec0b1d91b2570f7e2cddbeed586
    recommendNum: 293,
    wishNum: 12,
    province: "제주특별자치도",
    city: "",
    s_date: "2022-05-02",
    e_date: "2022-05-04",
    budget: 500000,
    transport: "렌트카/자가용",
    text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
    loading: false,
    selection: 1,
  }),
  methods: {
    reserve() {
      this.loading = true;
<<<<<<< HEAD

      setTimeout(() => (this.loading = false), 2000);
    },
=======
      setTimeout(() => (this.loading = false), 2000);
    },
    toProfilePage(){
      this.$router.push({ name: 'userPage', params: {userNickName: this.author} })
    },
    async fetchTravelInfo(){
      // title,recommednNum,wishNum,province,city,s_Date,e_Date,budget,transport,text , pinList -> 지도에 표시!!
    }
  },
  created() {
    this.fetchTravelInfo();
>>>>>>> c8a368701192bec0b1d91b2570f7e2cddbeed586
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
