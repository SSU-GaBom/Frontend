<template>
  <v-container>
    <v-row dense>
      <v-col v-model="items">
        <!-- <v-card :color="items.color" outlined> -->
        <div class="d-flex flex-no-wrap">
          <v-card flat>
            <v-avatar class="ma-2" size="150" style="border-radius: 15%">
              <v-carousel hide-delimiters show-arrows-on-hover height="150">
                <v-carousel-item
                  v-for="(image, i) in images"
                  :key="i"
                  :src="image.src"
                  reverse-transition="fade-transition"
                  transition="fade-transition"
                >
                </v-carousel-item>
              </v-carousel>
            </v-avatar>
          </v-card>

          <v-card flat>
            <v-card-title class="row mx-0 pb-0" style="font-size: 100%">
              {{ items.title }}
            </v-card-title>
            <v-divider class="row mx-4"></v-divider>
            <v-card-text class="row mx-0 py-2 pb-0">
              <v-icon fixed> mdi-account </v-icon>
              <v-text class="ms-2">
                {{ items.author }}
              </v-text>
            </v-card-text>
            <v-card-text class="row mx-0 py-1 pb-0">
              <v-icon fixed> mdi-map-outline </v-icon>
              <v-text class="ms-2">
                {{ items.province }} {{ items.city }}
              </v-text>
            </v-card-text>
            <v-card-text class="row mx-0 py-1 pb-0">
              <v-icon fixed> mdi-calendar </v-icon>
              <v-text class="ms-2">
                {{ items.s_date }} ~ {{ items.e_date }}
              </v-text>
            </v-card-text>
            <v-card-actions>
              <v-btn
                v-if="items.author === 'Ellie Goulding'"
                fab
                icon
                height="40px"
                right
                width="40px"
              >
                <v-icon>mdi-play</v-icon>
              </v-btn>
            </v-card-actions>

            <div class="row ma-0 mx-3 pa-0">
              <v-btn icon color="blue lighten-0">
                <v-icon>mdi-thumb-up</v-icon>
              </v-btn>
              <v-btn icon color="red lighten-0">
                <v-icon>mdi-cards-heart</v-icon>
              </v-btn>
            </div>
          </v-card>

          <v-card flat max-width="10%"> </v-card>
        </div>
        <!-- </v-card> -->
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import {getTravelList} from '../api/travel'

export default {
  data() {
    return {
      images: [
        {
          src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        },
        {
          src: "https://cdn.vuetifyjs.com/images/carousel/sky.jpg",
        },
        {
          src: "https://cdn.vuetifyjs.com/images/carousel/bird.jpg",
        },
        {
          src: "https://cdn.vuetifyjs.com/images/carousel/planet.jpg",
        },
      ],
      items: {
        title: "올해 최고의 제주도 여행 !!!",
        author: "Username",
        recommendNum: 293,
        wishNum: 12,
        province: "제주특별자치도",
        city: "",
        s_date: "2022-05-02",
        e_date: "2022-05-04",
        budget: 500000,
        transport: "렌트카/자가용",
        text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
      },
    };
  },
  methods: {
    async fetchTravelList(){
      console.log("fetchTravelList")
      const response = await getTravelList()
      console.log(response)
    }
  },
  created() {
    this.fetchTravelList()
  },
};
</script>

<style scoped></style>

