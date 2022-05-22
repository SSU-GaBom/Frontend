import {auth} from './index'
import store from '../store/index'

function writeTravel(travelDto){
    console.log("writeTravel")
    console.log(travelDto)
    // for (let i = 0; i < travelDto.pinList.length; i++) {
    //     console.log(travelDto.pinList[i].location)
    //     console.log(travelDto.pinList[i].locationContent)
    //     console.log(travelDto.pinList[i].images.getAll('images'))
    // }
    return auth.post(`/travel`,travelDto)
}

function getTravel() {
    console.log("getList")
    return auth.get(`/travellist/update`)
}

function getTitleTravel(title) {
    console.log("getTitleList")
    return auth.get(`/travellist/find/${title}`)
}

function getTravelDetail(travelId) {
    console.log("Detail-On")
    return auth.get(`/travel/${travelId}`)
}

function postLiked(likedCnt, isLike, travelId) {
    console.log("postLiked")
    if (isLike === false) {
        console.log("likedCnt-On")
        return auth.post(`/travel/like/${travelId}`)
    } else {
        console.log("likedCnt-Off")
        return auth.post(`/travel/like/cancel/${travelId}`)
    }
}

function postZzim(zzimCnt, isZzim, travelId) {
	console.log("postZzim")
	console.log(zzimCnt)
    if (isZzim === false) {
        console.log("zzimCnt-On")
        return auth.post(`/travel/zzim/${travelId}`)
    } else {
        console.log("zzimCnt-Off")
        return auth.post(`/travel/zzim/cancel/${travelId}`)
    }
}

function getRank() {
    return auth.get(`rank`)
}

export {writeTravel, getTravel, getTitleTravel, getTravelDetail, postLiked, postZzim, getRank}

