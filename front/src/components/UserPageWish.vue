<template>
<<<<<<< HEAD
  <v-card class="mx-auto" tile>
    <v-sheet class="mx-auto" height="200px" elevation="8" max-width="800">
      <v-list-item one-line>
        <v-list-item-content>
          <v-list-item-title class="headline text-left"
            >찜 목록</v-list-item-title
          >
        </v-list-item-content>
      </v-list-item>

      <v-row class="mx-2 my-2">
        <v-btn
          outlined
          height="100px"
          weight="100px"
          v-bind="attrs"
          v-on="on"
          darks
          text
        >
          <v-icon>mdi-marker</v-icon>
          <router-link id="link" to="/travel/content"> 게시글 </router-link>
        </v-btn>
        &nbsp;
        <v-btn
          outlined
          height="100px"
          weight="100px"
          v-bind="attrs"
          v-on="on"
          darks
          text
        >
          <v-icon>mdi-marker</v-icon>
          <router-link id="link" to="/travel/content"> 게시글 </router-link>
        </v-btn>
        &nbsp;
        <v-btn
          outlined
          height="100px"
          weight="100px"
          v-bind="attrs"
          v-on="on"
          darks
          text
        >
          <v-icon>mdi-marker</v-icon>
          <router-link id="link" to="/travel/content"> 게시글 </router-link>
        </v-btn>
        &nbsp;
        <v-btn
          outlined
          height="100px"
          weight="100px"
          v-bind="attrs"
          v-on="on"
          darks
          text
        >
          <v-icon>mdi-marker</v-icon>
          <router-link id="link" to="/travel/content"> 게시글 </router-link>
        </v-btn>
        &nbsp;
      </v-row>
    </v-sheet>
  </v-card>
=======
  <v-sheet class="mx-auto" elevation="8" max-width="800">
    <v-list-item one-line>
      <v-list-item-content>
        <v-list-item-title class="headline text-left">
          여행 목록
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
                  {{ items[0].author }}
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
>>>>>>> c8a368701192bec0b1d91b2570f7e2cddbeed586
</template>

<script>
export default {
  data: () => ({
    model: null,
<<<<<<< HEAD
=======
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
>>>>>>> c8a368701192bec0b1d91b2570f7e2cddbeed586
  }),
};
</script>

<<<<<<< HEAD
<style></style>
=======
<style>
#link {
  color: black;
  text-decoration: none;
}
</style>
>>>>>>> c8a368701192bec0b1d91b2570f7e2cddbeed586
