import React, { useState } from 'react';
import { useHistory } from "react-router";
import cities from '../data/nl.json'
import Select,{createFilter} from 'react-select';
import '../css_override/largerForm.css';
import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker.css';
import moment from 'moment';
import TripService from '../services/TripService';
import CustomOption from '../components/selectFix.component';

const CreateTrip = () => {
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
    if(origin.city != null && destination.city != null && startDate != new Date()){
        const trip = {
            origin: origin.city,
            destination: destination.city,
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

return (
<div className="auth-wrapper">
    <div className="auth-inner">
        <form onSubmit={handleRegistration}>
            <h3>Tell us about your ride</h3>
            <div className="form-group">
                <label>Date and time of the trip:</label>
                <DatePicker className="form-control" dateFormat="yyyy/MM/dd" closeOnScroll={true} selected={startDate} onChange={(date)=>
                    setStartDate(date)} minDate={new Date()} required showTimeSelect dateFormat="Pp"/>
            </div>

            <br></br>
            <div className="form-group">
                <label>Trip origin:</label>
                <Select name="origin" value={origin} onChange={setOrigin} options={cities.map(result=>result)}
                    getOptionLabel={(option) => option.city}
                    getOptionValue={(option) => option.city}
                    filterOption={createFilter({ignoreAccents: false})}
                    components={{ Option: CustomOption }}
                >
                </Select>
            </div>
            <br></br>
            <div className="form-group">
                <label>Trip destination:</label>
                <Select name="destination" value={destination} onChange={setDestination}
                    options={cities.map(result=>result)}
                    getOptionLabel={(option) => option.city}
                    getOptionValue={(option) => option.city}
                    filterOption={createFilter({ignoreAccents: false})}
                    components={{ Option: CustomOption }}
                >
                </Select>
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
            <br></br><br></br>
            <button type="submit" className="btn btn-primary btn-block form-control">Create trip</button>
            <h3>{msg}</h3>
        </form>

    </div>
</div>


/* <select>
    <option selected disabled="true">--- Origin Selector ---</option>
    {cities.map(result=>(<option text={result.city}></option>))}

</select> */
);
}
export default CreateTrip;