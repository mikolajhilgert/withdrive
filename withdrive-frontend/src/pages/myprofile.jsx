import React, { useState,useEffect } from 'react';
import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker.css'
import { useHistory } from "react-router";
import UserService from "../services/UserService";
import moment from 'moment';
import Person from '@material-ui/icons/Person';
import BackButton from '../components/backButton.component'

import form from '../modules/innerPage.module.css'
import NotFound from './notfound';

    const MyProfile = () => {
        
        const [startDate, setStartDate] = useState(new Date());
        const [user, setUser] = useState(null);
        
        useEffect(() => {
            UserService.getCurrentDetails().then((response) => {
                setUser(response.data);
        })}, [])
        
        const [msg, setMsg] = React.useState(null);
        const History = useHistory();
        const email = React.useRef();
        const firstname = React.useRef();
        const lastname = React.useRef();
        const dob = React.useRef();
        const gender = React.useRef();
        const phonenum = React.useRef();
        const password = React.useRef();
        const passwordCheck = React.useRef();

        const handleRegistration = e => {
            e.preventDefault();
            if(password.current.value === passwordCheck.current.value===""){
                UserService.updateUser(email.current.value,firstname.current.value,lastname.current.value,moment(startDate).format("DD-MM-YYYY"),gender.current.value,phonenum.current.value)
                .then(() => {
                    History.push("/view-trips");
                    window.location.reload();
                    }).catch(err=>{setMsg("Error");})
            }
            else if(password.current.value === passwordCheck.current.value){
                UserService.updateUser(email.current.value,firstname.current.value,lastname.current.value,moment(startDate).format("DD-MM-YYYY"),gender.current.value,phonenum.current.value,password.current.value)
                .then(() => {
                    History.push("/view-trips");
                    window.location.reload();
                    }).catch(err=>{setMsg("Error");})

            }else{
                setMsg("Error: Passwords do not match");
            }
        }
        if(!user){return <NotFound/>}

        return (
        <div className={form.authwrapper}>  
            <div className={form.authinner_form}>
            <BackButton />
            <form onSubmit={handleRegistration}>
            <center><Person size="big"/></center>
                <center><h3>My profile</h3></center>
                {/* <center><p>(No ability to change email)</p></center> */}

                <div className="form-group">
                    <label>First name</label>
                    <input type="text" className="form-control" defaultValue={user.firstName} placeholder="First name" required ref={firstname}/>
                </div>

                <div className="form-group">
                    <label>Last name</label>
                    <input type="text" className="form-control" defaultValue={user.lastName} placeholder="Last name" required ref={lastname}/>
                </div>

                <div className="form-group">
                    <label>Email address</label>
                    <input type="email" className="form-control" defaultValue={user.email} placeholder="Enter email" required ref={email}/>
                </div>

                <div className="form-group">
                    <label>Date of Birth</label>
                    <DatePicker className="form-control" closeOnScroll={true} defaultValue={user.dateOfBirth} selected={startDate} onChange={(date) => setStartDate(date)} maxDate={new Date()} showYearDropdown required ref={dob}/>
                </div>

                
                <div className="form-group">
                <label>Gender</label>
                <select className="auth-wrapper form-control" defaultValue={user.gender} required ref={gender}>
                    <option value="male" >Male</option>
                    <option value="female" >Female</option>
                    <option value="other">Other</option>
                </select>
                </div>

                <div className="form-group">
                    <label>Phone number</label>
                    <input type="text" className="form-control" placeholder="Enter number" defaultValue={user.phoneNumber} required ref={phonenum}/>
                </div>

                <div className="form-group">
                    <label>Password</label>
                    <input type="password" className="form-control" minlength="6" maxLength="15" placeholder="Enter password" ref={password} maxLength="4" maxLength="14"/>
                </div>
                
                <div className="form-group">
                    <label>Repeat Password</label>
                    <input type="password" className="form-control" minlength="6" maxLength="15" placeholder="Enter password" ref={passwordCheck} maxLength="4" maxLength="14"/>
                    <br></br>
                    <center><button type="submit" className="btn btn-primary btn-block">Update personal details</button></center>
                </div>
                <h3>{msg}</h3>
            </form>
            </div>
        </div>
        );
    }

    export default MyProfile;