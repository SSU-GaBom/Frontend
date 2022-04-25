import axios from 'axios'

function joinUser(user){
    console.log("signup")
    return axios.post("/api/signup",user);    
}

function validateLoginId(userId){
    console.log(userId)
    return axios.get(`/api/checkId/${userId}`);
}

function validateNickName(nickName){
    console.log(nickName)
    return axios.get(`/api/checkNickName/${nickName}`);
}

function loginUser(loginDto){
    return axios.post("api/signin",loginDto)
}

function testApi(userId){
    console.log(userId)
    return axios.get(`/api/testApi/${userId}`)
}

function testUserApi(userId){
    console.log("testUserApi")
    return axios.get(`/api/testUserApi/${userId}`)
}

export {
    joinUser , validateLoginId ,testApi ,loginUser , testUserApi , validateNickName
}