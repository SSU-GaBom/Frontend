<template>
  <v-container>
    <v-row dense>
      <v-card-title class="pa-0 mx-2 mb-2">
        <b>내가 찜한 TRAVEL LIST</b>
      </v-card-title>
    </v-row>
    <v-row dense>
      <div v-for="travel in myZzimList" :key="travel.title">
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
        </div>
        <v-divider class="py-1"></v-divider>
      </div>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "TravelZzim",
  data() {
    return {};
  },
  methods: {
    async toProfilePage(nickname) {
      this.$router.push({
        name: "userPage",
        params: { userNickName: nickname },
      });
    },
    async toTravelDetail(travelId) {
      this.$router.push({
        name: "travel-view",
        params: { travelContentId: travelId },
      });
    },
  },
  computed: {
    ...mapGetters(["myZzimList"]),
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
