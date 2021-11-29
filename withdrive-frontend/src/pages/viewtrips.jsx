import React from "react";
import TripTable from "../components/alltrips.component"
import form from '../modules/innerPage.module.css'
import text from '../modules/text.module.css'
import moment from 'moment'

const Index = () => {
        return (
        <div className={form.authwrapper}>  
            <div className={form.authinner_table}>
                <h1 className={text.center}>Today is {moment(new Date()).format("LLLL")}</h1>
            {/* <h3>{selection} upcoming trips:</h3> */}
            <TripTable/>
            </div>
        </div>
        );
    }
export default Index;