<template>
  <div id="main" class="my-3">
    <travel-comp />
  </div>
</template>

<script>
import { getTravel } from "../api/travel";
import TravelComp from "../components/TravelComp.vue";
import { mapGetters } from "vuex";
import store from "../store/index";
export default {
  name: "TravelList",
  data() {
    return {};
  },
  methods: {
    async fetchTravelList() {
      const response = await getTravel();
      console.log(response.data);
      let data = [];
      for (let index = 0; index < response.data.content.length; index++) {
        let travelId = response.data.content[index].travelId;
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
      console.log(data);
      store.commit("SET_TRAVEL_LIST", data);
    },
  },
  computed: {
    ...mapGetters(["ttravelList"]),
  },
  created() {
    this.fetchTravelList();
  },
  components: {
    TravelComp,
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
