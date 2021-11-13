import React, { useState } from 'react';
import DatePicker from "react-datepicker";
import { useHistory } from "react-router";
import AuthService from "../services/AuthService";
import 'react-datepicker/dist/react-datepicker.css'
    const SignUp = () => {
        const [startDate, setStartDate] = useState(new Date());

        const useDate = new Date();
                
        const [msg, setMsg] = React.useState(null);
        const History = useHistory();
        const email = React.useRef();
        const firstname = React.useRef();
        const lastname = React.useRef();
        const dob = React.useRef();
        const gender = React.useRef();
        const phonenum = React.useRef();
        const password = React.useRef();
        const handleRegistration = e => {
            e.preventDefault();
            console.log(email.current.value,firstname.current.value,lastname.current.value,useDate.toISOString().split('T')[0],gender.current.value,phonenum.current.value,password.current.value);
            AuthService.register(email.current.value,firstname.current.value,lastname.current.value,useDate.toISOString().split('T')[0],gender.current.value,phonenum.current.value,password.current.value).then((response) => response.json())
            .then((responseData) => {
                console.log(JSON.stringify(responseData));
                localStorage.setItem("user", JSON.stringify(responseData));
                History.push("/");
                window.location.reload();
            }).catch(err=>{setMsg("Error");})
            History.push("/sign-in");
            window.location.reload();
        }




        return (
            <form onSubmit={handleRegistration}>
                <h3>Sign Up</h3>

                <div className="form-group">
                    <label>First name</label>
                    <input type="text" className="form-control" placeholder="First name" required ref={firstname}/>
                </div>

                <div className="form-group">
                    <label>Last name</label>
                    <input type="text" className="form-control" placeholder="Last name" required ref={lastname}/>
                </div>

                <div className="form-group">
                    <label>Email address</label>
                    <input type="email" className="form-control" placeholder="Enter email" required ref={email}/>
                </div>

                <div className="form-group">
                    <label>Date of Birth</label>
                    <DatePicker className="form-control" closeOnScroll={true} selected={startDate} onChange={(date) => setStartDate(date)} maxDate={new Date()} showYearDropdown required ref={dob}/>
                </div>

                
                <div className="form-group">
                <label>Gender</label>
                <select className="auth-wrapper form-control" required ref={gender}>
                    <option hidden >Selection</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="other">Other</option>
                </select>
                </div>

                <div className="form-group">
                    <label>Phone number</label>
                    <input type="text" className="form-control" placeholder="Enter number" required ref={phonenum}/>
                </div>

                <div className="form-group">
                    <label>Password</label>
                    <input type="password" className="form-control" placeholder="Enter password" required ref={password}/>
                </div>
                <br></br>
                <button type="submit" className="btn btn-primary btn-block">Sign Up</button>
            </form>
        );
    }

    export default SignUp;