import axios from 'axios';

const API_BASE_URL = "http://localhost:8080/user";

class UserService {

    getUsers(){
        return axios.get(API_BASE_URL);
    }

    createUser(User){
        return axios.post(API_BASE_URL, User);
    }

    getUserById(UserID){
        return axios.get(API_BASE_URL + '/' + UserID);
    }

    updateUser(User){
        return axios.put(API_BASE_URL, User);
    }

    deleteUser(UserID){
        return axios.delete(API_BASE_URL + '/' + UserID);
    }
}

export default new UserService()