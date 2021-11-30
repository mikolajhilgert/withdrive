import React,{useState} from 'react'
import TripApps from '../components/tripapps.component'
import Button from 'react-bootstrap/Button'
import BackButton from '../components/backButton.component'
import form from '../modules/innerPage.module.css'
import IsAuthenticated from '../components/accessCheck.component';
import Dialog from '../components/dialogApplication.component'



const TripApplications = () => {


IsAuthenticated();

    return(
        <div className={form.authwrapper}>  
            <div className={form.authinner_table}>
                <BackButton />
                <div>
                <h3>Applications for your trip!</h3>        
                <TripApps/>
                </div>
                <br></br>
                {/* /<Dialog text={"xddd"}/> */}
            </div>
        </div>
    );
}
export default TripApplications;