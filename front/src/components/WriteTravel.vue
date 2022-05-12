<template>
  <v-app>
    <v-main>
      <v-container>
        <v-form ref="form" @submit.prevent="onSubmitForm">
          <v-row dense no-gutters>
            <v-col class="d-flex" cols="12" sm="8">
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
                sm="5"
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
          </v-row>

          <v-row dense no-gutters>
            <v-col cols="12" lg="4">
              <v-menu
                ref="menu1"
                v-model="menu1"
                :close-on-content-click="false"
                :return-value.sync="s_date"
                transition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="s_date"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-model="s_date"
                  no-title
                  scrollable
                  :max="e_date"
                >
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="menu1 = false"
                    >Cancel</v-btn
                  >
                  <v-btn text color="primary" @click="s_date_search(s_date)"
                    >OK</v-btn
                  >
                </v-date-picker>
              </v-menu>
            </v-col>
            &nbsp;&nbsp;&nbsp;
            <v-col cols="12" lg="4">
              <v-menu
                ref="menu2"
                v-model="menu2"
                :close-on-content-click="false"
                :return-value.sync="e_date"
                transition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="e_date"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-model="e_date"
                  no-title
                  scrollable
                  :min="s_date"
                  :max="date"
                >
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="menu2 = false"
                    >Cancel</v-btn
                  >
                  <v-btn text color="primary" @click="e_date_search(e_date)"
                    >OK</v-btn
                  >
                </v-date-picker>
              </v-menu>
            </v-col>
          </v-row>

          <v-row dense no-gutters>
            <v-col cols="12" sm="6">
              <v-textarea
                v-model="title"
                class="mx-0"
                label="여행 타이틀"
                rows="1"
                prepend-icon="mdi-format-title"
              ></v-textarea>
            </v-col>
          </v-row>

          <v-row dense no-gutters>
            <v-col cols="12" sm="6">
              <v-textarea
                v-model="budget"
                class="mx-0"
                label="여행 경비(원)"
                rows="1"
                prepend-icon="mdi-currency-krw"
              ></v-textarea>
            </v-col>
          </v-row>

          <v-row>
            <v-radio-group
              v-model="transport"
              row
              style="padding-left: 12px"
              prepend-icon="mdi-train-car"
            >
              <v-radio label="렌트카/자가용" value="렌트카/자가용"></v-radio>
              <v-radio label="대중교통" value="대중교통"></v-radio>
            </v-radio-group>
          </v-row>

          <v-row style="padding: 20px">
            <v-textarea
              v-model="text"
              label="여행 소개글"
              outlined
              rows="10"
            ></v-textarea>
          </v-row>

          <template v-if="travelList">
            <v-simple-table>
              <template v-slot:default>
                <thead>
                  <tr>
                    <th class="text-left">Place_Name</th>
                    <th class="text-left">Picture</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="travel in travelList" :key="travel.place_name">
                    <td>{{ travel.place_name }}</td>
                    <td><card-comp></card-comp></td>
                  </tr>
                </tbody>
              </template>
            </v-simple-table>
          </template>
          <br />
          <v-btn width="100px" style="margin-bottom: 30px" type="submit"
            >작성</v-btn
          >
          <v-btn
            width="100px"
            style="margin-left: 30px; margin-bottom: 30px"
            @click="moveback"
            >취소</v-btn
          >
        </v-form>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import axios from "axios";
import { writeTravel } from "../api/travel";
import { mapGetters } from "vuex";
import store from "../store/index";
import CardComp from "./CardComp.vue";

export default {
  data() {
    return {
      title: "",
      text: "",
      budget: "",
      transport: "",
      date: new Date().toISOString().substr(0, 10),
      s_date: new Date().toISOString().substr(0, 10),
      e_date: new Date().toISOString().substr(0, 10),
      menu1: false,
      menu2: false,
      province: "",
      city: "",
      image: "",
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
    async onSubmitForm() {
      if (this.$refs.form.validate()) {
        const pinList = [];
        var reader = new FileReader();

        //핀 만들기
        for (let i = 0; i < this.travelList.length; i++) {
          //사진 + 글
          // let formData = new FormData();

          // for (let j = 0; j < this.cardList[i].images.length; j++) {
          //   //image to base64 
          //   console.log(this.cardList[i].images[j].substr(23))
          // }
          
          let pin = {
            location : this.travelList[i],
            locationContent : this.cardList[i].text,
            images : this.cardList[i].images
          }

          // //장소
          // let pin = {
          //   location: this.travelList[i],
          //   card: formData,
          // };

          pinList.push(pin);
          
        }

        const travelDto = {
          // writer : this.writer,
          writer: this.writer,
          title: this.title,
          content: this.text,
          state: this.province,
          city: this.city,
          startDate: this.s_date,
          endDate: this.e_date,
          expense: this.budget,
          transport: this.transport,
          pinList: pinList,
        };

        const response = await writeTravel(travelDto);
        console.log(response);
      }

      // if (this.$refs.form.validate()) {
      //   // 위에 써준 rules를 만족하면 실행
      //   axios({
      //     url: "http://127.0.0.1:52273/content/write/",
      //     method: "POST",
      //     data: {
      //       boardnum: this.$route.params.id,
      //       writer: this.writer,
      //       title: this.title,
      //       text: this.text,
      //     },
      //   })
      //     .then((res) => {
      //       alert(res.data.message);
      //       window.history.back();
      //     })
      //     .catch((err) => {
      //       alert(err);
      //     });
      // }
    },
    moveback() {
      window.history.back();
    },
    movetomain() {
      window.location.href = "/";
    },
    s_date_search(v) {
      this.s_date = v;
      this.menu1 = false;
      this.$refs.menu1.save(v);
    },
    e_date_search(v) {
      this.e_date = v;
      this.menu2 = false;
      this.$refs.menu2.save(v);
    },
  },
  computed: {
    dateRangeText() {
      return this.dates.join(" ~ ");
    },
    ...mapGetters({
      writer: "myNickName",
      travelList: "writeTravelList",
      cardList: "writeCardList",
    }),
  },
  components: {
    CardComp,
  },
};
</script>
