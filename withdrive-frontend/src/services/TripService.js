import axios from 'axios';

const STATION_API_BASE_URL = "http://localhost:8080/trip";

class TripService {
    getTrips(){
        return axios.get(STATION_API_BASE_URL);
    }
}

export default new TripService()