<template>
   <v-menu offset-y :close-on-content-click="false">
      <template v-slot:activator="{ on: menu, attrs }">
        <v-btn
          v-bind="attrs"
          v-on="menu"
          x-small
          class="mx-2"
          fab
          dark
          color="green"
        >
          <v-icon dark>
            mdi-format-list-bulleted-square
          </v-icon>
        </v-btn>
      </template>
      
        <v-list >
        
          <v-list-item-content>
              <v-list-item-title @click="screenChange()">Travel작성</v-list-item-title>
          </v-list-item-content>

          <rank-comp></rank-comp>
          
          <v-list-item-content>
              <v-list-item-title @click="screenChange()">MyPage</v-list-item-title>
          </v-list-item-content>
        
        </v-list>
    </v-menu>
</template>

<script>
import RankComp from './RankComp.vue'


export default {
  components: { RankComp },
    name : 'MenuBar',
    data () {
        return {
            
        }
    },
    component :{
      RankComp
    },
    methods: {
        screenChange(){
            console.log("click")
        }
    },
}
</script>


<style scoped>

</style>