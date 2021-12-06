import React, { Fragment } from 'react';
import { DataGrid } from '@mui/x-data-grid';
import { useEffect, useState } from "react";
import moment from 'moment';
import NotFound from '../pages/notfound';
import { IconButton } from '@mui/material';
import Preview from '@material-ui/icons/Pageview';
import Popup from 'react-popup';
import Button from 'react-bootstrap/Button'
import ApplicationService from '../services/ApplicationService';

const columns = [
    { field: 'status', headerName: 'Status', flex: 1, minWidth: 150 },
    { field: 'date', headerName: 'Date', flex: 1, minWidth: 300 },
    { field: 'origin', headerName: 'From', flex: 1, minWidth: 150 },
    { field: 'destination', headerName: 'To', flex: 1, minWidth: 150 },
    {
        field: "Your Application",
        flex: 1, minWidth: 165,
        renderCell: (cellValues) => {
            return (
                <div>
                    <Fragment>
                        <Button variant="primary" onClick={() => { handleClick(1,cellValues); }}>View application</Button>
                    </Fragment>
                </div>
            );
        }
    },
    {
        field: "View trip",
        flex: 1, minWidth: 100,
        renderCell: (cellValues) => {
            return (
                <div>
                    <IconButton aria-label="view" onClick={() => { handleClick(0, cellValues) }}>
                        <Preview color="primary" />
                    </IconButton>
                </div>
            );
        }
    },
];

function handleClick(mode,selected) {
    switch(mode){
        case 0:
            window.history.pushState({}, '', "/trip/view/" + selected.row.id);
            window.location.replace("/trip/view/" + selected.row.id);
            break;
        default:
            Popup.alert(selected.row.text);
            break;
    }
    
}

export default function DriverTable() {
    const [apps, setApps] = useState([]);

    useEffect(() => {
        getAvailableTrips()
    }, [])

    const getAvailableTrips = () => {
        ApplicationService.getActiveAppsByUser().then((response) => {
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
                disableColumnSelector
                disableMultipleSelection={true}
                disableSelectionOnClick={true}
            />
        </div>
    );
}