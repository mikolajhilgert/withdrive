import React, {Fragment} from 'react';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import { useEffect, useState, useRef } from "react";
import TripService from "../services/TripService";
import moment from 'moment';
import NotFound from '../pages/notfound';
import { IconButton } from '@mui/material';
import Edit from '@material-ui/icons/Edit';
import Applications from '@material-ui/icons/Assignment';
import Preview from '@material-ui/icons/Pageview';
import Star from '@material-ui/icons/Star';
import Popup from 'react-popup';
import '../data/popup.css'
import Button from 'react-bootstrap/Button'
import ApplicationService from '../services/ApplicationService';



const columns = [
    { field: 'date', headerName: 'Date', flex: 1, minWidth: 300 },
    { field: 'origin', headerName: 'From', flex: 1, minWidth: 150 },
    { field: 'destination', headerName: 'To', flex: 1, minWidth: 150 },
    {
        field: "Action",
        flex: 1, minWidth: 220,
        renderCell: (cellValues) => {
            return (
                <div>
                    <IconButton aria-label="view" onClick={() => { handleClick(0, cellValues) }}>
                        <Preview color="primary" />
                    </IconButton>
                    <IconButton aria-label="view" onClick={() => { handleClick(0, cellValues) }}>
                        <Star color="primary" />
                    </IconButton>
                </div>
            );
        }
    },
];

function handleClick(selected) {
    Popup.alert(selected.row.text);
}

export default function DriverTable() {
    const [apps, setApps] = useState([]);

    useEffect(() => {
        getAvailableTrips()
    }, [])

    const getAvailableTrips = () => {
        ApplicationService.getAllAppsByUser().then((response) => {
            setApps(response.data);
        });
    }

    apps.map((app) => {
        app['id'] = app.trip.tripID;
        app.date = moment(app.trip.date).format('LLLL');
        app.origin = app.trip.origin
        app.destination = app.trip.destination
    })

    if (!apps) return <NotFound />;

    return (
        <div style={{ height: 600, width: 'flex' }}>
            <DataGrid
                density="comfortable"
                rows={apps}
                columns={columns}
                pageSize={5}
                rowsPerPageOptions={[5]}
                disableColumnSelector
                disableMultipleSelection={true}
                disableSelectionOnClick={true}
            />
        </div>
    );
}