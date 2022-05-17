import {auth} from './index'
import store from '../store/index'

function writeTravel(travelDto){
    console.log("writeTravel")
<<<<<<< HEAD
    // console.log(travelDto)
=======
    
    console.log(travelDto)
>>>>>>> c8a368701192bec0b1d91b2570f7e2cddbeed586
    // for (let i = 0; i < travelDto.pinList.length; i++) {
    //     console.log(travelDto.pinList[i].location)
    //     console.log(travelDto.pinList[i].locationContent)
    //     console.log(travelDto.pinList[i].images.getAll('images'))
    // }
    return auth.post(`/travel`,travelDto)
}

export { writeTravel}