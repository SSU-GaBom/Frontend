import axios from 'axios'

function joinUser(user){
    console.log("signup")
    return axios.post("/api/signup",user);    
}

function validateLoginId(userId){
    console.log(userId)
    return axios.get(`/api/checkId/${userId}`);
}

function loginUser(loginDto){
    console.log(loginDto)
    return axios.post("api/signin",loginDto)
}

function testApi(userId){
    console.log(userId)
    return axios.get(`/api/testApi/${userId}`)
}

export {
    joinUser , validateLoginId ,testApi ,loginUser
}