<template>
   <v-menu offset-y :close-on-content-click="false">
      <template v-slot:activator="{ on: menu, attrs }">
        <v-btn
          v-bind="attrs"
          v-on="menu"
        >
          <v-icon>mdi-menu</v-icon>
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
            items: [
                { title: 'Travel작성' },
                { title: '랭킹' },
                { title: 'MyPage' },
                
            ]
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