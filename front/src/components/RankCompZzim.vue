<template>
  <div>
    <v-simple-table>
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left">여행 타이틀</th>
            <th class="text-left">찜 수</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in rankInfoZzim" :key="item.id">
            <td class="text-left">
              <v-btn text tile @click="toTravelPage(item.id)">
                {{ item.title }}
              </v-btn>
            </td>
            <td class="text-left">{{ item.nums }}</td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
export default {
  name: "RankCompZzim",
  data() {
    return {};
  },
  methods: {
    async toTravelPage(travelId) {
      this.$router.push({
        name: "travel-view",
        params: { travelContentId: travelId },
      });
    },
  },
  computed: {
    ...mapGetters(["rankInfoZzim"]),
  },
};
</script>
<style scoped></style>
