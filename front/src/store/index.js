import Vue from "vue";
import Vuex from "vuex";
import { loginUser } from "../api/auth";
import createPersistedState from "vuex-persistedstate";
import {
  saveAuthToCookie,
  saveUserToCookie,
  getUserFromCookie,
  deleteCookie,
} from "../utils/cookies";

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [
    createPersistedState({
      storage: window.localStorage,
      reducer: (state) => ({
        user: {
          id: state.user.id,
          nickName: state.user.nickName,
        },
        token: state.token,
      }),
    }),
  ],
  state: {
    user: {
      id: null,
      nickName: null,
    },
    token: "",
    viewUser: {
      nickName: null,
      profileImage: "",
      followerCount: null,
      followingCount: null,
      travelList: [],
      travelImages : [],
      wishList: [],
    },
    pinList: [],
    writeTravelList: [],
    commentList: [],
    travelInfo: [],
    travelList: [],
    ttravelList: [],
    cardList: [],
    selectedMarker: null,
  },
  getters: {
    isLoggedIn(state) {
      return !!state.token || getUserFromCookie();
    },
    userToken(state) {
      return state.token;
    },
    myNickName(state) {
      return state.user.nickName;
    },
	myUserId(state){
		return state.user.id;
	},
	myTravelList(state){
		console.log("getters.myTravelList")
		return state.travelList;
	},
    writeTravelList(state) {
      return state.writeTravelList;
    },
    writeCardList(state) {
      return state.cardList;
    },
    viewUserNickName(state){
		return state.viewUser.nickName;
	},
	
	viewUserFollowerCount(state){
		return state.viewUser.followerCount;
	},
	viewUserFollowingCount(state){
		return state.viewUser.followingCount;
	},
	viewUserProfileImage(state){
		return state.viewUser.profileImage;
	},
	viewUserTravelList(state){
		return state.viewUser.travelList;
	},
  viewUserTravelImages(state){
    return state.viewUser.travelImageList;
  },
    commentList(state) {
      return state.commentList;
    },
    ttravelList(state) {
      return state.ttravelList;
    },
    
    travelInfo(state) {
      return state.travelInfo;
    },

  },
  mutations: {
    SET_USER(state, data) {
      state.user.id = data.userId;
      state.user.nickName = data.nickName;
    },
    SET_TOKEN(state, token) {
      state.token = token;
    },
    LOGOUT(state) {
		state.user.id = null;
		state.user.nickName = null;
		state.token = null;
		state.travelList = [];
		state.viewUser.nickName = null;
		state.viewUser.profileImage = '',
		state.viewUser.followerCount = null,
		state.viewUser.followingCount = null,
		state.viewUser.travelList = [];
		state.viewUser.wishList =[];
		state.pinList = [];
		state.travelInfo = [],
		state.writeTravelList = [],
		state.travelList = [],
		state.ttravelList = [],
		state.cardList = []
		deleteCookie("til_auth");
		deleteCookie("til_user");
    },
    SET_VIEWUSER(state, data) {
      console.log("Mutation.set_viewuser");
      console.log(data);
      state.viewUser.nickName = data.nickName;
      state.viewUser.followerCount = data.userFollowerCount;
      state.viewUser.followingCount = data.userFollowingCount;
      state.viewUser.profileImage = data.profileImage;
      state.viewUser.travelList = data.myTravelList;
      state.viewUser.travelImages = data.travelImageList;
      // state.viewUser.wishList=data.like
    },
    SET_WRITETRAVELLIST(state, travel) {
      state.writeTravelList.push(travel);
    },
    SET_TRAVEL(state, travel) {
      state.travelList.push(travel);
    },
    SET_TRAVEL_LIST(state, data) {
      state.ttravelList = data;
    },
    SET_TRAVEL_DETAIL(state, data) {
      state.travelInfo = data;
    },
    SET_COMMENT_LIST(state, data) {
      state.commentList = data;
    },
    SET_CARD(state, card) {
    //   console.log(card);
      state.cardList.push(card);
    },
    SET_PROFILEIMAGE(state, image) {
      state.viewUser.profileImage = image;
    },
  },
  actions: {
    async LOGIN({ commit }, data) {
      const response = await loginUser(data);
      console.log("Actions.LOGIN");

      // 로그인 성공
      if (response.data.code == 0) {
        
        const data = {
          userId: response.data.data.userId,
          nickName: response.data.data.nickName,
        };
        commit("SET_USER", data);
        commit("SET_TOKEN", response.data.data.token);
        saveUserToCookie(response.data.data.userId);
        saveAuthToCookie(response.data.data.token);
      } else {
        // 로그인실패
        console.log(response.data.code);
        console.log(response.data.msg);
      }

      return response;
    },
  },
  modules: {},
});
