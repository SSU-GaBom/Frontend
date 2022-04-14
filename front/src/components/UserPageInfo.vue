<template>
  <v-card
    class="mx-auto"
    tile
  >
    <v-sheet
      class="mx-auto"
      elevation="8"
      max-width="800"
    >
      <v-img
        height="300px"
        src="../assets/images/test5.png"
      >
        <v-row
          align="end"
          class="fill-height"
        >
          <v-col
            align-self="end"
            class="pa-0"
            cols="12"
          >
            <v-avatar
              class="profile"
              color="grey"
              size="128px"
            >
              <v-img src="../assets/images/profile-example.jpg"></v-img>
            </v-avatar>
          </v-col>
          <v-col class="py-0" align-self="center">
            <v-bind>{{ nickname }}</v-bind><br>
            <v-text>팔로워 <b>{{follower}}</b> &nbsp;&nbsp;&nbsp;&nbsp;  팔로잉 <b>{{following}}</b></v-text><br>
            <v-text>{{ introduction }}</v-text>
          </v-col>
        </v-row>
      </v-img>
    </v-sheet>
  </v-card>
</template>

<script>
export default {
    data() {
        return {
            nickname: '존잘윤세연',
            introduction: '안녕하세요. 여행이 좋아!',
            follower: 179,
            following: 279
        }
    },        
}
</script>

<style>
</style>