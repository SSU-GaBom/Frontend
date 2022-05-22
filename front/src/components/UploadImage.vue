<template>
  <div>
    <v-dialog width="500px">
      <template v-slot:activator="{ on:rank , attrs}">
        <v-btn
          v-bind="attrs"
          v-on="rank"
          icon
          darks
        >
          <v-icon>mdi-camera-flip-outline</v-icon>
        </v-btn>
      </template>
      <v-sheet
        color="white"
        elevation="1"
        >
        <v-file-input prepend-icon="mdi-camera" v-model="image" show-size label="File input"></v-file-input>
        
        <v-btn dark @click="uploadImg()">수정</v-btn>
        </v-sheet>
      
    </v-dialog>
    <!-- <img :src="image" alt=""> -->
  </div>
</template>

<script>
import {uploadImage,getUserInfo} from '../api/profile'
import store from '../store/index'

export default {
  data() {
    return {
      image : ''
    }
  },
  methods: {
    async uploadImg() {
      try {

        console.log("uploadImg")
        console.log(this.image)
        const formData = new FormData();
        formData.append('profile-image',this.image);
        const response = await uploadImage(formData);
        console.log(response)
        
        if(response.status == 200){
          // store.commit('SET_PROFILEIMAGE',this.image);

          const response = await getUserInfo(store.state.user.nickName)
          
          
          const data = {
            nickName : response.data.data.nickName,
            userFollowerCount : response.data.data.userFollowerCount,
            userFollowingCount : response.data.data.userFollowingCount,
            profileImage : response.data.data.profileImage,
            // myTravelList : response.data.data.myTravelList,
            // likedTravelList : response.data.data.likedTravelList;
          }
          
          store.commit('SET_VIEWUSER',data)
        }

      } catch (error) {

        console.log(error)

      }
      
      // console.log('들어왔다')
      // var image = this.$refs['image'].files[0]

      // const url = URL.createObjectURL(image)
      // this.image = url
      // console.log(url)
      // console.log(this.image)
    }
  }
}
</script>

<style lang="">
    
</style>