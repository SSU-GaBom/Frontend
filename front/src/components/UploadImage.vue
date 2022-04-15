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
      <v-file-input dark v-model="image" show-size label="File input"></v-file-input>
      <p>File Name : {{ image.name }}</p>
    </v-dialog>
    <img :src="image" alt="">
  </div>
</template>

<script>
export default {
  data() {
    return {
      image : ''
    }
  },
  methods: {
    uploadImg() {
      console.log('들어왔다')
      var image = this.$refs['image'].files[0]

      const url = URL.createObjectURL(image)
      this.image = url
      console.log(url)
      console.log(this.image)
    }
  }
}
</script>

<style lang="">
    
</style>