import React,{useEffect} from "react";
import { useHistory } from "react-router";
import UserService from "../services/AuthService";
import LockOutlinedIcon from '@material-ui/icons/LockOpen';
import BackButton from '../components/backButton.component'

import form from '../modules/innerPage.module.css'
import text from '../modules/text.module.css'
import AuthService from "../services/AuthService";

let id = window.location.pathname.split('/').pop();

const ForgotPassword = () => {


        const [msg, setMsg] = React.useState(null);
        const History = useHistory();
        const password = React.useRef();
        const repeatpassword = React.useRef();

        useEffect(() => {
            console.log(id)
            AuthService.verifyChangePassword(id).catch(()=>{
                History.push("/");
                window.location.reload();
            })
        }, []);

        const handleChange = e =>{
            if(password.current.value === repeatpassword.current.value){
                e.preventDefault();
                AuthService.changePassword(password.current.value,id);
                History.push("/sign-in");
                window.location.reload();
            }else{
                setMsg("Passwords do not match");
            }
        }

        return (
        <div className={form.authwrapper}>  
            <div className={form.authinner_form}>
            <form onSubmit={handleChange}>
                <BackButton/>
                <center><LockOutlinedIcon/></center>
                <h2><center>Forgotten password</center></h2>
                <br></br>
                <div className="form-group">
                    <label>New password:</label>
                    <input type="password" className="form-control" placeholder="Enter password" ref={password} minlength="6" maxLength="15" required/>
                </div>
                <div className="form-group">
                    <label>Repeat password:</label>
                    <input type="password" className="form-control" placeholder="Repeat password" ref={repeatpassword} minlength="6" maxLength="15" required/>
                </div>
                <br></br>
                <center><div className="form-group"><button type="submit" className="btn btn-primary btn-block">Change password</button></div></center>
                <br></br>
                
                <h1>{msg}</h1>
            </form>
            </div>
        </div>
        );
    }
export default ForgotPassword;