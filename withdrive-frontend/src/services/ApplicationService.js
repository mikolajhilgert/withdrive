import axios from 'axios';
import authHeader from './AuthHeader';

const STATION_API_BASE_URL = "http://localhost:8080/trip/app";

class ApplicationService {
    postApp(app){
        axios.post(STATION_API_BASE_URL,app,{headers: authHeader()});
    }


}

export default new ApplicationService()