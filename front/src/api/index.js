import axios from 'axios'


function joinUser(user){
    console.log("joinUser")
    return axios.post("/api/join",user);    
}

function validateLoginId(loginId){
    console.log("validateLoginId")
    return axios.get("/api/validtaeLoginId,",loginId);
}

export {
    joinUser
}