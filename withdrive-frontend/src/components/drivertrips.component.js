import * as React from 'react';
import { DataGrid,GridToolbar } from '@mui/x-data-grid';
import { useEffect, useState, useRef } from "react";
import TripService from "../services/TripService";
import moment from 'moment';
import NotFound from '../pages/notfound';
import { IconButton } from '@mui/material';
import Edit from '@material-ui/icons/Edit';
import Applications from '@material-ui/icons/Assignment';
import Preview from '@material-ui/icons/Pageview';



const columns = [
  { field: 'date', headerName: 'Date', width: 200 },
  { field: 'origin', headerName: 'From', width: 150 },
  { field: 'destination', headerName: 'To', width: 150 },
  { field: 'maxPassengers', headerName: 'Capacity', width: 90 },
  {
    field: "Actions",
    width: 145, 
    renderCell: (cellValues) => {
      return (
        <div>
        <IconButton aria-label="view" onClick={()=>{handleClick(0,cellValues)}}>
        <Preview color="primary"/>
        </IconButton>
        <IconButton aria-label="edit" onClick={()=>{handleClick(1,cellValues)}}>
        <Applications color="delete"/>
        </IconButton>
        <IconButton aria-label="edit" onClick={()=>{handleClick(2,cellValues)}}>
          <Edit color="delete"/>
        </IconButton>
        </div>
      );
    }
  },
  ];

  function handleClick(mode, selected){
    switch(mode){
      case 0:
        window.history.pushState({}, '', "/trip/view/"+selected.row.tripID);
        window.location.replace("/trip/view/"+selected.row.tripID);
        break;
      case 1:
        window.history.pushState({}, '', "/trip/apps/"+selected.row.tripID);
        window.location.replace("/trip/apps/"+selected.row.tripID);
        break;
      default:
        window.history.pushState({}, '', "/trip/edit/"+selected.row.tripID);
        window.location.replace("/trip/edit/"+selected.row.tripID);
        break;
    }

  }

export default function DriverTable() {
    const [trips,setTrips] = useState([]);

    useEffect(() => {
        getAvailableTrips()
    }, [])

    const getAvailableTrips = () => {
        TripService.getActiveTripsByDriver().then((response) => {
            setTrips(response.data);
        });
    }   

    trips.map((trip) => {
        trip['id'] = trip.tripID;
        trip.date = moment(trip.date).format('MMM. D, YYYY [at] h:mm A z');
        trip.maxPassengers = "0"+"/"+trip.maxPassengers;
    })

    if(!trips) return <NotFound/>;

  return (
    <div style={{ height: 600, width: 'flex' }}>
      <DataGrid
        density="comfortable"
        rows={trips}
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