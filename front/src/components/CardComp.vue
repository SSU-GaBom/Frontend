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
        <v-file-input  prepend-icon="mdi-camera" multiple v-model="card.images" show-size label="File input"></v-file-input>
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
            card : {
                images : [],
                text : null
            }
        }
    },
    methods: {
        addCard(){
          
          store.commit('SET_CARD',this.card)
          this.dialog = false;
        }
    }
}
</script>

<style lang="">
    
</style>