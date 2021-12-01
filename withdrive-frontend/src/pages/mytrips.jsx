import React, { useState } from 'react'
import Switch from '@mui/material/Switch';
import IsAuthenticated from '../components/accessCheck.component';
import BackButton from '../components/backButton.component'
import form from '../modules/innerPage.module.css'
import UserTrips from '../components/usertrips.component'
import UserAllTrips from '../components/userAllTrips.component'



const MyTrips = () => {
    const [select, setSelect] = useState(false);
    IsAuthenticated();
    const handleToggle = () => {
        setSelect(!select);
    }

    function Table() {
        if (select) {
            return <UserAllTrips/>
        }
        return <UserTrips/>
    }

    return(
        <div className={form.authwrapper}>  
            <div className={form.authinner_table}>
            <BackButton />
            <div>
                <h3>Your trips as a passenger:</h3>
                    All trips<Switch onClick={() => handleToggle()} defaultChecked /> Upcoming trips
                    <Table />
                </div>
            </div>
      </div>
    );
}
export default MyTrips;