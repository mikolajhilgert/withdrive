import React, { useState } from 'react';
import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker.css'
    const SignUp = () => {
        const [startDate, setStartDate] = useState(new Date());
        return (
            <form>
                <h3>Sign Up</h3>

                <div className="form-group">
                    <label>First name</label>
                    <input type="text" className="form-control" placeholder="First name" />
                </div>

                <div className="form-group">
                    <label>Last name</label>
                    <input type="text" className="form-control" placeholder="Last name" />
                </div>

                <div className="form-group">
                    <label>Email address</label>
                    <input type="email" className="form-control" placeholder="Enter email" />
                </div>

                <div className="form-group">
                    <label>Date of Birth</label>
                    <DatePicker className="form-control" closeOnScroll={true} selected={startDate} onChange={(date) => setStartDate(date)} maxDate={new Date()} showYearDropdown/>
                </div>

                
                <div className="form-group">
                <label>Gender</label>
                <select class="form-control">
                    <option hidden >Selection</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="other">Other</option>
                </select>
                </div>

                <div className="form-group">
                    <label>Password</label>
                    <input type="password" className="form-control" placeholder="Enter password" />
                </div>
                <br></br>
                <button type="submit" className="btn btn-primary btn-block">Sign Up</button>
            </form>
        );
    }

    export default SignUp;