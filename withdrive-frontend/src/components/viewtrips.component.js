import React, { useEffect, useState } from "react";
import TripService from "../services/TripService";
import '../css_override/table.css';
import Button from 'react-bootstrap/Button'

function UserList(){
    const [trips,setTrips] = useState([]);

    useEffect(() => {
        getAvailableTrips()
    }, [])

    const getAvailableTrips = () => {
        TripService.getTrips().then((response) => {
            setTrips(response.data);
            console.log(response.data)
        });
    }    



    function buildTable(){
        var table = document.getElementById('content');
        getAvailableTrips();
        for(var i = 0; i < trips.length; i++){
            var row = `
                <tr>
                <td data-label="Origin">${trips[i].origin}</td>
                <td data-label="Destination">${trips[i].destination}</td>
                <td data-label="Date">${trips[i].description}</td>
                <td data-label="Staus"><div><Button size="sm">View More</Button></div></td>
                </tr>
            `
            table.innerHTML += row;
        }
    }

    buildTable();

    return(
            <div>
                <div>
                <h2>Upcoming trips:</h2>
                <br></br>
                </div>
                <table className="table">
                    <thead>
                        <tr>
                        <th>Origin</th>
                        <th>Destination</th>
                        <th>Date</th>
                        <th>Status</th>
                        </tr>
                    </thead>
                    <tbody id="content">
                    </tbody>
                </table>
            </div>
    );
}

export default UserList