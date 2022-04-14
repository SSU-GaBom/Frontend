<template>
    <v-div>    
    <v-sheet
      class="mx-auto"
      elevation="8"
    >
        <v-app-bar dense color="light-green">
            <menu-list/>
            <v-toolbar-title>&nbsp;&nbsp;
                <router-link id="link" to="/">GaBom</router-link>
            </v-toolbar-title>
            <v-spacer/>
<<<<<<< HEAD
            <router-link to="/">home&nbsp;&nbsp;</router-link>
            <router-link to="/about">about&nbsp;&nbsp;</router-link>
            <router-link to="/user">user&nbsp;&nbsp;</router-link>
            <router-link to="/travel">travel</router-link>
            <!-- <v-btn @click="test">   
                Test
            </v-btn> -->
            
=======
            <rank-comp></rank-comp>
>>>>>>> 63e758d30616f4df62ca980deddc646d7fb66f42
            <template v-if="!isLoggedIn">
				<join-comp/>
                <login-comp/>
			</template>
			<template v-else>
                <v-btn @click="logout">
                    LogOut
                </v-btn>

			</template>   
        </v-app-bar>
    </v-sheet>
    </v-div>
</template>

<script>
import { mapGetters } from 'vuex';
import LoginComp from './LoginComp.vue';
import JoinComp from './JoinComp.vue';
import RankComp from './RankComp.vue';
<<<<<<< HEAD
import WriteTravel from './WriteTravel.vue';
import {testUserApi} from '../api/index'

=======
import MenuList from './MenuList.vue';
>>>>>>> 63e758d30616f4df62ca980deddc646d7fb66f42

export default {
    name: 'HeaderBar',
    computed: {
		...mapGetters(['isLoggedIn']),
	},
    methods: {
        logout() {
			// bus.$emit('show:toast', 'User logged out');
			this.$store.commit('LOGOUT');
			this.$router.push('/');
		},
        async test(){
            
            console.log(this.$store.state.user)
            const response = await testUserApi(this.$store.state.user)
            console.log(response.data)
        }
    },
    data() {
        return {
            items: [
                { title: '메뉴1' },
                { title: '메뉴2' },
                { title: '메뉴3' },
                { title: '메뉴4' },
            ],
        }
    },
    components:{
        LoginComp,
        JoinComp,
        RankComp,
        MenuList
    }
}
</script>

<style scoped>
#link {
    color: black;
    text-decoration: none;
}
</style>