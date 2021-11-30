import axios from 'axios';
import authHeader from './AuthHeader';

const STATION_API_BASE_URL = "http://localhost:8080/trip";

class TripService {
    getTrips(){
        return axios.get(STATION_API_BASE_URL);
    }

    getActiveTripsByDriver(){
        return axios.get(STATION_API_BASE_URL+"/active/driver",{headers: authHeader()});
    }
    getActiveTripsByUser(){
        return axios.get(STATION_API_BASE_URL+"/active",{headers: authHeader()});
    }

    getAllTripsByDriver(){
        return axios.get(STATION_API_BASE_URL+"/alld",{headers: authHeader()});
    }
    getAllTripsByUser(){
        return axios.get(STATION_API_BASE_URL+"/allu",{headers: authHeader()});
    }

    getTrip(tripID){
        return axios.get(STATION_API_BASE_URL+"/"+tripID);
    }

    postTrip(trip){
        axios.post(STATION_API_BASE_URL,trip,{headers: authHeader()});
    }

    editTrip(trip){
        axios.put(STATION_API_BASE_URL+'/update',trip,{headers: authHeader()});
    }

    deleteTrip(tripID){
        axios.delete(STATION_API_BASE_URL+"/"+tripID,{headers: authHeader()})
    }


}

export default new TripService()