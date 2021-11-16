import React, { useState,useEffect } from "react";
import TripService from '../services/TripService';
import Map from '../components/map.component'

let param = window.location.pathname;  //to find the path of id /viewjob/1
let id = param.split('/').pop();

const Trip = () => {
    const [trip,setTrip] = useState([]);
    console.log(TripService.getTrip(id));
    useEffect(() => {
        getTrip()
    }, [])
    
    const getTrip = () => {
        TripService.getTrip(id).then((response) => {
            setTrip(response.data);
        });
    }   

    const tripDetails = {
        date: trip.date
    }

return (
<>
    <br></br>
    <div className="auth-wrapper"> 
        <div className="auth-inner">
                    <h3>Trip details:</h3>
                    <h3>                          </h3>
                    <div>A trip with <plaintext dangerouslySetInnerHTML={{__html: tripDetails.date}}></plaintext></div>
                    <Map></Map>    
        </div>
    </div>
</>
);
};

export default Trip;