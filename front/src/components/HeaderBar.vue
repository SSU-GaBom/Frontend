<template>
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
    data() {
        return {
            
        }
    },
    components:{
        LoginComp,JoinComp
    }
}
</script>
