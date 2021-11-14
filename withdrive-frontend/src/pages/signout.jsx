import React from 'react'
import { Redirect, useHistory } from 'react-router';
import AuthService from '../services/AuthService'



const LogOut = () => {
    const History = useHistory();
    AuthService.logout();
    History.push("/");
    return(
        <div></div>
    );

}
export default LogOut;