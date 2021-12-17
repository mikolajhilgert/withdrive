import React from "react";
import withdriveLogo from "../images/withdrive-whole.png";
import image from "../images/carpooling.jpg";
import styles from '../modules/front.module.css'

const Index = () => {
    return (
        <div className={styles.authwrapper}>
            <div className={styles.authinner}>
                <div>
                    <center><img src={withdriveLogo} height="40"></img></center>
                    <br></br><br></br><br></br><br></br>
                    <div className={styles.postcontainer}>
                        <div className={styles.postthumb}><img src={image} alt-text=""/></div>
                        <p className={styles.posttext}> <b>Withdrive</b> is an upcoming car-pooling platform that will be used by drivers and passengers alike.</p>
                        <p className={styles.posttext}>Carpooling is the act of sharing car journeys- meaning one person can travel somewhere and others can come on as passengers and the price of the trip can be shared and or paid to the driver.</p>
                    </div>
                </div>
            </div>
        </div>
    );
}
export default Index;