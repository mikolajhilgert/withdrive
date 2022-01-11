import axios from 'axios';
import authHeader from './AuthHeader';

const API_BASE_URL = "http://localhost:8080/rating";

class ReviewService {

    postRating(review){
        axios.post(API_BASE_URL,review,{headers: authHeader()});
    }

    getAverageRating(userID){
        return axios.get(API_BASE_URL+"/avg/"+userID);
    }

    hasUserLeftReview(tripID){
        return axios.get(API_BASE_URL+"/check/"+tripID,{headers: authHeader()})
    }

    getRatingsByDriver(userID){
        return axios.get(API_BASE_URL+"/driver/"+userID)
    }
}

export default new ReviewService()