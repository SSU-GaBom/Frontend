<template>
  <div id="main">
    <user-page-info></user-page-info>
    <user-page-travel-list id="content"></user-page-travel-list>
    <user-page-wish-list id="content"></user-page-wish-list>
  </div>

</template>

<script>
import UserPageInfo from '../components/UserPageInfo.vue'
import UserPageTravelList from '../components/UserPageTravelList.vue'
import UserPageWishList from '../components/UserPageWishList.vue'

export default {
  name: 'UserView',
  components: {
    UserPageInfo,
    UserPageWishList,
    UserPageTravelList
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