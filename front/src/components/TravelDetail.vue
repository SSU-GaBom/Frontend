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
      <b>{{ detailTravelInfo.title }}</b>
    </v-card-title>

    <v-card-text class="mx-auto">
      <!-- 작성자, 추천 수, 저장 수, 댓글 수 -->
      <v-row align="center" class="mx-0 my-0">
        <v-icon size="28px" color="black darken-4">mdi-account</v-icon>

        <v-btn max-width="120px" text @click="toProfilePage()">
          <v-text style="text-decoration: underline">{{ detailTravelInfo.userId }}</v-text>
        </v-btn>

        <v-btn max-width="80px" text color="blue lighten-0">
          <v-icon>mdi-thumb-up</v-icon>
          <v-text class="ms-2">{{ recommendNum }}</v-text>
        </v-btn>


        <v-btn max-width="80px" text color="red lighten-0">

          <v-icon>mdi-cards-heart</v-icon>
          <v-text class="ms-2">{{ wishNum }}</v-text>
        </v-btn>
        <comment-comp></comment-comp>
      </v-row>

      <!-- 지역, 일정-->
      <v-row align="center" class="mx-0 my-4">
        <v-icon size="28px" color="black darken-4">mdi-map-outline</v-icon>
        <v-text class="ms-4">{{ detailTravelInfo.state }} {{ detailTravelInfo.city }}</v-text>

        <v-icon fixed class="ms-12" color="black lighten-0">
          mdi-calendar
        </v-icon>
        <v-text class="ms-4">{{ detailTravelInfo.startDate }} ~ {{ detailTravelInfo.endDate }}</v-text>
      </v-row>

      <!-- 경비, 교통 -->
      <v-row align="center" class="mx-0 my-5">
        <v-icon size="26px" color="black darken-4">mdi-cash-multiple</v-icon>
        <v-text class="ms-5">₩ {{ detailTravelInfo.expense }}</v-text>

        <v-icon size="26px" class="ms-16" color="black lighten-0">
          mdi-train-car
        </v-icon>
        <v-text class="ms-4">{{ transport }}</v-text>
      </v-row>
    </v-card-text>
    <v-divider></v-divider>


    <v-card-title>
      <b>여행소개글</b>
    </v-card-title>
    <v-card-text class="mx-0">
      <v-row align="center" class="mx-0 my-0">
        <v-text>{{ detailTravelInfo.content }}</v-text>
      </v-row>
    </v-card-text>
    <v-divider></v-divider>

    <!-- <v-img height="200px" v-bind:src="'data:image/jpeg;base64,'+ this.image" />
    <v-divider></v-divider> -->

    <v-card-title>
      <b>핀 리스트</b>
    </v-card-title>
    <v-card-text class="mx-auto">
      <template v-if="detailTravelInfo">
            <v-simple-table>
              <template v-slot:default>
                <thead>
                  <tr>
                    <th class="text-center">
                      Place_Name
                    </th>
                    <th class="text-center">
                      Picture
                    </th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="pin in detailTravelInfo.pinList" :key="pin.location.place_name">
                    <td>{{ pin.location.place_name }}</td>
                    <td><card-view-comp :pin="pin"></card-view-comp></td>
                  </tr>
                </tbody>
              </template>
            </v-simple-table>
          </template>
    </v-card-text>
  </v-card>
</template>

<script>
import CommentComp from "./CommentComp.vue";
import CardViewComp from './CardViewComp.vue'
import {getTravelInfo} from '../api/travel'
import store from '../store/index'
import {mapGetters} from 'vuex'

export default {
  data: () => ({
    // title: "올해 최고의 제주도 여행 !!!",
    // image : null,
    author: "ex1",
    recommendNum: 293,
    wishNum: 12,
    // province: "제주특별자치도",
    // city: "",
    // s_date: "2022-05-02",
    // e_date: "2022-05-04",
    // budget: 500000,
    transport: "렌트카/자가용",
    // text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
    loading: false,
    selection: 1,
  }),
  methods: {
    reserve() {
      this.loading = true;

      setTimeout(() => (this.loading = false), 2000);
    },
    toProfilePage(){
      this.$router.push({ name: 'userPage', params: {userNickName: this.author} })
    },
    async fetchTravelInfo(){
      console.log("fetchTravelInfo")
      
      const response = await getTravelInfo(this.$route.params.travelId)
      store.commit('SET_DETAILTRAVELINFO', response.data);
      
      // console.log(response.data.pinList[0].images[0].base64Image)
      // this.image = response.data.pinList[0].images[0].base64Image
      
      // title,recommednNum,wishNum,province,city,s_Date,e_Date,budget,transport,text , pinList -> 지도에 표시!!
    }
  },
  created() {
    this.fetchTravelInfo();
  },
  computed : {
      ...mapGetters([
        'detailTravelInfo'
      ])
  },
  components: {
    CommentComp,
    CardViewComp
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
