import React, { Component } from "react";
import { useHistory } from "react-router";
import AuthService from "../services/AuthService";
import LockOutlinedIcon from '@material-ui/icons/LockOpen';

const SignIn = () => {
        const [msg, setMsg] = React.useState(null);
        const History = useHistory();
        const email = React.useRef();
        const password = React.useRef();
        const handleLogin = e =>{
            e.preventDefault();
            AuthService.login(email.current.value,password.current.value)
                .then((response) => response.json())
                .then((responseData) => {
                    console.log(JSON.stringify(responseData));
                    localStorage.setItem("user", JSON.stringify(responseData));
                    History.push("/view-trips");
                    window.location.reload();
                    this.email.value = "";
                    this.password.value = "";
                })
                .catch(err=>{setMsg("Error");})
        }

        return (
        <div className="auth-wrapper">  
            <div className="auth-inner">
            <form onSubmit={handleLogin}>
                <center><LockOutlinedIcon/></center>
                <h2><center>Sign In</center></h2>
                <h4>              </h4>
                <div className="form-group">
                    <label>Email address</label>
                    <input type="email" className="form-control" placeholder="Enter email" ref={email} required/>
                </div>

                <div className="form-group">
                    <label>Password</label>
                    <input type="password" className="form-control" placeholder="Enter password" ref={password} required/>
                </div>
                
                <br></br>
                <div className="form-group"><button type="submit" className="btn btn-primary btn-block">Submit</button></div>
                <p className="forgot-password text-right">
                    Forgot <a href="#">password?</a>
                </p>
                <h1>{msg}</h1>
            </form>
            </div>
        </div>
        );
    }
export default SignIn;