import React, { useState, useEffect} from 'react';
import { useHistory } from "react-router";
import cities from '../data/nl.json'
import Select, { createFilter } from 'react-select';
import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker.css';
import moment from 'moment';
import TripService from '../services/TripService';
import CustomOption from '../components/selectFix.component';
import IsAuthenticated from '../components/accessCheck.component';
import AuthService from '../services/AuthService';
import NotFound from '../pages/notfound'
import BackButton from '../components/backButton.component'

import form from '../modules/innerPage.module.css'

let id = window.location.pathname.split('/').pop();

const EditTrip = () => {

    IsAuthenticated();

    const [trip, setTrip] = useState();
    const [origin, setOrigin] = useState("");
    const [destination, setDestination] = useState("");

    useEffect(() => {
        TripService.getTrip(id).then((response) => {
            setTrip(response.data);
            setOrigin(cities.find(o => o.city === response.data.origin));
            setDestination(cities.find(o => o.city === response.data.destination));

        });
    }, [id])


    IsAuthenticated(AuthService.getCurrentUser());
    const History = useHistory();

    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
        setOpen(!open);
    };

    const [startDate, setStartDate] = useState(new Date());

    const [msg, setMsg] = React.useState(null);

    const plate = React.useRef();
    const details = React.useRef();
    const price = React.useRef();
    const max = React.useRef();
    
    const handleRegistration = e => {
        e.preventDefault();
        if (origin.city !== null && destination.city !== null && startDate !== new Date()) {
            const trip = {
                tripID: id,
                origin: origin.city,
                destination: destination.city,
                description: details.current.value,
                date: moment(startDate).format('YYYY-MM-DDTHH:mm'),
                licensePlate: plate.current.value,
                maxPassengers: max.current.value,
                pricePerPassenger: price.current.value,
            };
            TripService.editTrip(trip);
            alert("Your trip listing has been updated!");
            History.push("/driver-trips");
            window.location.reload();

        } else {
            setMsg("Error: Details missing");
        }
    }

    function handleDelete(){
        var r = window.confirm("Are you sure you want to delete this trip?\nYou cannot undo this action!");
        if (r === true) {
            TripService.deleteTrip(id);
            History.push("/driver-trips");
            window.location.reload();
        }
    }


    if (!trip) return <NotFound/>;
    return (
<div className={form.authwrapper}>
    <div className={form.authinner_big}>
            <BackButton />
                <form onSubmit={handleRegistration}>
                    
                    <h3>Edit ride</h3>
                    <div className="form-group">
                        <label>Date and time of the trip:</label>
                        <DatePicker className="form-control" dateFormat="yyyy/MM/dd" closeOnScroll={true} selected={moment(trip.date).toDate()} onChange={(date) =>
                            setStartDate(date)} minDate={new Date()} required showTimeSelect dateFormat="Pp" />
                    </div>

                    <br></br>
                    <div className="form-group">
                        <label>Trip origin:</label>
                        <Select
                            name="origin"
                            value={origin}
                            onChange={setOrigin}
                            options={cities.map(result => result)}
                            getOptionLabel={(option) => option.city}
                            getOptionValue={(option) => option.city}
                            filterOption={createFilter({ ignoreAccents: false })}
                            components={{ Option: CustomOption }}
                        >
                        </Select>
                    </div>
                    <br></br>
                    <div className="form-group">
                        <label>Trip destination:</label>
                        <Select name="destination" value={destination} onChange={setDestination}
                            options={cities.map(result => result)}
                            getOptionLabel={(option) => option.city}
                            getOptionValue={(option) => option.city}
                            filterOption={createFilter({ ignoreAccents: false })}
                            components={{ Option: CustomOption }}
                        >
                        </Select>
                    </div>
                    <hr>
                    </hr>
                    <div className="form-group">
                        <label>Car license plate:</label>
                        <input type="text" className="form-control" defaultValue={trip.licensePlate} required ref={plate} />
                    </div>
                    <br></br>
                    <div className="form-group">
                        <label>Details:</label>
                        <textarea name="details" className="form-control" cols="25" rows="5" defaultValue={trip.description} required ref={details}></textarea>
                    </div>
                    <br></br>
                    <div className="form-group">
                        <label>Price per passenger (€):</label>
                        <input className="form-control" type="number" min="0.00" max="100.00" step="0.5" defaultValue={trip.pricePerPassenger}
                            required ref={price} />
                        <label>Max passengers:</label>
                        <input className="form-control" type="number" min="1" max="8" step="1" defaultValue={trip.maxPassengers} required ref={max} />
                    </div>
                    <br></br>
                    <button type="submit" className="btn btn-success btn-block form-control">Edit trip</button>
                    <h3>{msg}</h3>
                </form>
                
                <button onClick={handleDelete} className="btn btn-danger btn-block form-control">Delete trip</button>
                {/* { open && (<div><Confirmation/></div>)} */}
            </div>
        </div>
    );
}
export default EditTrip;