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

export default {
  name: 'UserView',
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