import * as React from 'react';
import { DataGrid,GridToolbar } from '@mui/x-data-grid';
import { useEffect, useState, useRef } from "react";
import Button from 'react-bootstrap/Button'
import TripService from "../services/TripService";
import moment from 'moment';
import NotFound from '../pages/notfound';



const columns = [
    { field: 'date', headerName: 'Date', width: 200 },
  { field: 'origin', headerName: 'From', width: 150 },
  { field: 'destination', headerName: 'To', width: 150 },
  { field: 'pricePerPassenger', headerName: 'Price', width: 120 },
  { field: 'maxPassengers', headerName: 'Passengers', width: 120 },
  {
    field: "Apply",
    width: 120, 
    renderCell: (cellValues) => {
      return (
        <Button
          color="primary"
          onClick={(event) => {
            handleClick(cellValues);
          }}
          >
          Apply now!
        </Button>
      );
    }
  },
  ];

  function handleClick(selected){
    window.history.pushState({}, '', "/trip/view/"+selected.row.tripID);
    window.location.replace("/trip/view/"+selected.row.tripID);
}

export default function DataTable() {
    const [trips,setTrips] = useState([]);

    useEffect(() => {
        getAvailableTrips()
    }, [])

    const getAvailableTrips = () => {
        TripService.getTrips().then((response) => {
            setTrips(response.data);
        });
    }   

    trips.map((trip) => {
        trip['id'] = trip.tripID
    })

    trips.map((trip) => {
        trip.date = moment(trip.date).format('MMM. D, YYYY [at] h:mm A z')
    })

    trips.map((trip) => {
        trip.maxPassengers = "0"+"/"+trip.maxPassengers
    })

    if(!trips) return <NotFound/>;

  return (
    <div style={{ height: 650, width: 880 }}>
      <DataGrid
        // components={{
        // Toolbar: GridToolbar,
        // }}        
        disableColumnFilter 
        density="comfortable"
        rows={trips}
        columns={columns}
        pageSize={5}
        rowsPerPageOptions={[8]}
        disableColumnSelector
        disableMultipleSelection={true}
        disableSelectionOnClick={true}
      />
    </div>
  );
}