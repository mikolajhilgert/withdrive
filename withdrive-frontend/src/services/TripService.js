import axios from 'axios';
import authHeader from './AuthHeader';

const STATION_API_BASE_URL = "http://localhost:8080/trip";

class TripService {
    getTrips(){
        return axios.get(STATION_API_BASE_URL);
    }

    getTrip(tripID){
        return axios.get(STATION_API_BASE_URL+"/"+tripID);
    }

    postTrip(trip){
        axios.post(STATION_API_BASE_URL,trip,{headers: authHeader()});
    }
}

export default new TripService()