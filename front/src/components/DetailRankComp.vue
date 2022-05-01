<template>
    <div>
        <v-simple-table>
        <template v-slot:default>
            <thead>
                <tr>
                <th class="text-left">
                    Name
                </th>
                <th class="text-left">
                    ZZim
                </th>
                </tr>
            </thead>
            <tbody>
                <tr
                v-for="item in person"
                :key="item.name"
                >
                <td class="text-left" >{{ item.name }}</td>
                <td class="text-left">{{ item.zzim }}</td>
                </tr>
            </tbody>
        </template>
        </v-simple-table>
    </div>
</template>
<script>
export default {
    name : 'DetailRankComp',
    data () {
      return {
        person: [
          {
            name: 'BusanTravel',
            zzim: 613,
          },
          {
            name: 'SeoulTravel',
            zzim: 597,
          },
          {
            name: 'AndongTravel',
            zzim: 574,
          },
          {
            name: 'DaeguTravel',
            zzim: 521,
          },
          {
            name: 'JejuTravel',
            zzim: 487,
          },
          {
            name: 'Travel',
            zzim: 465,
          },
          {
            name: 'GangneungTravel',
            zzim: 392,
          },
          {
            name: 'SokchoTravel',
            zzim: 386,
          },
          {
            name: 'GyeongjuTravel',
            zzim: 350,
          },
          {
            name: 'GapyeongTravel',
            zzim: 312,
          },
        ],
      }
    },
}
</script>
<style scoped>
    
</style>