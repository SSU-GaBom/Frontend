<template>
  <div>
    <v-dialog 
    width="700px"
    v-model="dialog"
    >
      <template v-slot:activator="{ on , attrs}">
        <v-btn
          v-bind="attrs"
          v-on="on"
          icon
          darks
        >
          <v-icon>mdi-image-plus</v-icon>
        </v-btn>
      </template>

      <v-sheet
        color="white"
        elevation="1"
        >

        <v-file-input  prepend-icon="mdi-camera" multiple v-model="images" show-size label="File input"></v-file-input>

        <v-textarea
            v-model="card.text"
            label="소개글"
            outlined
            rows="10"
        ></v-textarea>
        <v-btn @click="addCard()">추가</v-btn>
        <v-btn @click="dialog=false">취소</v-btn>           
        </v-sheet>

    </v-dialog>
  </div>
</template>

<script>
import store from '../store/index'

export default {
    name : 'CardComp',
    data() {
        return {
            dialog : false,
            images : [],
            card : {
                images : [],
                text : null
            }
        }
    },
    methods: {
        addCard(){

          for (let index = 0; index < this.images.length; index++) {
            let image = {
              fileName : this.images[index].name,
              base64Image : null,
            }
            this.getBase64(this.images[index]).then(
              // data => this.card.images.push(data.substr(23))
              data => (image.base64Image = data.substr(23))
            )
            console.log(image)
            this.card.images.push(image)
          }
          store.commit('SET_CARD',this.card)
          this.dialog = false;
        },
        getBase64(image){
          return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.readAsDataURL(image);
            reader.onload = () => resolve(reader.result);
            reader.onerror = error => reject(error);
         });

        }
    }
}
</script>

<style lang="">
    
</style>