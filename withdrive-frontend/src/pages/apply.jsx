import React from 'react';
import IsAuthenticated from '../components/accessCheck.component';
import { useHistory } from "react-router";
import 'react-datepicker/dist/react-datepicker.css';

import form from '../modules/innerPage.module.css'
import text from '../modules/text.module.css'
import ApplicationService from '../services/ApplicationService';

let id = window.location.pathname.split('/').pop();

const CreateTrip = () => {

IsAuthenticated();

const History =  useHistory();
const app = React.useRef();
const [msg, setMsg] = React.useState(null);

const handleRegistration = e => {
    e.preventDefault();
    const tripApplication = {
        trip: id,
        text: app.current.value,
    };
    ApplicationService.postApp(tripApplication);
    alert("Your application has been submitted!");
    History.push("/my-trips");
    window.location.reload();
}
return (
<div className={form.authwrapper}>
    <div className={form.authinner_form}>
        <form onSubmit={handleRegistration}>
            <h3 className={text.center}>Your application should be filed below:</h3>
            <br></br><br></br>
            <div className="form-group">
                <label>Application:</label>
                <textarea name="details" className="form-control" cols="25" rows="15" minlength="25" required ref={app}></textarea>
            </div>
            <br></br><br></br>
            <button type="submit" className="btn btn-primary btn-block form-control">Submit application!</button>
            <h3>{msg}</h3>
        </form>
    </div>
</div>
);
}
export default CreateTrip;