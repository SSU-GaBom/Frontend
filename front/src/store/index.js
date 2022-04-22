import Vue from 'vue'
import Vuex from 'vuex'
import {loginUser} from '../api/index'
import {
        saveAuthToCookie,
        saveUserToCookie,
        getUserFromCookie,
        deleteCookie} 
        from '../utils/cookies'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user : {},
    token : '',
  },
  getters: {
		isLoggedIn(state) {
			return !!state.token || getUserFromCookie();
		},
		userToken(state) {
			return state.token;
		},
	},
  mutations: {
		SET_USER(state, user) {
			state.user = user;
		},
		SET_TOKEN(state, token) {
			state.token = token;
		},
		LOGOUT(state) {
			state.user = null;
			state.token = null;
			deleteCookie('til_auth');
			deleteCookie('til_user');
		},
	},
  actions: {
		async LOGIN({ commit }, data) {
			const response = await loginUser(data);
      console.log("LOGIN")
      console.log(response)
      console.log(response.data.data)
      
			commit('SET_USER', response.data.user);
			commit('SET_TOKEN', response.data.data);
			saveUserToCookie(response.data.user.username);
			saveAuthToCookie(response.data.token);
			return response;
		},
	},
  modules: {
  }
})
