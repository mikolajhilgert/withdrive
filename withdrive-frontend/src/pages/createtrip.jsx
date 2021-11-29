import React, { useState} from 'react';
import IsAuthenticated from '../components/accessCheck.component';
import { useHistory } from "react-router";
import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker.css';
import moment from 'moment';
import TripService from '../services/TripService';

import CitySelect from '../components/citySelect.component'

import form from '../modules/innerPage.module.css'
import text from '../modules/text.module.css'



const CreateTrip = () => {

IsAuthenticated();

const History =  useHistory();
const [origin, setOrigin] = useState("");
const [destination, setDestination] = useState("");
const [startDate, setStartDate] = useState(new Date());

const [msg, setMsg] = React.useState(null);

const plate = React.useRef();
const details = React.useRef();
const price = React.useRef();
const max = React.useRef();

const handleRegistration = e => {
    e.preventDefault();
    if(origin != null && destination != null && startDate != new Date()){
        const trip = {
            origin: origin,
            destination: destination,
            description: details.current.value,
            date: moment(startDate).format('YYYY-MM-DDTHH:mm'),
            licensePlate: plate.current.value,
            maxPassengers: max.current.value,
            pricePerPassenger: price.current.value,
        };
        TripService.postTrip(trip);
        alert("Your trip listing has been posted!");
        History.push("/view-trips");
        window.location.reload();

    }else{
        setMsg("Error: Details missing");
    }
}
const setOriginCity = (data) =>{
    setOrigin(data);
}
const setDestinationCity = (data) =>{
    setDestination(data);
}
return (
<div className={form.authwrapper}>
    <div className={form.authinner_big}>
        <form onSubmit={handleRegistration}>
            <h3 className={text.center}>Tell us about your ride</h3>
            <div className="form-group">
                <label>Date and time of the trip:</label>
                <DatePicker className="form-control" dateFormat="yyyy/MM/dd" closeOnScroll={true} selected={startDate} onChange={(date)=>
                    setStartDate(date)} minDate={new Date()} required showTimeSelect dateFormat="Pp"/>
            </div>

            <br></br>
            <div className="form-group">
                <label>Trip origin:</label>
                <CitySelect setCity={setOriginCity} text="Select your trips origin"/>
            </div>
            <br></br>
            <div className="form-group">
                <label>Trip destination:</label>
                <CitySelect setCity={setDestinationCity} text="Select your trips destination"/>
            </div>
            <hr>
            </hr>
            <div className="form-group">
                <label>Car license plate:</label>
                <input type="text" className="form-control" placeholder="60-PJR-12" required ref={plate}/>
            </div>
            <br></br>
            <div className="form-group">
                <label>Details:</label>
                <textarea name="details" className="form-control" cols="25" rows="5" required ref={details}></textarea>
            </div>
            <br></br>
            <div className="form-group">
                <label>Price per passenger (€):</label>
                <input className="form-control" type="number" min="0.00" max="100.00" step="0.5" defaultValue="0"
                    required ref={price}/>
                <label>Max passengers:</label>
                <input className="form-control" type="number" min="1" max="8" step="1" defaultValue="1" required ref={max}/>
            </div>
            <br></br>
            <button type="submit" className="btn btn-primary btn-block form-control">Create trip</button>
            <h3>{msg}</h3>
        </form>
    </div>
</div>
);
}
export default CreateTrip;