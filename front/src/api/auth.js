import { noAuth } from './index';

function joinUser(user){
    console.log("signup")
    return noAuth.post("/signup",user);    
}

function validateLoginId(userId){
    console.log(userId)
    return noAuth.get(`/check-id/${userId}`);
}

function validateNickName(nickName){
    console.log(nickName)
    return noAuth.get(`/check-nickname/${nickName}`);
}

function loginUser(loginDto){
    return noAuth.post("/signin",loginDto)
}

export{
    joinUser , validateLoginId , validateNickName , loginUser
}