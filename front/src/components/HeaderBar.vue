<template>
    <v-div>
        <v-app-bar dense color="light-green">
            <v-menu offset-y>
                <template v-slot:activator="{ on, attrs }">
                    <v-btn
                        small
                        class="mx-2"
                        fab
                        dark
                        color="teal"
                        v-bind="attrs"
                        v-on="on"
                    >      
                        <v-icon dark>
                            mdi-format-list-bulleted-square
                        </v-icon>
                    </v-btn>
                </template>
                <v-list>
                    <v-list-item
                        v-for="(item, index) in items"
                        :key="index"
                    >
                    <v-list-item-title>{{ item.title }}</v-list-item-title>
                    </v-list-item>
                </v-list>
            </v-menu>
            <v-toolbar-title>&nbsp;&nbsp;GaBom</v-toolbar-title>
            <v-spacer/>
            <router-link to="/">home&nbsp;&nbsp;</router-link>
            <router-link to="/about">about&nbsp;&nbsp;</router-link>
            <router-link to="/user">user&nbsp;&nbsp;</router-link>
            <router-link to="/travel">travel</router-link>
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
            items: [
                { title: '메뉴1' },
                { title: '메뉴2' },
                { title: '메뉴3' },
                { title: '메뉴4' },
            ],
        }
    },
    components:{
        LoginComp, JoinComp
    }
}
</script>
