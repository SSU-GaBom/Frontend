import {auth} from './index'
import store from '../store/index'

function getUserInfo(nickName){
    return auth.get(`/profile/${nickName}`)
}

function uploadImage(formData){
    console.log("uploadImage")
    return auth.post(`/profile/update-profile/${store.state.user.nickName}`,
    formData , {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    })
}

function getUserFollower(nickName){
    console.log("getUserFollower")
    return auth.get(`/profile/follow/${nickName}/follower`)
}

function getUserFollowing(nickName){
    console.log("getUserFollowing")
    return auth.get(`/profile/follow/${nickName}/following`)
}

export {
    getUserInfo , uploadImage , getUserFollower , getUserFollowing
}