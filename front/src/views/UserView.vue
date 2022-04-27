<template>
  <div id="main">
    <user-page-info></user-page-info>
    <user-page-travel id="content"></user-page-travel>
    <user-page-wish id="content"></user-page-wish>
  </div>

</template>

<script>
import UserPageInfo from '../components/UserPageInfo.vue'
import UserPageTravel from '../components/UserPageTravel.vue'
import UserPageWish from '../components/UserPageWish.vue'
import {getUserInfo} from '../api/profile'
import store from '../store/index'

export default {
  
  name: 'UserView',
  data() {
    return {
      
    }
  },
  methods: {
     async fetchUserInfo(){
        console.log(this.$route.query.nickName)
        // 닉네임을 타고 들어온경우
        if(this.$route.query.nickName){
          console.log("hihi2");
          const response = await getUserInfo(this.$route.query.nickName)

          store.state.viewUser.nickName = response.data.data.nickName
          store.state.viewUser.follower = response.data.data.follower
          store.state.viewUser.following = response.data.data.following
          store.state.viewUser.travelList = response.data.data.travelList
          store.state.viewUser.wishList = response.data.data.wishList

          // store.state.viewUser = response.data.data


        }else{ // 마이페이지로 들어온경우
          const response = await getUserInfo(store.state.user.nickName)

          store.state.viewUser.nickName = response.data.data.nickName
          store.state.viewUser.follower = response.data.data.follower
          store.state.viewUser.following = response.data.data.following
          store.state.viewUser.travelList = response.data.data.travelList
          store.state.viewUser.wishList = response.data.data.wishList

          // store.state.viewUser = response.data.data
        }
        
      },
  },
  created() {
      this.fetchUserInfo();
  },
  components: {
    UserPageInfo,
    UserPageTravel,
    UserPageWish
  },
}
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