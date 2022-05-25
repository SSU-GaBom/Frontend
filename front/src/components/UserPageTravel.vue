<template>
  <v-container>
    <v-row>
      <div v-for="travel in viewUserTravelList" :key="travel.title">
          <div class="d-flex flex-no-wrap">
            <v-card flat>
              <v-avatar class="ma-2" size="150" style="border-radius: 15%">
                <v-carousel hide-delimiters show-arrows-on-hover height="150">
                  <v-carousel-item
                    v-for="img in travel.pinList[0].images"
                    :key="img"
                    :src="'data:image/png;base64,' + img.base64Image"
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
                @click="toProfilePage(travel.userNickName)"
              >
                <v-icon fixed> mdi-account </v-icon>
                <v-text class="ms-2">
                  {{ travel.userNickname }}
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
                  {{ travel.startDate }} ~ {{ travel.endDate }}
                </v-text>
              </v-card-text>

              <div class="row mx-5 my-2 py-0">
                <div>
                  <v-icon color="blue lighten-0">mdi-thumb-up</v-icon>
                  <v-text class="ms-2" style="color: #2196f3">{{
                    travel.likedCount
                  }}</v-text>
                </div>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <div>
                  <v-icon color="red lighten-0">mdi-cards-heart</v-icon>
                  <v-text class="ms-2" style="color: #f44336">{{
                    travel.zzimCount
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
  data: () => ({
      
  }),
  computed: {
    ...mapGetters([
      "viewUserTravelList",
      "viewUserTravelImages"
    ]),
  },
};
</script>

<style>
#link {
  color: black;
  text-decoration: none;
}
</style>
