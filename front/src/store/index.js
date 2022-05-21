import Vue from 'vue'
import Vuex from 'vuex'
import {loginUser} from '../api/auth'
import createPersistedState from 'vuex-persistedstate';
import {
        saveAuthToCookie,
        saveUserToCookie,
        getUserFromCookie,
        deleteCookie} 
        from '../utils/cookies'

Vue.use(Vuex)

export default new Vuex.Store({
	plugins: [
		createPersistedState()
	],
  state: {
    user : {
		id : null,
		nickName : null
	},
    token : '',
	viewUser : {
		nickName : null,
		profileImage : '',
		followerCount : null,
		followingCount : null,
		travelList : [],
		wishList : [],
	},
	detailTravelInfo : null,
	pinList : [],
	writeTravelList : [],
	cardList : [],
	selectedMarker : null,
  },
  getters: {
		isLoggedIn(state) {
			return !!state.token || getUserFromCookie();
		},
		userToken(state) {
			return state.token;
		},
		myNickName(state){
			return state.user.nickName;
		},
		writeTravelList(state){
			return state.writeTravelList;
		},
		writeCardList(state){
			return state.cardList;
		},
		nickName(state){
			return state.viewUser.nickName;
		},
		followerCount(state){
			return state.viewUser.followerCount;
		},
		followingCount(state){
			return state.viewUser.followingCount;
		},
		profileImage(state){
			return state.viewUser.profileImage;
		},
		detailTravelInfo(state){
			return state.detailTravelInfo;
		},
		selectedMarker(state){
			return state.selectedMarker;
		}
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
			state.viewUser.nickName = null;
			state.viewUser.profileImage = null;
			state.viewUser.followerCount = null;
			state.viewUser.followingCount = null;
			deleteCookie('til_auth');
			deleteCookie('til_user');
		},
		SET_VIEWUSER(state,data){
			console.log("set_viewuser")
			console.log(data)
			state.viewUser.nickName = data.nickName;
			state.viewUser.followerCount = data.userFollowerCount;
			state.viewUser.followingCount = data.userFollowingCount;
			state.viewUser.profileImage = data.profileImage;
		},
		SET_TRAVEL(state,travel){
			state.writeTravelList.push(travel);
		},
		SET_CARD(state,card){
			console.log(card)
			state.cardList.push(card);
		},
		SET_PROFILEIMAGE(state,image){
			state.viewUser.profileImage = image;
		},
		SET_DETAILTRAVELINFO(state, travelInfo){
			
			state.detailTravelInfo = travelInfo
		},
		SET_MARKER(state,marker){
			state.selectedMarker = marker;
		},
		SET_MARKERNULL(state){
			state.selectedMarker = null;
		}
	},
  actions: {
		async LOGIN({ commit }, data) {
			const response = await loginUser(data);
			console.log("LOGIN")
		
			// 로그인 성공
			if(response.data.code == 0){
				console.log(response.data.msg)
				console.log(response.data.data)
				console.log(response.data.data.nickName)
				const data = {
					userId : response.data.data.userId,
					nickName : response.data.data.nickName
				}
				commit('SET_USER',data);
				commit('SET_TOKEN', response.data.data.token);
				saveUserToCookie(response.data.data.userId);
				saveAuthToCookie(response.data.data.token);

			}else{ // 로그인실패
				console.log(response.data.code)
				console.log(response.data.msg)
			}
      
			
			return response;
		},
	},
  modules: {
  }
})
