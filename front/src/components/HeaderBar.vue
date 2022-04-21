<template>
<<<<<<< HEAD
    <v-div>
    <v-app-bar dense color="light-green">
        <v-toolbar-title>GaBom</v-toolbar-title>
        <v-spacer/>
        <router-link to="/">home&nbsp;&nbsp;</router-link><br>
        <router-link to="/about">about&nbsp;&nbsp;</router-link>
        <router-link to="/user">user</router-link>
        
        <join-comp/>
        <login-comp/>
    </v-app-bar>
</v-div>
</template>

<script>
import LoginComp from './LoginComp.vue'
import JoinComp from './JoinComp.vue'


export default {
    name: 'HeaderBar',
=======
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
>>>>>>> c39cc1ed80ad0933348399aba536b4b6e8571c78
    data() {
        return {
            loginId : this.$store.user.loginId
        }
    },
    components:{
        LoginComp,JoinComp
    }
}
</script>
