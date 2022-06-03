<template>
  <div id="main" class="my-3">
    <travel-like />
  </div>
</template>

<script>
import { getLikeTravel } from "../api/travel";
import { mapGetters } from "vuex";
import TravelLike from "../components/TravelLike.vue";
import store from "../store/index";
export default {
  name: "TravelLikeList",
  data() {
    return {};
  },
  methods: {
    async fetchLikeTravelList() {
      console.log("fetchLikeList");
      const response = await getLikeTravel(this.myUserId);
      let data = [];
      for (let index = 0; index < response.data.length; index++) {
        let travelId = response.data[index].travelId;
        let title = response.data[index].title;
        let author = response.data[index].userNickname;
        let state = response.data[index].state;
        let city = response.data[index].city;
        let s_date = response.data[index].startDate;
        let e_date = response.data[index].endDate;
        let likedCnt = response.data[index].likedCount;
        let zzimCnt = response.data[index].zzimCount;
        let images = [];
        for (let idx = 0; idx < response.data[index].pinList.length; idx++) {
          for (
            let imgIdx = 0;
            imgIdx < response.data[index].pinList[idx].images.length;
            imgIdx++
          ) {
            images.push(
              response.data[index].pinList[idx].images[imgIdx].base64Image
            );
          }
        }
        data.push({
          travelId,
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
      store.commit("SET_LIKE_LIST", data);
    },
  },
  computed: {
    ...mapGetters(["myUserId", "myLikeList"]),
  },
  created() {
    this.fetchLikeTravelList();
  },
  components: {
    TravelLike,
  },
};
</script>

<style scoped>
#main {
  position: relative;
  width: 30%;
  max-width: 500px;
  height: 780px;
}
</style>
