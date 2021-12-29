import axios from 'axios';
import authHeader from './AuthHeader';
    const API_URL = "http://localhost:8080/user/";

    class AuthService {
    login(username, password) {
        var reqBody = "username="+username+"&password="+password+"&grant_type=password";
        return fetch(API_URL + "login", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            body: reqBody
        })
    }

    logout() {
        localStorage.removeItem("user");
    }

    register(email,firstName,lastName,dateOfBirth,gender,phoneNumber,password){
        return axios.post(API_URL + "register", {
            email,
            firstName,
            lastName,
            dateOfBirth,
            gender,
            phoneNumber,
            password
        });
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));;
    }

    checkToken(){
        async function check(){
            await axios.get(API_URL + "check",{headers: authHeader()}).catch(() => {window.history.pushState({}, '', "/sign-out");window.location.reload();});
        }
        check();
    }

    checkTokenAdmin(){    
        async function check(){
            return await axios.get(API_URL + "admin",{headers: authHeader()}).catch(() => {window.history.pushState({}, '', "/sign-out");window.location.reload();});
        }
        return check();
    }

    recoverPassword(email){
        var params = new URLSearchParams();
        params.append('email', email);

        axios.post(API_URL+"forgot_password",params);
    }

    changePassword(password,token){
        var params = new URLSearchParams();
        params.append('password', password);
        params.append('token', token);

        axios.post(API_URL+"reset_password",params);
    }

    verifyChangePassword(token){
        return axios.get(API_URL+"reset_password/"+token);
    }
}

    export default new AuthService();