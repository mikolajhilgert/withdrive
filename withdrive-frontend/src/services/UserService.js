import axios from 'axios';

const STATION_API_BASE_URL = "http://localhost:8080/user";

class UserService {

    getUsers(){
        return axios.get(STATION_API_BASE_URL);
    }

    createUser(User){
        return axios.post(STATION_API_BASE_URL, User);
    }

    getUserById(UserID){
        return axios.get(STATION_API_BASE_URL + '/' + UserID);
    }

    updateUser(User){
        return axios.put(STATION_API_BASE_URL, User);
    }

    deleteUser(UserID){
        return axios.delete(STATION_API_BASE_URL + '/' + UserID);
    }
}

export default new UserService()