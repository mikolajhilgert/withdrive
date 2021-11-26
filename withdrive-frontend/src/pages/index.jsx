import React, { Component } from "react";
import withdriveLogo from "../images/withdrive-whole.png";
import image from "../images/carpooling.jpg";
const Index = () => {
        return (
        <div className="landing">  
            <div className="auth-inner">
                <div>
                    {/* <h3>Who are we?</h3> */}
                    {/* <h4>                                                          </h4> */}
                    <center><img src={withdriveLogo} height="40"></img></center>
                    <br></br><br></br><br></br><br></br>
                    <div className="post-container">                
    <div className="post-thumb"><img src={image} height="500px"/></div>
    <br></br>
        <p className="post-text"> <b>Withdrive</b> is an upcoming car-pooling platform that will be used by drivers and passengers alike.</p>
        <p className="post-text">Carpooling is the act of sharing car journeys- meaning one person can travel somewhere and others can come on as passengers and the price of the trip can be shared and or paid to the driver.</p>
        </div>
    </div>
    <br></br><br></br><br></br><br></br><br></br><br></br></div>
            </div>
        );
    }
export default Index;