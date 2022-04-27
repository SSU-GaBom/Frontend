<template>
    
         <v-dialog
            v-model="dialog"
            width="500"
         >
            <template v-slot:activator="{ on, attrs }">
                <v-btn
                text
                v-bind="attrs"
                v-on="on"
                >
                <v-text>
                    팔로워 {{follower}}
                </v-text>
                
                </v-btn>
            </template>

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
    
</template>

<script>
import {getUserFollower} from '../api/profile'

export default {
    name : 'FollowerComp',
    data () {
      return {
        nickName : null,
        dialog : 'false',
        desserts: [
          {
            name: 'Frozen Yogurt',
            calories: 159,
          },
          {
            name: 'Ice cream sandwich',
            calories: 237,
          },
          {
            name: 'Eclair',
            calories: 262,
          },
          {
            name: 'Cupcake',
            calories: 305,
          },
          {
            name: 'Gingerbread',
            calories: 356,
          },
          {
            name: 'Jelly bean',
            calories: 375,
          },
          {
            name: 'Lollipop',
            calories: 392,
          },
          {
            name: 'Honeycomb',
            calories: 408,
          },
          {
            name: 'Donut',
            calories: 452,
          },
          {
            name: 'KitKat',
            calories: 518,
          },
        ],
      }
    },
    props : {
      follower : Number
    },
    methods: {
        async fetchUserFollower(){
            try {
                const response = await getUserFollower()

            } catch (error) {
                console.log(error)
            }
        }
    },
}
</script>

<style scoped>
    
</style>