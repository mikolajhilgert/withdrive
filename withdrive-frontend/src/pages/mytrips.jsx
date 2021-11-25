import DriverTrips from '../components/drivertrips.component'
import { useHistory } from 'react-router';
import Button from 'react-bootstrap/Button'
const MyTrips = () => {
    return(
        <div className="auth-wrapper">  
            <div className="auth-inner">
                <div>
                    <h1>
                <Button size="lg" onClick={() => {
                    window.history.pushState({}, '', "driver-trips");
                    window.location.replace("driver-trips");}}>
                    Driver view
                </Button></h1>
                <h4>                               </h4>
                <h3>Your trips as a passenger:</h3>       
                </div>
            </div>
      </div>
    );
}
export default MyTrips;