import React, { useEffect, useState } from "react";
import UserService from "../services/UserService";

function UserList(){
    const [userItems,setStations] = useState([]);

    useEffect(() => {
        getUsers()
    }, [])

    const getUsers = () => {
        UserService.getUsers().then((response) => {
            setStations(response.data);
            console.log(response.data)
        });
    }

    return(
    <div>
        <h3>API example:</h3>
        {userItems.map(
            user=> 
            <div key={user.clientNumber}>
            <div>Client UUID:⠀{user.userID}</div>
            <div>Email:⠀{user.email}</div>
            <br></br>
            </div>
        )}
    </div>
    );
}

export default UserList