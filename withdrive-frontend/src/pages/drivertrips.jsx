import React, { useState } from 'react'
import Switch from '@mui/material/Switch';
import DriverTrips from '../components/drivertrips.component'
import DriverAllTrips from '../components/driveralltrips.component'
import Button from 'react-bootstrap/Button'
import BackButton from '../components/backButton.component'
import form from '../modules/innerPage.module.css'
import IsAuthenticated from '../components/accessCheck.component';

const MyTrips = () => {
    const [select, setSelect] = useState(false);
    IsAuthenticated();
    const handleToggle = () => {
        setSelect(!select);
    }

    function Table() {
        if (select) {
            return <DriverAllTrips />;
        }
        return <DriverTrips />
    }

    return (
        <div className={form.authwrapper}>
            <div className={form.authinner_table}>
                <BackButton />
                <div>
                    <h3>Upcoming driver trips:</h3>
                    {/* All trips<Switch onClick={() => handleToggle()} defaultChecked /> Upcoming trips */}
                    <DriverTrips />
                    <br></br><br></br>
                    <h3>Past driver trips:</h3>
                    <DriverAllTrips />
                    {/* <Table /> */}
                </div>
                <br></br>
            </div>
        </div>
    );
}
export default MyTrips;