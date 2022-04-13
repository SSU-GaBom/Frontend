import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from "@/views/HomeView.vue";
import About from "@/views/AboutView.vue";
import User from "@/views/UserView.vue";
import Test from "@/views/TestView.vue"

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component:Home
  },
  {
    path: '/user',
    name: 'user',
    component:User
  },
  
  {
    path: '/about',
    name: 'about',
    component:About
  },
  {
    path: '/test',
    name: 'test',
    component:Test
  },
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
