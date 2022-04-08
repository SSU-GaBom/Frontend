<template>
    <v-div>
    <v-app-bar dense color="light-green">
        <v-toolbar-title>GaBom</v-toolbar-title>
        <v-spacer></v-spacer>
        <join-comp></join-comp>
        <login-comp/>
    </v-app-bar>
</v-div>
</template>

<script>
import LoginComp from './LoginComp.vue'
import JoinComp from './JoinComp.vue'


export default {
    name: 'HeaderBar',
    components:{
        LoginComp,JoinComp
    }
}
</script>
