import {auth} from './index'
import store from '../store/index'

function writeTravel(travelDto){
    console.log("writeTravel")
    console.log(travelDto)
    // return auth.post(`/travel`,travelDto)
}

export { writeTravel}