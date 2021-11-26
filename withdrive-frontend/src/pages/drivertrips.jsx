import DriverTrips from '../components/drivertrips.component'
import Button from 'react-bootstrap/Button'
import BackButton from '../components/backButton.component'
const MyTrips = () => {
    return(
        <div className="auth-wrapper">  
            <div className="auth-inner">
                <BackButton />
                <div>
                <h4>                               </h4>
                <h3>Your upcoming trips as a driver:</h3>        
                <DriverTrips/>
                </div>
                <br></br>
                <Button>View completed trips</Button>
            </div>
      </div>
    );
}
export default MyTrips;