import axios from 'axios';
import authHeader from './AuthHeader';

const API_BASE_URL = "http://localhost:8080/user";

class UserService {

    getUsers(){
        return axios.get(API_BASE_URL,{headers: authHeader()});
    }

    createUser(User){
        return axios.post(API_BASE_URL, User);
    }

    updateUser(email,
        firstName,
        lastName,
        dateOfBirth,
        gender,
        phoneNumber,
        password){
        return axios.put(API_BASE_URL,{
            email,
            firstName,
            lastName,
            dateOfBirth,
            gender,
            phoneNumber,
            password
        },
            {headers: authHeader()});
    }

    getUserById(UserID){
        return axios.get(API_BASE_URL + '/' + UserID);
    }

    deleteUser(UserID){
        return axios.delete(API_BASE_URL + '/' + UserID,{headers: authHeader()});
    }

    getCurrentDetails(){
        return axios.get(API_BASE_URL+"/myDetails",{headers: authHeader()});
    }
}

export default new UserService()