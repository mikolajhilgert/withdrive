import React from "react";
import form from '../../modules/innerPage.module.css'
import text from '../../modules/text.module.css'
import IsAuthenticated from '../../components/accessCheck.component';

const ViewUsers = () => {
    IsAuthenticated("admin");

        return (
        <div className={form.authwrapper}>  
            <div className={form.authinner_table}>
                <h1 className={text.center}>Welcome administrator!</h1>
                <br></br>
            </div>
        </div>
        );
    }
export default ViewUsers;