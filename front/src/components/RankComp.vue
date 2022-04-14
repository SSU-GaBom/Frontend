<template>
    <v-dialog
      v-model="dialog"
      width="1000"
      persistent
    >
      <template v-slot:activator="{ on:rank , attrs}">
        <v-btn
          v-bind="attrs"
          v-on="rank"
          icon
          darks
        >
          <v-icon>mdi-podium-silver</v-icon>
          
        </v-btn>
      </template>

      <v-card height="70vh">
        <v-tabs
          v-model="tab"
          fixed-tabs
          background-color="indigo"
          dark
        >
          <v-tab
            v-for="item in items"
            :key="item.tab"
          >
            {{ item.tab }}
          </v-tab>
          <v-tab @click="dialog=false">
            Close
          </v-tab>
        </v-tabs>
      
        <v-tabs-items v-model="tab">
          <v-tab-item
            v-for="item in items"
            :key="item.tab"
          >
            <v-card flat>
              <v-card-text>{{ item.content }}</v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs-items>

      </v-card>
    </v-dialog>
  
</template>

<script>


export default {
    name : 'RankComp',
    data () {
        return {
          tab: null,
           dialog: false,
           TravelZZimRank : null,
           FollwerRank : null,
           RecommendRank : null,
           items : [
            { tab : "TravelZZimRank" , content : 'TravelZZimRank' },
            { tab : "FollwerRank" , content : 'FollwerRank' },
            { tab : "RecommendRank" , content : 'RecommendRank' }

           ]
        }
    }
}
</script>


<style scoped>

</style>