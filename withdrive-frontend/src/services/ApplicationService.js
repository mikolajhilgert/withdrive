import axios from 'axios';
import authHeader from './AuthHeader';

const STATION_API_BASE_URL = "http://localhost:8080/trip/app";

class ApplicationService {
    postApp(app){
        axios.post(STATION_API_BASE_URL,app,{headers: authHeader()});
    }
    getApps(id){
        return axios.get(STATION_API_BASE_URL+"/t/"+id,{headers: authHeader()});
    }
    acceptApp(accept){
        axios.post(STATION_API_BASE_URL+"/accept",accept,{headers: authHeader()});
    }
    rejectApp(reject){
        axios.post(STATION_API_BASE_URL+"/reject",reject,{headers: authHeader()});
    }
    getActiveAppsByUser(){
        return axios.get(STATION_API_BASE_URL+"/u/active",{headers: authHeader()});
    }
    getAllAppsByUser(){
        return axios.get(STATION_API_BASE_URL+"/u",{headers: authHeader()});
    }
}

export default new ApplicationService()