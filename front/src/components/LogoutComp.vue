<template >
    <div>
        <v-btn icon darks @click="logout">
            <v-icon>mdi-logout</v-icon>
        </v-btn>
    </div>
</template>
<script>
import store from '../store/index'

export default {
    name: 'LogoutComp',
    methods: {        
        logout() {
			// bus.$emit('show:toast', 'User logged out');
            console.log("LogoutComp.logout()")
			store.commit('LOGOUT');
            if(this.$route.path !== '/'){
                this.$router.push('/');
            }
			
		},
    },
}
</script>
<style >
    
</style>