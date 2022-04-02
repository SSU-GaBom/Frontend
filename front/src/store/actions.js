import {joinUser} from '../api/index.js'

export default {
    JOIN_USER(context){
        return joinUser().then(response => context.commit('SET_JOIN_USER' ,response.data));
    }
}