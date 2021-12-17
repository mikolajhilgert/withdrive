import React from 'react'
import TripApps from '../components/tripapps.component'
import BackButton from '../components/backButton.component'
import form from '../modules/innerPage.module.css'
import AuthService from '../services/AuthService';



const TripApplications = () => {
    AuthService.checkToken();

    return(
        <div className={form.authwrapper}>  
            <div className={form.authinner_table}>
                <BackButton />
                <div>
                <h3>Applications for your trip!</h3>        
                <TripApps/>
                </div>
                <br></br>
            </div>
        </div>
    );
}
export default TripApplications;