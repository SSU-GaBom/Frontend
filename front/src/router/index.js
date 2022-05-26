import Vue from "vue";
import VueRouter from "vue-router";

import Home from "@/views/HomeView.vue";
import About from "@/views/AboutView.vue";
import User from "@/views/UserView.vue";
import TravelList from "@/views/TravelList.vue";
import TravelLikeList from "@/views/TravelLikeList.vue";
import TravelZzimList from "@/views/TravelZzimList.vue";
import TravelView from "@/views/TravelView.vue";
import TravelWriter from "@/views/TravelWriter.vue";
import store from "../store/index";
import { getUserFromCookie } from "@/utils/cookies.js";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: Home,
  },
  // 마이페이지
  {
    path: "/user",
    name: "myPage",
    component: User,
    // beforeEnter
  },
  // 유저페이지
  {
    path: "/user/:userNickName",
    name: "userPage",
    component: User,
    // beforeEnter
  },
  {
    path: "/travel/list",
    name: "travel-list",
    component: TravelList,
  },
  {
    path: "/travel/list/like",
    name: "like-list",
    component: TravelLikeList,
  },
  {
    path: "/travel/list/zzim",
    name: "zzim-list",
    component: TravelZzimList,
  },
  {
    path: "/travel/content/:travelContentId",
    name: "travel-view",
    component: TravelView,
  },
  {
    path: "/travel/write",
    name: "travel-writer",
    component: TravelWriter,
  },
  {
    path: "/about",
    name: "about",
    component: About,
  },
];

// function beforeEnter(to, from, next) {
// 	if (store.getters['isLoggedIn'] || getUserFromCookie()) {
// 		next();
// 	} else {
// 		alert('sign in please');
//     next('/home');
// 	}
// }

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
