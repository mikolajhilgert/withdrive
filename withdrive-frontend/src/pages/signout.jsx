import React from 'react'
import { useHistory } from 'react-router';
import AuthService from '../services/AuthService'

const LogOut = () => {
    const History = useHistory();
    alert("You will now be logged out!");
    AuthService.logout();
    History.push("/");
    window.location.reload();
    return(
        <div>Redirecting you to the index page..</div>
    );

}
export default LogOut;