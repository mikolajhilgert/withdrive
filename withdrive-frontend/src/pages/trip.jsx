import React, { useState} from "react";
import TripService from '../services/TripService';
import ReviewService from "../services/ReviewService";
import Map from '../components/map.component'
import moment from 'moment';
import { Button } from "react-bootstrap";
import NotFound from './notfound'
import Rating from '@mui/material/Rating';
import BackButton from '../components/backButton.component'
import AuthService from "../services/AuthService";

import form from '../modules/innerPage.module.css'

let id = window.location.pathname.split('/').pop();

const Trip = () => {
    const [trip,setTrip] = useState(null);
    const [rating,setRating] = useState(0);

    React.useEffect(() => {
        TripService.getTrip(id).then((response) => {
            setTrip(response.data);
            
        });

    }, [id])

    if(trip !== null){
        ReviewService.getAverageRating(trip.driver.userID).then((response) => {
            setRating(response.data);
        });
    }

    if(!trip) return <NotFound/>;

function showButton(actual,max){
    if(actual<max){
        return(<Button onClick={()=>{
            if(AuthService.getCurrentUser() !== null){
                window.history.pushState({}, '', "/trip/apply/"+id);window.location.reload();
            }else{
                window.history.pushState({}, '', "/sign-in");window.location.reload();
            }
            
        }}> Apply now!</Button>)
    }else{
        return("Trip is full!")
    }
}

return (
<>
    <br></br>
    <div className={form.authwrapper}> 
        <div className={form.authinner_trip}>
            <BackButton/>
                    <h3>Trip with {trip.driver.firstName} from {trip.origin} to {trip.destination}</h3>
                    <div><h3>For {moment(trip.date).format('MMM. D, YYYY [at] h:mm A z')}</h3></div>
                    <table className="table">
                        <tbody>
                            <tr>
                            <td>Price: €{trip.pricePerPassenger}</td>
                            </tr>
                            <tr>
                            <td>Available seats: {trip.maxPassengers-trip.passengers.length}/{trip.maxPassengers}</td>
                            </tr>
                            <tr>
                            <td>{trip.description}</td>
                            </tr>
                            <tr>
                                <td>
                                Driver rating:
                                <br></br>
                                <Rating name="read-only" value={rating} precision={0.5} readOnly/>
                                <br></br>
                                <a href="reviews">Read reviews of the driver</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    
                    <center><h3>{showButton(trip.passengers.length,trip.maxPassengers)}</h3></center>
                    <br></br>
                    <Map trip={trip} />    
        </div>
    </div>
</>
);
};

export default Trip;