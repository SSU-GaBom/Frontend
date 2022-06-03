<template>
  <div>
    <v-simple-table>
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left">닉네임</th>
            <th class="text-left">팔로워 수</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in rankInfoFollower" :key="item.nickname">
            <td class="text-left">
              <v-btn text tile @click="toProfilePage(item.nickname)">
                {{ item.nickname }}
              </v-btn>
            </td>
            <td class="text-left">{{ item.followerNums }}</td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
export default {
  name: "RankCompFollower",
  data() {
    return {};
  },
  methods: {
    toProfilePage(nickname) {
      this.$router.push({
        name: "userPage",
        params: { userNickName: nickname },
      });
    },
  },
  computed: {
    ...mapGetters(["rankInfoFollower"]),
  },
};
</script>
<style scoped></style>
