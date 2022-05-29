<template>
  <v-card class="mx-auto" tile outlined>
    <v-sheet class="mx-auto" elevation="8" max-width="800">
      <v-img height="300px" src="../assets/images/test5.png">
        <v-row align="end" class="fill-height">
          <v-col align-self="end" class="pa-0" cols="12">
            <v-row justify="center">
              <v-avatar class="profile" color="grey" size="128px">
                <!-- <img :src="profileImage"/> -->
                <img
                  v-bind:src="'data:image/jpeg;base64,' + viewUserProfileImage"
                />
                <upload-image
                  v-if="myNickName === viewUserNickName"
                  class="update-image"
                ></upload-image>
              </v-avatar>
            </v-row>
          </v-col>

          <v-col class="py-0" align-self="center">
            <v-bind style="font-size: 24px"
              ><b>{{ viewUserNickName }}</b></v-bind
            ><br />
            <!-- <follower-comp v-bind:follower="this.followerCount"></follower-comp>
            <following-comp v-bind:following="this.followingCount"></following-comp> -->
            <br />
            <v-row justify="center">
              <v-btn text @click="followerDialog = true"
                ><v-text
                  >Follower <b>{{ viewUserFollowerCount }}</b></v-text
                ></v-btn
              >
              <v-btn text @click="followingDialog = true"
                ><v-text
                  >Following <b>{{ viewUserFollowingCount }}</b></v-text
                ></v-btn
              >
            </v-row>
            <v-row justify="center">
              <v-btn text @click="follow">
                <v-icon>mdi-account-plus</v-icon>
              </v-btn>
              <v-btn text @click="unFollow">
                <v-icon>mdi-account-remove</v-icon>
              </v-btn>
            </v-row>

            <v-dialog v-model="followerDialog" width="500">
              <v-simple-table>
                <template v-slot:default>
                  <thead>
                    <tr>
                      <th class="text-center">NickName</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr
                      v-for="userNickName in viewUserFollowerList"
                      :key="userNickName"
                    >
                      <td>
                        <v-btn text @click="toProfilePage(userNickName)">
                          {{ userNickName }}
                        </v-btn>
                      </td>
                    </tr>
                  </tbody>
                </template>
              </v-simple-table>
            </v-dialog>

            <v-dialog v-model="followingDialog" width="500">
              <v-simple-table>
                <template v-slot:default>
                  <thead>
                    <tr>
                      <th class="text-center">NickName</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr
                      v-for="userNickName in viewUserFollowingList"
                      :key="userNickName"
                    >
                      <td>
                        <v-btn text @click="toProfilePage(userNickName)">
                          {{ userNickName }}
                        </v-btn>
                      </td>
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
import UploadImage from "./UploadImage.vue";
import { mapGetters } from "vuex";
import { doFollow, doUnFollow } from "../api/profile";
import store from "../store/index";

export default {
  data() {
    return {
      followerDialog: false,
      followingDialog: false,
    };
  },
  methods: {
    updateImage() {
      if (!this.profileImage) {
        this.url = "No File";
      }
      let reader = new FileReader();
      reader.readAsDataURL(this.profileImage);
      reader.onload = () => {
        this.url = reader.result;
      };
    },
    toProfilePage(nickname) {
      console.log("UserPageInfo.toProfilePage");
      this.$router.push({
        name: "userPage",
        params: { userNickName: nickname },
      });
    },
    async follow() {
      try {
        console.log("UserPageInfo.follow()");
        const response = await doFollow(this.viewUserNickName);
        console.log(response.data);
        if (response.data.code === 0) {
          alert("Follow Success!");
          store.commit("SET_PLUS");
        } else {
          alert(response.data.msg);
        }
      } catch (error) {
        console.log(error);
        return;
      }
    },
    async unFollow() {
      try {
        console.log("UserPageInfo.unFollow()");
        const response = await doUnFollow(this.viewUserNickName);
        console.log(response.data);
        if (response.data.code === 0) {
          store.commit("SET_MINUS");
          alert("UnFollow Success!");
        } else {
          alert(response.data.msg);
        }
      } catch (error) {
        console.log(error);
        return;
      }
    },
    refresh() {
      // 새로고침
      this.$router.go();
    },
  },
  computed: {
    ...mapGetters([
      "viewUserNickName",
      "viewUserFollowerCount",
      "viewUserFollowingCount",
      "viewUserProfileImage",
      "viewUserFollowerList",
      "viewUserFollowingList",
      "myNickName",
    ]),
  },
  components: {
    UploadImage,
    // FollowerComp,
    // FollowingComp
  },
};
</script>

<style>
.profile {
  position: relative;
}
.update-image {
  position: absolute;
}
</style>
