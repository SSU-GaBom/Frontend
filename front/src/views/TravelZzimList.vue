<template>
  <div id="main" class="my-3">
    <travel-zzim />
  </div>
</template>

<script>
import { getZzimTravel } from "../api/travel";
import { mapGetters } from "vuex";
import TravelZzim from "../components/TravelZzim.vue";
import store from "../store/index";
export default {
  name: "TravelLikeList",
  data() {
    return {};
  },
  methods: {
    async fetchZzimTravelList() {
      console.log("fetchZzimList");
      const response = await getZzimTravel(this.myUserId);
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
      store.commit("SET_ZZIM_LIST", data);
    },
  },
  computed: {
    ...mapGetters(["myUserId", "myZzimList"]),
  },
  created() {
    this.fetchZzimTravelList();
  },
  components: {
    TravelZzim,
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
