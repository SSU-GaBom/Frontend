import {auth} from './index'
import store from '../store/index'

function writeTravel(travelDto){
    console.log("writeTravel")
    console.log(travelDto)
    for (let i = 0; i < travelDto.pinList.length; i++) {
        console.log(travelDto.pinList[i].location)
        console.log(travelDto.pinList[i].card.getAll('images'))
        
    }
    //  return auth.post(`/test`,travelDto)
}

export { writeTravel}