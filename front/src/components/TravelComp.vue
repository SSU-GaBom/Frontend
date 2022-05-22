<template>
  <v-container>
    <v-row dense no-gutters>
      <v-col
        class="d-flex"
        style="position: relative; top: 6px"
        cols="8"
        sm="7"
      >
        <v-select
          v-model="province"
          :items="province_list"
          label="자치시/도"
          prepend-icon="mdi-map-search-outline"
          dense
        ></v-select>
        &nbsp;&nbsp;&nbsp;
        <v-select
          v-if="province === '경기도'"
          v-model="city"
          :items="gyeonggi_list"
          label="시/군"
          dense
        ></v-select>
        <v-select
          v-if="province === '강원도'"
          v-model="city"
          :items="gangwon_list"
          label="시/군"
          dense
        ></v-select>
        <v-select
          v-if="province === '충청북도'"
          v-model="city"
          :items="ccnorth_list"
          label="시/군"
          dense
        ></v-select>
        <v-select
          v-if="province === '충청남도'"
          v-model="city"
          :items="ccsouth_list"
          label="시/군"
          dense
        ></v-select>
        <v-select
          v-if="province === '경상북도'"
          v-model="city"
          :items="gsnorth_list"
          label="시/군"
          dense
        ></v-select>
        <v-select
          v-if="province === '경상남도'"
          v-model="city"
          :items="gssouth_list"
          label="시/군"
          dense
        ></v-select>
        <v-select
          v-if="province === '전라북도'"
          v-model="city"
          :items="jrnorth_list"
          label="시/군"
          dense
        ></v-select>
        <v-select
          v-if="province === '전라남도'"
          v-model="city"
          :items="jrsouth_list"
          label="시/군"
          dense
        ></v-select>
      </v-col>
      <v-col sm="6" md="4">
        <v-text-field class="pa-0" label="검색" v-model="title"></v-text-field>
      </v-col>
      <v-col sm="0" md="0" class="my-1">
        <v-btn plain icon @click="searchTitle()">
          <v-icon>mdi-magnify</v-icon>
        </v-btn>
      </v-col>
    </v-row>
    <v-row dense>
      <div v-for="travel in ttravelList" :key="travel.title">
        <!-- <v-card :color="items.color" outlined> -->
        <div v-if="province === ''">
          <div class="d-flex flex-no-wrap">
            <v-card flat>
              <v-avatar class="ma-2" size="150" style="border-radius: 15%">
                <v-carousel hide-delimiters show-arrows-on-hover height="150">
                  <v-carousel-item
                    v-for="img in travel.images"
                    :key="img"
                    :src="'data:image/png;base64,' + img"
                    reverse-transition="fade-transition"
                    transition="fade-transition"
                  >
                  </v-carousel-item>
                </v-carousel>
              </v-avatar>
            </v-card>
            <v-card flat>
              <v-btn
                class="mx-4 my-0 pa-0"
                plain
                text
                @click="toTravelDetail(travel.travelId)"
              >
                <v-text id="title" left>
                  <b>{{ travel.title }}</b>
                </v-text>
              </v-btn>
              <v-divider class="row mx-4 my-0"></v-divider>
              <v-btn
                class="row mx-4 my-0 pa-0"
                plain
                text
                @click="toProfilePage(travel.author)"
              >
                <v-icon fixed> mdi-account </v-icon>
                <v-text class="ms-2">
                  {{ travel.author }}
                </v-text>
              </v-btn>
              <v-card-text class="row mx-0 my-0 py-0 pb-0">
                <v-icon fixed> mdi-map-outline </v-icon>
                <v-text class="ms-2">
                  {{ travel.state }} {{ travel.city }}
                </v-text>
              </v-card-text>
              <v-card-text class="row mx-0 my-0 py-1 pb-0">
                <v-icon fixed> mdi-calendar </v-icon>
                <v-text class="ms-2">
                  {{ travel.s_date }} ~ {{ travel.e_date }}
                </v-text>
              </v-card-text>

              <div class="row mx-5 my-2 py-0">
                <div>
                  <v-icon color="blue lighten-0">mdi-thumb-up</v-icon>
                  <v-text class="ms-2" style="color: #2196f3">{{
                    travel.likedCnt
                  }}</v-text>
                </div>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <div>
                  <v-icon color="red lighten-0">mdi-cards-heart</v-icon>
                  <v-text class="ms-2" style="color: #f44336">{{
                    travel.zzimCnt
                  }}</v-text>
                </div>
              </div>
            </v-card>

            <v-card flat max-width="10%"> </v-card>
          </div>
        </div>
        <div v-else-if="province === travel.state">
          <div v-if="city === ''">
            <div class="d-flex flex-no-wrap">
              <v-card flat>
                <v-avatar class="ma-2" size="150" style="border-radius: 15%">
                  <v-carousel hide-delimiters show-arrows-on-hover height="150">
                    <v-carousel-item
                      v-for="img in travel.images"
                      :key="img"
                      :src="'data:image/png;base64,' + img"
                      reverse-transition="fade-transition"
                      transition="fade-transition"
                    >
                    </v-carousel-item>
                  </v-carousel>
                </v-avatar>
              </v-card>
              <v-card flat>
                <v-card-title class="row mx-0 pb-0" style="font-size: 100%">
                  {{ travel.title }}
                </v-card-title>
                <v-divider class="row mx-4"></v-divider>
                <v-card-text class="row mx-0 py-2 pb-0">
                  <v-icon fixed> mdi-account </v-icon>
                  <v-text class="ms-2">
                    {{ travel.author }}
                  </v-text>
                </v-card-text>
                <v-card-text class="row mx-0 py-1 pb-0">
                  <v-icon fixed> mdi-map-outline </v-icon>
                  <v-text class="ms-2">
                    {{ travel.state }} {{ travel.city }}
                  </v-text>
                </v-card-text>
                <v-card-text class="row mx-0 py-1 pb-0">
                  <v-icon fixed> mdi-calendar </v-icon>
                  <v-text class="ms-2">
                    {{ travel.s_date }} ~ {{ travel.e_date }}
                  </v-text>
                </v-card-text>
                <div class="row mx-5 my-5 pa-0">
                  <div>
                    <v-icon color="blue lighten-0">mdi-thumb-up</v-icon>
                    <v-text class="ms-2" style="color: #2196f3">{{
                      travel.likedCnt
                    }}</v-text>
                  </div>
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  <div>
                    <v-icon color="red lighten-0">mdi-cards-heart</v-icon>
                    <v-text class="ms-2" style="color: #f44336">{{
                      travel.zzimCnt
                    }}</v-text>
                  </div>
                </div>
              </v-card>

              <v-card flat max-width="10%"> </v-card>
            </div>
          </div>
          <div v-else-if="city === travel.city">
            <div class="d-flex flex-no-wrap">
              <v-card flat>
                <v-avatar class="ma-2" size="150" style="border-radius: 15%">
                  <v-carousel hide-delimiters show-arrows-on-hover height="150">
                    <v-carousel-item
                      v-for="img in travel.images"
                      :key="img"
                      :src="'data:image/png;base64,' + img"
                      reverse-transition="fade-transition"
                      transition="fade-transition"
                    >
                    </v-carousel-item>
                  </v-carousel>
                </v-avatar>
              </v-card>
              <v-card flat>
                <v-card-title class="row mx-0 pb-0" style="font-size: 100%">
                  {{ travel.title }}
                </v-card-title>
                <v-divider class="row mx-4"></v-divider>
                <v-card-text class="row mx-0 py-2 pb-0">
                  <v-icon fixed> mdi-account </v-icon>
                  <v-text class="ms-2">
                    {{ travel.author }}
                  </v-text>
                </v-card-text>
                <v-card-text class="row mx-0 py-1 pb-0">
                  <v-icon fixed> mdi-map-outline </v-icon>
                  <v-text class="ms-2">
                    {{ travel.state }} {{ travel.city }}
                  </v-text>
                </v-card-text>
                <v-card-text class="row mx-0 py-1 pb-0">
                  <v-icon fixed> mdi-calendar </v-icon>
                  <v-text class="ms-2">
                    {{ travel.s_date }} ~ {{ travel.e_date }}
                  </v-text>
                </v-card-text>
                <div class="row mx-5 my-5 pa-0">
                  <div>
                    <v-icon color="blue lighten-0">mdi-thumb-up</v-icon>
                    <v-text class="ms-2" style="color: #2196f3">{{
                      travel.likedCnt
                    }}</v-text>
                  </div>
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  <div>
                    <v-icon color="red lighten-0">mdi-cards-heart</v-icon>
                    <v-text class="ms-2" style="color: #f44336">{{
                      travel.zzimCnt
                    }}</v-text>
                  </div>
                </div>
              </v-card>

              <v-card flat max-width="10%"> </v-card>
            </div>
          </div>
        </div>
        <div v-else></div>
        <!-- </v-card> -->
        <v-divider class="py-1"></v-divider>
      </div>
    </v-row>
    <!-- <div class="text-center">
      <v-pagination
        v-model="currentpage"
        :length="calculateMaxPage"
        :total-visible="7"
        prev-icon="mdi-menu-left"
        next-icon="mdi-menu-right"
      ></v-pagination>
    </div> -->
  </v-container>
