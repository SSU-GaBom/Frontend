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
            <v-row 
                justify="center">
              <v-avatar
                class="profile" 
                color="grey"
                size="128px"
              >
                <img :src="profileImage"/>
                <upload-image class="update-image"></upload-image>
              </v-avatar>
            </v-row>
          </v-col>
          <v-col class="py-0" align-self="center">
            <v-bind style="font-size: 24px"><b>{{ nickname }}</b></v-bind><br>
            <!-- <follower-comp v-bind:follower="this.followerCount"></follower-comp>
            <following-comp v-bind:following="this.followingCount"></following-comp> -->
            <v-btn text @click="followerDialog=true"><v-text>팔로워 <b>{{followerCount}}</b></v-text></v-btn>
            <v-btn text @click="followingDialog=true"><v-text>팔로잉 <b>{{followingCount}}</b></v-text></v-btn>
            <!--<v-text>{{ introduction }}</v-text>-->
            <v-dialog
              v-model="followerDialog"
              width="500"
            >
              <v-simple-table>
                <template v-slot:default>
                <thead>
                    <tr>
                    <th class="text-left">
                        NickName
                    </th>
                    <th class="text-left">
                        FollowerNum
                    </th>
                    </tr>
                </thead>
                <tbody>
                    <tr
                        v-for="item in desserts"
                        :key="item.name"
                    >
                    <td>{{ item.name }}</td>
                    <td>{{ item.calories }}</td>
                    </tr>
                </tbody>
                </template>
            </v-simple-table>
           </v-dialog>

           <v-dialog
              v-model="followingDialog"
              width="500"
            >
              <v-simple-table>
                <template v-slot:default>
                <thead>
                    <tr>
                    <th class="text-left">
                        NickName
                    </th>
                    <th class="text-left">
                        FollowerNum
                    </th>
                    </tr>
                </thead>
                <tbody>
                    <tr
                        v-for="item in desserts"
                        :key="item.name"
                    >
                    <td>{{ item.name }}</td>
                    <td>{{ item.calories }}</td>
                    </tr>
                </tbody>
                </template>
            </v-simple-table>
           </v-dialog>


          </v-col>
        </v-row>
      </v-img>
    </v-sheet>
  </v-card>
</template>

<script>
import FollowerComp from './FollowerComp.vue'
import UploadImage from './UploadImage.vue'
import FollowingComp from './FollowingComp.vue'
import {mapGetters} from 'vuex'
import store from '../store/index'

export default {
    data() {
        return {
            followerDialog : false,
            followingDialog : false,
            profileImage: require('../assets/images/profile-example.jpg'),
            nickname: store.state.viewUser.nickName,
            followerCount: store.state.viewUser.followerCount,
            followingCount: store.state.viewUser.followingCount,
        }
    },   
    methods: {
      updateImage() {
        if (!this.profileImage) {this.url="No File"}
        let reader = new FileReader();
        reader.readAsDataURL(this.profileImage);
        reader.onload = () => {
          this.url = reader.result;
        }
      },
    },
    // computed : {
    //   ...mapGetters({
    //     nickname : 'nickName',
    //     followerCount : 'followCount',
    //     followingCount : 'followingCount'
    //   })
    // },
    components:{
        UploadImage,
        // FollowerComp,
        // FollowingComp
    },
    
    
}
</script>

<style>
.profile {
  position: relative;
}
.update-image {
  position: absolute;
}
</style>