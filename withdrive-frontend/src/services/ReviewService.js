import axios from 'axios';
import authHeader from './AuthHeader';

const API_BASE_URL = "http://localhost:8080/rating";

class ReviewService {

    postRating(review){
        return axios.post(API_BASE_URL,review,{headers: authHeader()});
    }

    getAverageRating(userID){
        return axios.get(API_BASE_URL+"/avg/"+userID);
    }
}

export default new ReviewService()