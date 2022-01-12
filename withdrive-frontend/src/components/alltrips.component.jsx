import * as React from 'react';
import { DataGrid } from '@mui/x-data-grid';
import { useEffect, useState } from "react";
import Button from 'react-bootstrap/Button'
import TripService from "../services/TripService";
import moment from 'moment';
import NotFound from '../pages/notfound';
import Select from './citySelect.component'



const columns = [
  { field: 'date', headerName: 'Date', minWidth: "320"},
  { field: 'origin', headerName: 'From',minWidth: "200" },
  { field: 'destination', headerName: 'To', minWidth: "200" },
  { field: 'pricePerPassenger', headerName: 'Price', minWidth: "200" },
  { field: 'PassengerCount', headerName: 'Available seats', minWidth: "200"},
  { field: "Apply",flex: 1,minWidth: "210",renderCell: (cellValues) => {return (<Button color="primary" onClick={() => { handleClick(cellValues); }} >View details</Button>);}},
];

function handleClick(selected) {
  window.history.pushState({}, '', "/trip/view/" + selected.row.tripID);
  window.location.replace("/trip/view/" + selected.row.tripID);
}

export default function DataTable() {
  const [trips, setTrips] = useState([]);
  const [page, setPage] = useState(0);
  const [count, setCount] = useState(100);

  useEffect(() => {
    getAvailableTrips(page);
    getAvailableTripCount();
  }, [page])

  const getAvailableTrips = async (page) => {
    await TripService.getTrips(page).then((response) => {
      setTrips(response.data);
    });
  }

  const getAvailableTripCount = async () => {
    await TripService.getTripsCount().then((response) => {
      setCount(response.data);
    });
  }

  const setSearch = (data) => {
    if(data !== ""){
      TripService.getTripsByOrigin(data).then((response) => {
        setTrips(response.data);
      });
    }else{
      getAvailableTrips(0);
    }
  }

  trips.map((trip) => {
    trip['id'] = trip.tripID
    trip.date = moment(trip.date).format('LLLL')
    trip.PassengerCount = trip.maxPassengers-trip.passengers.length
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
          pagination
          disableColumnFilter
          density="comfortable"
          rows={trips}
          columns={columns}
          pageSize={5}
          rowCount={count}
          disableColumnSelector
          disableMultipleSelection={true}
          disableSelectionOnClick={true}
          paginationMode="server"
          onPageChange={(newPage) => {setPage(newPage)}}
        />
      </div>
    </div>
  );
}