<template>
    <v-div>
        <v-app-bar dense color="light-green">
            <v-toolbar-title>GaBom</v-toolbar-title>
            <v-spacer/>
            <router-link to="/">home&nbsp;&nbsp;</router-link><br>
            <router-link to="/about">about&nbsp;&nbsp;</router-link>
            <router-link to="/user">user</router-link>
            
            <template v-if="!isLoggedIn">
				<join-comp/>
                <login-comp></login-comp>
			</template>
			<template v-else>
                <v-btn @click="logout">
                    LogOut
                </v-btn>
				
			</template>
            
        </v-app-bar>
    </v-div>
</template>

<script>
import { mapGetters } from 'vuex';
import LoginComp from './LoginComp.vue'
import JoinComp from './JoinComp.vue'


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
            
        }
    },
    components:{
        LoginComp,JoinComp
    }
}
</script>
