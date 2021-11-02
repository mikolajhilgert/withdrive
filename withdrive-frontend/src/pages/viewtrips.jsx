import React, {  } from "react";
import Button from 'react-bootstrap/Button'
//import Users from "../components/user.component";
import '../css_override/table.css';
const Index = () => {
        return (
            <div>
                <div>
                <h2>Upcoming trips:</h2>
                <br></br>
                </div>
                <table class="table">
                    <thead>
                        <tr>
                        <th>Origin</th>
                        <th>Destination</th>
                        <th>Date</th>
                        <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td data-label="Origin">Eindhoven</td>
                            <td data-label="Destination">Amsterdam</td>
                            <td data-label="Date">01/11/2021</td>
                            <td data-label="Staus"><Button size="sm">View More</Button></td>
                        </tr>
                    </tbody>
                </table>
            </div>

        );
    }
export default Index;