</template>

<script>

import { mapGetters } from "vuex";
import { getTitleTravel } from "../api/travel";
import { getTravelDetail } from "../api/travel";
import store from "../store/index";


export default {
  name: "TravelComp",
  data() {
    return {
      currentpage: 1,
      images: [
        "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        "https://cdn.vuetifyjs.com/images/carousel/sky.jpg",
        "https://cdn.vuetifyjs.com/images/carousel/bird.jpg",
        "https://cdn.vuetifyjs.com/images/carousel/planet.jpg",
      ],
      items: {},
      title: "",
      province: "",
      city: "",
      province_list: [
        "서울특별시",
        "부산광역시",
        "인천광역시",
        "대전광역시",
        "대구광역시",
        "광주광역시",
        "울산광역시",
        "세종특별자치시",
        "경기도",
        "강원도",
        "충청북도",
        "충청남도",
        "경상북도",
        "경상남도",
        "전라북도",
        "전라남도",
        "제주특별자치도",
      ],
      gyeonggi_list: [
        "수원시",
        "고양시",
        "용인시",
        "성남시",
        "화성시",
        "부천시",
        "남양주시",
        "안산시",
        "안양시",
        "평택시",
        "시흥시",
        "김포시",
        "파주시",
        "의정부시",
        "광주시",
        "하남시",
        "광명시",
        "군포시",
        "양주시",
        "구리시",
        "오산시",
        "안성시",
        "의왕시",
        "이천시",
        "포천시",
        "동두천시",
        "과천시",
        "여주시",
        "양평군",
        "가평군",
        "연천군",
      ],
      gangwon_list: [
        "원주시",
        "춘천시",
        "강릉시",
        "동해시",
        "속초시",
        "삼척시",
        "태백시",
        "홍천군",
        "철원군",
        "횡성군",
        "평창군",
        "정선군",
        "영월군",
        "인제군",
        "고성군",
        "양양군",
        "화천군",
        "양구군",
      ],
      ccnorth_list: [
        "청주시",
        "충주시",
        "제천시",
        "보은군",
        "옥천군",
        "영동군",
        "증평군",
        "진천군",
        "괴산군",
        "음성군",
        "단양군",
      ],
      ccsouth_list: [
        "천안시",
        "공주시",
        "보령시",
        "아산시",
        "서산시",
        "논산시",
        "계룡시",
        "당진시",
        "금산군",
        "부여군",
        "서천군",
        "청양군",
        "홍성군",
        "예산군",
        "태안군",
      ],
      gsnorth_list: [
        "포항시",
        "경주시",
        "김천시",
        "안동시",
        "구미시",
        "영주시",
        "영천시",
        "상주시",
        "문경시",
        "경산시",
        "군위군",
        "의성군",
        "청송군",
        "영양군",
        "영덕군",
        "청도군",
        "고령군",
        "성주군",
        "칠곡군",
        "예천군",
        "봉화군",
        "울진군",
        "울릉군",
      ],
      gssouth_list: [
        "창원시",
        "김해시",
        "진주시",
        "양산시",
        "거제시",
        "통영시",
        "사천시",
        "밀양시",
        "함안군",
        "거창군",
        "창녕군",
        "고성군",
        "하동군",
        "합천군",
        "남해군",
        "함양군",
        "산천군",
        "의령군",
      ],
      jrnorth_list: [
        "전주시",
        "익산시",
        "군산시",
        "정읍시",
        "김제시",
        "남원시",
        "완주군",
        "고창군",
        "부안군",
        "임실군",
        "순창군",
        "진안군",
        "무주군",
        "장수군",
      ],
      jrsouth_list: [
        "목포시",
        "여수시",
        "순천시",
        "나주시",
        "광양시",
        "담양군",
        "곡성군",
        "구례군",
        "고흥군",
        "보성군",
        "화순군",
        "장흥군",
        "강진군",
        "해남군",
        "영암군",
        "무안군",
        "함평군",
        "영광군",
        "장성군",
        "완도군",
        "진도군",
        "신안군",
      ],
    };
  },
  methods: {

    async toProfilePage(nickname) {
      this.$router.push({
        name: "userPage",
        params: { userNickName: nickname },
      });
    },
    async toTravelDetail(travelId) {
      // const response = await getTravelDetail(travelId);
      // console.log(response.data);
      // store.commit("SET_TRAVEL_DETAIL", response.data);
      this.$router.push({
        name: "travel-view",
        params: { travelContentId: travelId },
      });
    },
    async searchTitle() {
      const response = await getTitleTravel(this.title);
      console.log(response);
      let data = [];
      for (let index = 0; index < response.data.content.length; index++) {
        let title = response.data.content[index].title;
        let author = response.data.content[index].userNickname;
        let state = response.data.content[index].state;
        let city = response.data.content[index].city;
        let s_date = response.data.content[index].startDate;
        let e_date = response.data.content[index].endDate;
        let likedCnt = response.data.content[index].likedCount;
        let zzimCnt = response.data.content[index].zzimCount;
        let images = [];
        for (
          let pinIdx = 0;
          pinIdx < response.data.content[index].pinList.length;
          pinIdx++
        ) {
          for (
            let imgIdx = 0;
            imgIdx < response.data.content[index].pinList[pinIdx].images.length;
            imgIdx++
          ) {
            images.push(
              response.data.content[index].pinList[pinIdx].images[imgIdx]
                .base64Image
            );
          }
        }
        data.push({
          title,
          author,
          state,
          city,
          s_date,
          e_date,
          likedCnt,
          zzimCnt,
          images,
        });
      }
      console.log(data);
      store.commit("SET_TRAVEL_LIST", data);
    },
  },
  computed: {
    ...mapGetters(["ttravelList"]),
    calculateMaxPage() {
      if (this.ttravelList.length % 4 === 0) {
        return this.ttravelList.length / 4;
      } else {
        return this.ttravelList.length / 4 + 1;
      }
    },
  },
  components: {},
};
</script>

<style scoped>
#title {
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 20px;
  max-width: 200px;
}
</style>

