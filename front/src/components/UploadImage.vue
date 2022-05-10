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
import {uploadImage} from '../api/profile'

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
        formData.append('profile_image',this.image);
        const response = await uploadImage(formData);
        console.log("success!!")
        console.log(response)

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