import {auth} from './index'
import store from '../store/index'

function getUserInfo(nickName){
    console.log("api.profile.getUserInfo")
    return auth.get(`/profile/${nickName}`)
}

function uploadImage(formData){
    console.log("api.profile.uploadImage")
    return auth.put(`/profile/update-profile/${store.state.user.nickName}`,
    formData , {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    })
}

function getUserFollower(nickName){
    console.log("api.profile.getUserFollower")
    return auth.get(`/profile/follow/${nickName}/follower`)
}

function getUserFollowing(nickName){
    console.log("api.profile.getUserFollowing")
    return auth.get(`/profile/follow/${nickName}/following`)
}

export {
    getUserInfo , uploadImage , getUserFollower , getUserFollowing
}