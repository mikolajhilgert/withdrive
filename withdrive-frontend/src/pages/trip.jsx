import React, { useState,useEffect } from "react";
import TripService from '../services/TripService';
import Map from '../components/map.component'
import moment from 'moment';
import { Button } from "react-bootstrap";

let param = window.location.pathname;  //to find the path of id /viewjob/1
let id = param.split('/').pop();

const Trip = () => {
    const [trip,setTrip] = useState([]);
    //console.log(TripService.getTrip(id));
    useEffect(() => {
        getTrip()
    }, [])
    
    const getTrip = () => {
        TripService.getTrip(id).then((response) => {
            setTrip(response.data);
        });
    }   

    const locations = {
        origin: trip.date,
        destination: trip.destination
    }

    //console.log(trip.driver.frstName);
return (
<>
    <br></br>
    <div className="auth-wrapper"> 
        <div className="auth-inner">
                    <h3>Trip with placeholder from {trip.origin} to {trip.destination}</h3>
                    <div><h3>For {moment(trip.date).format('MMM. D, YYYY [at] h:mm A z')}</h3></div>
                    <h3>                        </h3>
                    <table className="table">
                        <tbody>
                            <tr>
                            <td>Price: €{trip.pricePerPassenger}</td>
                            </tr>
                            <tr>
                            <td>Available seats: 2/{trip.maxPassengers}</td>
                            </tr>
                            <tr>
                            <td>{trip.description}</td>
                            </tr>
                            <tr>
                            <td>Driver rating: ☆★★★★ <a href="reviews">Read reviews</a></td>
                            </tr>
                        </tbody>
                    </table>
                    <h3><Button> Apply now!</Button></h3>
                    
                    {/* <div>A trip with <text dangerouslySetInnerHTML={{__html: tripDetails.date}}></text></div> */}
                    <h4>Trip map:</h4>
                    <Map/>    
        </div>
    </div>
</>
);
};

export default Trip;