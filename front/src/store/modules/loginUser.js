
import Vue from 'vue'
import Vuex from 'vuex'
import {loginUser} from '../../api/auth'


import {
        saveAuthToCookie,
        saveUserToCookie,
        getUserFromCookie,
        deleteCookie} 
        from '../../utils/cookies'
        
Vue.use(Vuex)

const loginUserStore = new Vuex.Store({
    state : {
        user : {
            id : null,
            nickName : null
        },
        token : '',
    },
    getters : {
        userToken(state) {
            return state.token;
        },
        myNickName(state){
            return state.user.nickName;
        },
        isLoggedIn(state) {
            return !!state.token || getUserFromCookie();
        },
    },
    mutations : {
        SET_USER(state, data) {
            state.user.id = data.userId;
            state.user.nickName = data.nickName;
        },
        SET_TOKEN(state, token) {
            state.token = token;
        },
        LOGOUT(state) {
            console.log("logout2")
            state.user.id = null;
            state.user.nickName = null;
            state.token = null;
            deleteCookie('til_auth');
            deleteCookie('til_user');
        },
      },
      actions : {
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
      }
})

  
export default loginUserStore