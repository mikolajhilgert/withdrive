import React, {useEffect} from "react";
import { useHistory } from "react-router";
import UserService from "../services/AuthService";
import LockOutlinedIcon from '@material-ui/icons/LockOpen';
import BackButton from '../components/backButton.component'

import form from '../modules/innerPage.module.css'
import text from '../modules/text.module.css'

const ForgotPassword = () => {
    
        const [msg, setMsg] = React.useState(null);
        const History = useHistory();
        const email = React.useRef();
        const handleSubmit = e =>{
            e.preventDefault();
            UserService.recoverPassword(email.current.value);
            email.current.value="";
            setMsg("Password restoration link has been sent to your email!");
        }

        return (
        <div className={form.authwrapper}>  
            <div className={form.authinner_form}>
            <form onSubmit={handleSubmit}>
                <BackButton/>
                <center><LockOutlinedIcon/></center>
                <h2><center>Forgotten password</center></h2>
                <br></br>
                <div className="form-group">
                    <label>Recovery email:</label>
                    <input type="email" className="form-control" placeholder="Enter email" ref={email} required/>
                </div>
                
                <br></br>
                <center><div className="form-group"><button type="submit" className="btn btn-primary btn-block">Send recovery email</button></div></center>
                <br></br>
                
                <h3>{msg}</h3>
            </form>
            </div>
        </div>
        );
    }
export default ForgotPassword;