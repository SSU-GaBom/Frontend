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
    
    return axios.post("api/signin",loginDto)
}

function testApi(userId){
    console.log(userId)
    return axios.get(`/api/testApi/${userId}`)
}

function testUser(){
    console.log("testUser")
    return axios.get('/api/testUser')
}

export {
    joinUser , validateLoginId ,testApi ,loginUser , testUser
}