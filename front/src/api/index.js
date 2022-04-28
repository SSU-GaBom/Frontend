import axios from 'axios'
import {setInterceptors} from './interceptors'

// instance & interceptor
function beforeLogin(url, options) {
	const instance = axios.create(Object.assign({ baseURL: url }, options));
	return instance;
}

function afterLogin(url, options) {
	const instance = axios.create(Object.assign({ baseURL: url }, options));
	setInterceptors(instance);
	return instance;
}

export const noAuth = beforeLogin("/api");
export const auth = afterLogin("/api");



// function testApi(userId){
//     console.log(userId)
//     return axios.get(`/testApi/${userId}`)
// }

// function testUserApi(userId){
//     console.log("testUserApi")
//     return axios.get(`/testUserApi/${userId}`)
// }

