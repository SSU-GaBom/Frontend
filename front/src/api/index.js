import axios from 'axios'

function joinUser(user){
    console.log("joinUser")
    return axios.post("/api/join",user);    
}

export {
    joinUser
}