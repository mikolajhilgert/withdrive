import React from "react";
import '../css_override/table.css';
import TripTable from "../components/viewtrips.component"

const Index = () => {
        return (
        <div className="auth-wrapper">  
            <div className="auth-inner">
                <TripTable></TripTable>
            </div>
        </div>
        );
    }
export default Index;