import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from "@/views/HomeView.vue";
import About from "@/views/AboutView.vue";
import User from "@/views/UserView.vue";
import TravelList from "@/views/TravelList.vue";
import TravelView from "@/views/TravelView.vue";
import TravelWriter from "@/views/TravelWriter.vue";

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
    path: '/travel/list',
    name: 'travel-list',
    component:TravelList
  },
  {
    path: '/travel/content/:travelContentId',
    name: 'travel-view',
    component:TravelView
  },
  {
    path: '/travel/write',
    name: 'travel-writer',
    component:TravelWriter
  },
  {
    path: '/about',
    name: 'about',
    component:About
  },

  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
