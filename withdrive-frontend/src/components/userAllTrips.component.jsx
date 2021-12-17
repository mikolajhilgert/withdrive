import React from 'react';
import { DataGrid} from '@mui/x-data-grid';
import { useEffect, useState} from "react";
import moment from 'moment';
import NotFound from '../pages/notfound';
import { IconButton } from '@mui/material';
import Preview from '@material-ui/icons/Pageview';
import Star from '@material-ui/icons/Star';
import ApplicationService from '../services/ApplicationService';
import ReviewService from '../services/ReviewService'
import { store } from 'react-notifications-component';



export default function UserTripTable(props) {

    const [apps, setApps] = useState([]);

    useEffect(() => {
        getAvailableTrips()
    }, [])


    const getAvailableTrips = () => {
        ApplicationService.getAllAppsByUser().then((response) => {
            setApps(response.data);
        });
    }

    function handleClick(mode, selected) {
        switch(mode){
            case 0:
                var id = selected.row.id;
                window.history.pushState({}, '', "/trip/view/" + id);
                window.location.replace("/trip/view/" + id);
                break;

            default:
                ReviewService.hasUserLeftReview(selected.id).then((response)=>{
                    if(response.data === false){
                        props.rating(selected.id);
                    }else{
                        store.addNotification({
                            title: "Alert",
                            message: "You have already left a rating for this trip!",
                            type: "warning",
                            insert: "top",
                            container: "top-center",
                            dismiss: {
                                duration: 5000,
                                onScreen: true
                            }
                        });
                    }
                })

                break;
        }
    }

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
                        <IconButton aria-label="view" onClick={() => { handleClick(0,cellValues) }}>
                            <Preview color="primary" />
                        </IconButton>
                        <IconButton aria-label="view" onClick={() => { handleClick(1,cellValues) }}>
                            <Star color="primary" />
                        </IconButton>
                    </div>
                );
            }
        },
    ];



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