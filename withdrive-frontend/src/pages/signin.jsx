import React, { Component } from "react";
import { useHistory } from "react-router";
import AuthService from "../services/AuthService";
import { useEffect } from "react";

const SignIn = () => {
        const [msg, setMsg] = React.useState(null);
        const History = useHistory();
        const email = React.useRef();
        const password = React.useRef();
        const handleLogin = e =>{
            e.preventDefault();
            AuthService.login(email.current.value,password.current.value).then((response) => response.json())
            .then((responseData) => {
                console.log(JSON.stringify(responseData));
                localStorage.setItem("user", JSON.stringify(responseData));
                History.push("/");
                window.location.reload();
            }).catch(err=>{setMsg("Error");})
        }

        return (
            <form onSubmit={handleLogin}>
                <h3>Sign In</h3>

                <div className="form-group">
                    <label>Email address</label>
                    <input type="email" className="form-control" placeholder="Enter email" ref={email}/>
                </div>

                <div className="form-group">
                    <label>Password</label>
                    <input type="password" className="form-control" placeholder="Enter password" ref={password}/>
                </div>

                <div className="form-group">
                    <div className="custom-control custom-checkbox">
                        <input type="checkbox" className="custom-control-input" id="customCheck1" />
                        <label className="custom-control-label" htmlFor="customCheck1">Remember me</label>
                    </div>
                </div>
                
                <br></br>
                <button type="submit" className="btn btn-primary btn-block">Submit</button>
                <p className="forgot-password text-right">
                    Forgot <a href="#">password?</a>
                </p>
                <h1>{msg}</h1>
            </form>
        );
    }
export default SignIn;