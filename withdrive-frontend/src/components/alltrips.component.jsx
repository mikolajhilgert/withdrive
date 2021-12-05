import * as React from 'react';
import { DataGrid } from '@mui/x-data-grid';
import { useEffect, useState } from "react";
import Button from 'react-bootstrap/Button'
import TripService from "../services/TripService";
import moment from 'moment';
import NotFound from '../pages/notfound';
import Select from './citySelect.component'



const columns = [
  { field: 'date', headerName: 'Date', width: "320"},
  { field: 'origin', headerName: 'From',flex: 1 },
  { field: 'destination', headerName: 'To', flex: 1 },
  { field: 'pricePerPassenger', headerName: 'Price', flex: 1 },
  { field: 'maxPassengers', headerName: 'Available seats', flex: 1},
  { field: "Apply",flex: 1,renderCell: (cellValues) => {return (<Button color="primary" onClick={() => { handleClick(cellValues); }} >View details</Button>);}},
];

function handleClick(selected) {
  window.history.pushState({}, '', "/trip/view/" + selected.row.tripID);
  window.location.replace("/trip/view/" + selected.row.tripID);
}

export default function DataTable() {
  const [trips, setTrips] = useState([]);
  useEffect(() => {
    getAvailableTrips()
  }, [])

  const getAvailableTrips = () => {
    TripService.getTrips().then((response) => {
      setTrips(response.data);
    });
  }

  const setSearch = (data) => {
    if(data !== ""){
      TripService.getTripsByOrigin(data).then((response) => {
        setTrips(response.data);
      });
    }else{
      TripService.getTrips().then((response) => {
        setTrips(response.data);
      });
    }
  }

  trips.map((trip) => {
    trip['id'] = trip.tripID
    trip.date = moment(trip.date).format('LLLL')
    trip.maxPassengers = trip.maxPassengers-trip.passengers.length + "/" + trip.maxPassengers
  })

  if (!trips) return <NotFound />;

  return (
    <div>
      <br></br>
      <center>
      <Select setCity={setSearch} clear={true} text="Select to see trips originating from a specific city" />
      </center>
      <br></br>
      <div style={{ height: 600, width: 'flex' }}>
        <DataGrid
          disableColumnFilter
          density="comfortable"
          rows={trips}
          columns={columns}
          pageSize={5}
          disableColumnSelector
          disableMultipleSelection={true}
          disableSelectionOnClick={true}
        />
      </div>
    </div>
  );
}