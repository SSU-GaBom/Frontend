<template >
    <div>
        <v-btn icon darks @click="logout">
            <v-icon>mdi-logout</v-icon>
        </v-btn>
    </div>
</template>
<script>
export default {
    name: 'LogoutComp',
    methods: {        
        logout() {
			// bus.$emit('show:toast', 'User logged out');
			this.$store.commit('LOGOUT');
			this.$router.push('/');
		},
    },
}
</script>
<style >
    
</style>