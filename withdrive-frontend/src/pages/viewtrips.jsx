import React from "react";
import '../css_override/table.css';
import TripTable from "../components/alltrips.component"

var selection = 'All';

const Index = () => {
        return (
        <div className="auth-wrapper">  
            <div className="auth-inner">
                <h1>Today is {new Date().toDateString()}</h1>
            <h4>                                  </h4>
            <h3>{selection} upcoming trips:</h3>
                <TripTable></TripTable>
            </div>
        </div>
        );
    }
export default Index;