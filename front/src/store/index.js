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
    refreshToken : "",
    viewUser: {
      nickName: null,
      profileImage: "",
      followerCount: null,
      followingCount: null,
      travelList: [],
      travelImages : [],
      followerList :[],
      followingList : []
    },
    rankInfo: {
      liked: [],
      zzim: [],
      follower: [],
    },
    pinList: [],
    writeTravelList: [],
    commentList: [],
    travelInfo: [],
    travelList: [],
    myLikeList: [],
    myZzimList: [],
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
    userRefreshToken(state){
      return state.refreshToken;
    },
    myNickName(state) {
      return state.user.nickName;
    },
    myUserId(state) {
      return state.user.id;
    },
    myTravelList(state) {
      return state.travelList;
    },
    myLikeList(state) {
      return state.myLikeList;
    },
    myZzimList(state) {
      return state.myZzimList;
    },
    writeTravelList(state) {
      return state.writeTravelList;
    },
    writeCardList(state) {
      return state.cardList;
    },
    viewUserTravelImages(state){
      return state.viewUser.travelImageList;
    },
    viewUserNickName(state) {
      return state.viewUser.nickName;
    },
    viewUserFollowerCount(state) {
      return state.viewUser.followerCount;
    },
    viewUserFollowingCount(state) {
      return state.viewUser.followingCount;
    },
    viewUserProfileImage(state) {
      return state.viewUser.profileImage;
    },
    viewUserTravelList(state) {
      return state.viewUser.travelList;
    },
    viewUserFollowingList(state){
      return state.viewUser.followingList;
    },
    viewUserFollowerList(state){
      return state.viewUser.followerList;
    },
    rankInfoLiked(state) {
      return state.rankInfo.liked;
    },
    rankInfoZzim(state) {
      return state.rankInfo.zzim;
    },
    rankInfoFollower(state) {
      return state.rankInfo.follower;
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
    SET_REFRESHTOKEN(state,token){
      state.refreshToken = token;
    },
    LOGOUT(state) {
      state.user.id = null
      state.user.nickName = null
      state.token = null
      state.refreshToken = null
      state.travelList = []
      state.viewUser.nickName = null
      state.viewUser.profileImage = ""
      state.viewUser.followerCount = null
      state.viewUser.followingCount = null
      state.viewUser.travelList = []
      state.viewUser.travelImages = []
      state.viewUser.followerList = []
      state.viewUser.followingList = []
      state.pinList = []
      state.travelInfo = []
      state.writeTravelList = []
      state.travelList = []
      state.ttravelList = []
      state.cardList = []
      deleteCookie("til_auth")
      deleteCookie("til_user")
    },
    SET_VIEWUSER(state, data) {
      console.log("Mutation.set_viewuser");
      state.viewUser.nickName = data.nickName;
      state.viewUser.followerCount = data.userFollowerCount;
      state.viewUser.followingCount = data.userFollowingCount;
      state.viewUser.profileImage = data.profileImage;
      state.viewUser.travelList = data.myTravelList;
      state.viewUser.travelImages = data.travelImageList;
      state.viewUser.followerList = data.followerList;
      state.viewUser.followingList = data.followingList;
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
    SET_LIKE_LIST(state, data) {
      state.myLikeList = data;
    },
    SET_ZZIM_LIST(state, data) {
      state.myZzimList = data;
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
    SET_RANK(state, data) {
      state.rankInfo.liked = data.liked;
      state.rankInfo.zzim = data.zzim;
      state.rankInfo.follower = data.follower;
    },
    SET_PLUS(state){
      // console.log("SET_PLUS")
      state.viewUser.followerCount = state.viewUser.followerCount +1;
    },
    SET_MINUS(state){
      // console.log("SET_MINUS")
      state.viewUser.followerCount = state.viewUser.followerCount -1;
    }
  },
  actions: {
    async LOGIN({ commit }, data) {
      const response = await loginUser(data);
      console.log("Actions.LOGIN");
      console.log(response.headers)

      // 로그인 성공
      if (response.data.code == 0) {
        
        const data = {
          userId: response.data.data.userId,
          nickName: response.data.data.nickName,
        };
        commit("SET_USER", data);
        commit("SET_TOKEN", response.headers['x-auth-token']);
        commit("SET_REFRESHTOKEN",response.headers['x-auth-refresh-token'])
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
