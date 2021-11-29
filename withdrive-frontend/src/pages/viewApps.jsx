import TripApps from '../components/tripapps.component'
import Button from 'react-bootstrap/Button'
import BackButton from '../components/backButton.component'
import form from '../modules/innerPage.module.css'
import IsAuthenticated from '../components/accessCheck.component';



const TripApplications = () => {
    
IsAuthenticated();

    return(
        <div className={form.authwrapper}>  
            <div className={form.authinner_table}>
                <BackButton />
                <div>
                <h3>Your upcoming trips as a driver:</h3>        
                <TripApps/>
                </div>
                <br></br>
                <Button>View completed trips</Button>
            </div>
        </div>
    );
}
export default TripApplications;