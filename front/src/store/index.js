import Vue from 'vue'
import Vuex from 'vuex'
import {loginUser} from '../api/auth'
import {
        saveAuthToCookie,
        saveUserToCookie,
        getUserFromCookie,
        deleteCookie} 
        from '../utils/cookies'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user : {
		id : null,
		nickName : null
	},
    token : '',
	viewUser : {
		nickName : null,
		profileImage : null,
		followerCount : null,
		followingCount : null,
		travelList : [],
		wishList : [],
	},
	travelList : []
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
		myTravelList(state){
			return state.travelList;
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
			deleteCookie('til_auth');
			deleteCookie('til_user');
		},
		SET_VIEWUSER(state,data){
			console.log("set_viewuser")
			state.viewUser.nickName = data.nickName;
			state.viewUser.followerCount = data.userFollowerCount;
			state.viewUser.followingCount = data.userFollowingCount;
		},
		SET_TRAVEL(state,travel){
			state.travelList.push(travel);
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
