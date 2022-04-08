<template >
     <v-btn-toggle
        v-model="text"
        tile
        color="deep-purple accent-3"
        group
        >
          <rank-comp></rank-comp>

          <write-travel></write-travel>

          <v-btn value="right">
            Right
          </v-btn>

          <v-btn value="justify">
            Justify
          </v-btn>
      </v-btn-toggle>
</template>
<script>
import RankComp from './RankComp.vue'
import WriteTravel from './WriteTravel.vue'

export default {
    name : 'MenuList',
    components :{
        RankComp,WriteTravel
    },
    data() {
        return {
            text: 'center',
        }
    },
}
</script>
<style scoped>
    
</style>