import DriverTrips from '../components/drivertrips.component'
import { useHistory } from 'react-router';
import Button from 'react-bootstrap/Button'
import IsAuthenticated from '../components/accessCheck.component';

import form from '../modules/innerPage.module.css'

const MyTrips = () => {
    
IsAuthenticated();

    return(
        <div className={form.authwrapper}>  
            <div className={form.authinner_table}>
                <div>
                    <h1>
                <Button size="lg" onClick={() => {
                    window.history.pushState({}, '', "driver-trips");
                    window.location.replace("driver-trips");}}>
                    Driver view 🚕
                </Button></h1>
                <h4>                               </h4>
                <h3>Your upcoming trips as a passenger:</h3>       
                </div>
            </div>
      </div>
    );
}
export default MyTrips;