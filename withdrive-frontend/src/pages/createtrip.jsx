import React, { useState } from 'react';
import cities from '../data/nl.json'
import Select,{createFilter} from 'react-select';
import '../css_override/largerForm.css';
import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker.css'


const SignUp = () => {
    const [origin, setOrigin] = useState("");
    const [destination, setDestination] = useState("");
    const [startDate, setStartDate] = useState(new Date());

    const useDate = new Date();
        return (
            <form>
            <h3>Tell us about your ride</h3>
            <div className="form-group">
                    <label>Date and time of the trip:</label>
                    <DatePicker className="form-control" closeOnScroll={true} selected={startDate} onChange={(date) => setStartDate(date)} minDate={new Date()} required showTimeSelect  dateFormat="Pp"/>
                </div>

            <br></br>
            <div className="form-group">
                <label>Trip origin:</label>
                <Select 
                    name="origin"
                    value={origin}
                    onChange={setOrigin}
                    options={cities.map(result=>result)}
                    getOptionLabel={(option) => option.city}
                    getOptionValue={(option) => option.city}
                    filterOption={createFilter({ignoreAccents: false})} required>
                </Select>
            </div>
            <br></br>
            <div className="form-group">
                <label>Trip destination:</label>
                <Select 
                    name="destination"
                    value={destination}
                    onChange={setDestination}
                    options={cities.map(result=>result)}
                    getOptionLabel={(option) => option.city}
                    getOptionValue={(option) => option.city}
                    filterOption={createFilter({ignoreAccents: false})} required>
                </Select>
            </div>
            <hr></hr>
            <div className="form-group">
                    <label>Car license plate:</label>
                    <input type="text" className="form-control" placeholder="60-PJR-12" required/>
            </div>
            <br></br>
            <div className="form-group">
                <label>Details:</label>
                <textarea name="Text1" className="form-control" cols="25" rows="5" required></textarea>
            </div>
            <br></br>
            <div className="form-group">
                <label>Price per passenger (€):</label>
                <input className="form-control" type="number" min="0.00" max="100.00" step="0.5" defaultValue="0" required/>
                <label>Max passengers:</label>
                <input className="form-control" type="number" min="1" max="8" step="1" defaultValue="1" required/>
            </div> 
            <br></br><br></br>
                <button type="submit" className="btn btn-primary btn-block form-control">Create trip</button>
            </form>


/* <select>
<option selected disabled="true">--- Origin Selector ---</option>
{cities.map(result=>(<option text={result.city}></option>))}

</select> */
        );
    }
    export default SignUp;