import React, { useEffect, useState } from "react";
import TripService from "../services/TripService";
import '../css_override/table.css';

function UserList(){
    const [trips,setTrips] = useState([]);

    useEffect(() => {
        getAvailableTrips()
    }, [])

    const getAvailableTrips = () => {
        TripService.getTrips().then((response) => {
            setTrips(response.data);
        });
    }    

    function buildTable(){
        var table = document.getElementById('content');

        for(var i = 0; i < trips.length; i++){
            var row = `
                <tr>
                <td data-label="Origin">${trips[i].origin}</td>
                <td data-label="Destination">${trips[i].destination}</td>
                <td data-label="Date">${trips[i].date}</td>
                <td data-label="Price">€${trips[i].pricePerPassenger} /pp</td>
                <td data-label="Staus"><a href="trip/view/${trips[i].tripID}"><div><Button size="sm" >View More</Button></div></a></td>
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
                        <th>Start</th>
                        <th>Destination</th>
                        <th>Date</th>
                        <th>Price</th>
                        <th>Apply!</th>
                        </tr>
                    </thead>
                    <tbody id="content">
                    </tbody>
                </table>
            </div>
    );
}

export default UserList