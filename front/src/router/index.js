import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from "@/views/HomeView.vue";
import About from "@/views/AboutView.vue";
import User from "@/views/UserView.vue";
import Travel from "@/views/TravelView.vue";
import Test from "@/views/TestView.vue"

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component:Home
  },
  // 마이페이지
  {
    path: '/user',
    name: 'myPage',
    component:User
  },
  // 유저페이지
  {
    path: '/user/:userNickName',
    name: 'userPage',
    component:User
  },
  {
    path: '/travel',
    name: 'travel',
    component:Travel
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
