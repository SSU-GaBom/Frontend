<template>
  <div id="main">
    <user-page-info></user-page-info>
    <user-page-travel id="content"></user-page-travel>
    <!-- <user-page-wish id="content"></user-page-wish> -->
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
      console.log("UserView.fetchUserInfo()");

      // console.log(this.$route.params.userNickName);
      // 닉네임을 타고 들어온경우
      if (this.$route.params.userNickName) {
        try {
          const response = await getUserInfo(this.$route.params.userNickName);
          if(response.data.code === 0){
            const data = {
            nickName: response.data.data.nickName,
            userFollowerCount: response.data.data.userFollowerCount,
            userFollowingCount: response.data.data.userFollowingCount,
            profileImage: response.data.data.profileImage,
            myTravelList: response.data.data.myTravelList,
            travelImageList : response.data.data.myTravelimages
          
          };
          
          store.commit("SET_VIEWUSER", data);
          console.log("SET_VIEWUSER finish");
          }
          
        } catch (error) {
          console.log(error)
          return
        }

      } else {
        // 마이페이지로 들어온경우
        try {
          const response = await getUserInfo(store.state.user.nickName);
          if(response.data.code === 0){
            const data = {
            nickName: response.data.data.nickName,
            userFollowerCount: response.data.data.userFollowerCount,
            userFollowingCount: response.data.data.userFollowingCount,
            profileImage: response.data.data.profileImage,
            myTravelList: response.data.data.myTravelList,
            travelImageList : response.data.data.myTravelimages
          
          };
          
          store.commit("SET_VIEWUSER", data);
          console.log("SET_VIEWUSER finish");
          }
          
        } catch (error) {
          console.log(error)
          return
        }
        
      }
    },
  },
  created() {
    this.fetchUserInfo();
  },
  components: {
    UserPageInfo,
    UserPageTravel,
    
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
