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
            <router-link to="/">home&nbsp;&nbsp;</router-link>
            <router-link to="/about">about&nbsp;&nbsp;</router-link>
            <router-link to="/test">test&nbsp;&nbsp;</router-link>
            <router-link to="/user">user&nbsp;&nbsp;</router-link>
            <router-link to="/travel">travel</router-link>
            <rank-comp></rank-comp>
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
import MenuList from './MenuList.vue';

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