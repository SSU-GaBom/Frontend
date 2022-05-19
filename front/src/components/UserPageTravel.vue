<template>
  <v-sheet class="mx-auto" elevation="8" max-width="800">
    <v-list-item one-line>
      <v-list-item-content>
        <v-list-item-title class="headline text-left">
          내가 간 여행 목록
        </v-list-item-title>
      </v-list-item-content>
    </v-list-item>

    <v-slide-group
      v-model="model"
      class="pt-4 pb-4"
      active-class="success"
      show-arrows
    >
      <v-slide-item v-for="n in 15" :key="n">
        <v-row class="ma-1">
          <v-btn
            outlined
            height="100px"
            weight="100px"
            v-bind="attrs"
            v-on="on"
            darks
            text
          >
            <router-link id="link" to="/travel/content">
              <v-card-text class="row mx-0 py-0 pb-0">
                <v-icon fixed size="1"> mdi-account </v-icon>
                <v-text class="ms-2" style="font-size: 1px">
                  {{ travelList[0].title }}
                  <!--                  {{ items[0].author }}-->
                </v-text>
              </v-card-text>
              <v-card-text class="row mx-0 py-0 pb-0">
                <v-icon fixed size="1"> mdi-map-outline </v-icon>
                <v-text class="ms-2" style="font-size: 1px">
                  {{ items[0].province }} {{ items[0].city }}
                </v-text>
              </v-card-text>
              <v-card-text class="row mx-0 py-0 pb-0">
                <v-icon fixed size="1"> mdi-calendar </v-icon>
                <v-text class="ms-2" style="font-size: 1px">
                  {{ items[0].s_date }} ~ {{ items[0].e_date }}
                </v-text>
              </v-card-text>
            </router-link>
          </v-btn>
        </v-row>
      </v-slide-item>
    </v-slide-group>
  </v-sheet>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  data: () => ({
    model: null,
    items: [
      {
        author: "Username1",
        province: "제주특별자치도",
        city: "",
        s_date: "2022-05-02",
        e_date: "2022-05-04",
      },
      {
        author: "Username2",
        province: "경상남도",
        city: "김해시",
        s_date: "2021-04-02",
        e_date: "2022-04-07",
      },
      {
        author: "Username3",
        province: "강원도",
        city: "영월군",
        s_date: "2021-01-01",
        e_date: "2021-02-04",
      },
    ],
  }),
  computed: {
    ...mapGetters([
      // 'nickName',
      // 'followerCount',
      // 'followingCount',
      // 'profileImage',
      // 'myNickName',
      "travelList",
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
