<template>
  <v-dialog v-model="dialog" width="1000" persistent>
    <template v-slot:activator="{ on: rank, attrs }">
      <v-btn v-bind="attrs" v-on="rank" icon darks>
        <v-icon>mdi-podium-silver</v-icon>
      </v-btn>
    </template>

    <v-card height="70vh">
      <v-tabs v-model="tab" fixed-tabs background-color="indigo" dark>
        <v-tab v-for="item in items" :key="item.tab">
          {{ item.tab }}
        </v-tab>
        <v-tab @click="dialog = false"> Close </v-tab>
      </v-tabs>

      <v-tabs-items v-model="tab">
        <v-tab-item v-for="item in items" :key="item.tab">
          <v-card v-if="item.tab === '좋아요 순위'" flat>
            <rank-comp-liked></rank-comp-liked>
          </v-card>
          <v-card v-else-if="item.tab === '찜 순위'" flat>
            <rank-comp-zzim></rank-comp-zzim>
          </v-card>
          <v-card v-else-if="item.tab === '팔로워 순위'" flat>
            <rank-comp-follower></rank-comp-follower>
          </v-card>
        </v-tab-item>
      </v-tabs-items>
    </v-card>
  </v-dialog>
</template>

<script>
import RankCompLiked from "./RankCompLiked.vue";
import RankCompZzim from "./RankCompZzim.vue";
import RankCompFollower from "./RankCompFollower.vue";
import { getRank } from "../api/travel";
import store from "../store/index";

export default {
  name: "RankComp",
  data() {
    return {
      tab: null,
      dialog: false,
      TravelZZimRank: null,
      FollwerRank: null,
      RecommendRank: null,
      items: [
        { tab: "좋아요 순위", content: "TravelZZimRank" },
        { tab: "찜 순위", content: "FollwerRank" },
        { tab: "팔로워 순위", content: "RecommendRank" },
      ],
    };
  },
  methods: {
    async fetchRankInfo() {
      console.log("fetchRank");
      const response = await getRank();
      let liked = [];
      let zzim = [];
      let follower = [];
      console.log(response);
      for (let i = 0; i < response.data.rankingRecommendDtos.length; i++) {
        let id = response.data.rankingRecommendDtos[i].recommendTravelId;
        let title = response.data.rankingRecommendDtos[i].recommendTravelTitle;
        let nums = response.data.rankingRecommendDtos[i].recommendNums;
        liked.push({ id, title, nums });
      }
      for (let i = 0; i < response.data.rankingZzimDtos.length; i++) {
        let id = response.data.rankingZzimDtos[i].zzimTravelid;
        let title = response.data.rankingZzimDtos[i].zzimTravelTitle;
        let nums = response.data.rankingZzimDtos[i].zzimNums;
        zzim.push({ id, title, nums });
      }
      for (let i = 0; i < response.data.rankingFollowDto.length; i++) {
        let nickname = response.data.rankingFollowDto[i].nickName;
        let followerNums = response.data.rankingFollowDto[i].followerNums;
        follower.push({ nickname, followerNums });
      }
      let data = { liked, zzim, follower };
      store.commit("SET_RANK", data);
    },
  },
  created() {
    this.fetchRankInfo();
  },
  components: {
    RankCompLiked,
    RankCompZzim,
    RankCompFollower,
  },
};
</script>

<style scoped></style>
