import { noAuth } from './index';

function joinUser(user){
    console.log("signup")
    return noAuth.post("/signup",user);    
}

function validateLoginId(userId){
    console.log(userId)
    return noAuth.get(`/checkId/${userId}`);
}

function validateNickName(nickName){
    console.log(nickName)
    return noAuth.get(`/checkNickName/${nickName}`);
}

function loginUser(loginDto){
    return noAuth.post("/signin",loginDto)
}

export{
    joinUser , validateLoginId , validateNickName , loginUser
}