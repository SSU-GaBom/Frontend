<template>
  <div id="main">
    <user-page-info></user-page-info>
    <user-page-travel id="content"></user-page-travel>
    <user-page-wish id="content"></user-page-wish>
  </div>
</template>

<script>
import UserPageInfo from "../components/UserPageInfo.vue";
import UserPageTravel from "../components/UserPageTravel.vue";
import UserPageWish from "../components/UserPageWish.vue";
import { getUserInfo } from "../api/profile";
import store from "../store/index";

export default {
  name: "UserView",
  data() {
    return {};
  },
  methods: {
    async fetchUserInfo() {
      console.log("fetchUserInfo");

      // console.log(this.$route.params.userNickName);
      // 닉네임을 타고 들어온경우
      if (this.$route.params.userNickName) {
        const response = await getUserInfo(this.$route.params.userNickName);
        console.log(response);

        const data = {
          nickName: response.data.data.nickName,
          userFollowerCount: response.data.data.userFollowerCount,
          userFollowingCount: response.data.data.userFollowingCount,
          profileImage: response.data.data.profileImage,
          myTravelList: response.data.data.myTravelList,
          // likedTravelList : response.data.data.likedTravelList
        };
        store.commit("SET_VIEWUSER", data);
      } else {
        // 마이페이지로 들어온경우
        const response = await getUserInfo(store.state.user.nickName);
        console.log(response.data);

        const data = {
          nickName: response.data.data.nickName,
          userFollowerCount: response.data.data.userFollowerCount,
          userFollowingCount: response.data.data.userFollowingCount,
          profileImage: response.data.data.profileImage,
          myTravelList: response.data.data.myTravelList,
          // likedTravelList : response.data.data.likedTravelList
        };
        store.commit("SET_VIEWUSER", data);
        console.log("SET_VIEWUSER finish");
      }
    },
  },
  created() {
    this.fetchUserInfo();
  },
  components: {
    UserPageInfo,
    UserPageTravel,
    UserPageWish,
  },
};
</script>

<style scoped>
#main {
  position: relative;
  width: 30%;
  height: 580px;
}
#content {
  margin: 10px auto;
}
</style>
