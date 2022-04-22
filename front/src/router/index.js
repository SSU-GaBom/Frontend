import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from "@/views/HomeView.vue";
import About from "@/views/AboutView.vue";
import User from "@/views/UserView.vue";
<<<<<<< HEAD
import Travel from "@/views/TravelView.vue";
import Write from "@/views/WriteTravel.vue";
import Test from "@/views/TestView.vue";
=======
>>>>>>> 7d2c3670423e60d9722743e8c084e77b013ac13b

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
<<<<<<< HEAD
    path: '/travel',
    name: 'travel',
    component:Travel
  },
  {
    path: '/write',
    name: 'write',
    component:Write
  },
  {
=======
>>>>>>> 7d2c3670423e60d9722743e8c084e77b013ac13b
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
