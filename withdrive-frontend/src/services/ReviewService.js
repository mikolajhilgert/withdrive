import axios from 'axios';
import authHeader from './AuthHeader';

const API_BASE_URL = "http://localhost:8080/rating";

class ReviewService {

    postRating(review){
        return axios.post(API_BASE_URL,review,{headers: authHeader()});
    }
}

export default new ReviewService()