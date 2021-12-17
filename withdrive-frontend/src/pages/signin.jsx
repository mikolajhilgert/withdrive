import React from "react";
import { useHistory } from "react-router";
import AuthService from "../services/AuthService";
import LockOutlinedIcon from '@material-ui/icons/LockOpen';

import form from '../modules/innerPage.module.css'
import text from '../modules/text.module.css'

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
                    localStorage.setItem("user", JSON.stringify(responseData));
                    History.push("/view-trips");
                    window.location.reload();
                    this.email.value = "";
                    this.password.value = "";
                })
                .catch(err=>{setMsg("Detail mismatch");})
        }

        return (
        <div className={form.authwrapper}>  
            <div className={form.authinner_form}>
            <form onSubmit={handleLogin}>
                <center><LockOutlinedIcon/></center>
                <h2><center>Sign In</center></h2>
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
                <br></br>
                {/* <p className={text.right+" form-group"}>
                    <a href="#">Forgotten password?</a>
                </p> */}
                <h1>{msg}</h1>
            </form>
            </div>
        </div>
        );
    }
export default SignIn;