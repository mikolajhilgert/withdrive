import * as React from 'react';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import { useEffect, useState, useRef } from "react";
import TripService from "../services/TripService";
import moment from 'moment';
import NotFound from '../pages/notfound';
import { IconButton } from '@mui/material';
import Edit from '@material-ui/icons/Edit';
import Applications from '@material-ui/icons/Assignment';
import Preview from '@material-ui/icons/Pageview';
import Popup from 'react-popup';
import '../data/popup.css'



const columns = [
  { field: 'date', headerName: 'Date', flex: 1 , minWidth: 300},
  { field: 'origin', headerName: 'From', flex: 1 , minWidth: 150},
  { field: 'destination', headerName: 'To', flex: 1 , minWidth: 150},
  { field: 'maxPassengers', headerName: 'Capacity', flex: 1 , minWidth: 150},
  {
    field: "Actions",
    flex: 1 , minWidth: 220,
    renderCell: (cellValues) => {
      return (
        <div>
          <IconButton aria-label="view" onClick={() => { handleClick(0, cellValues) }}>
            <Preview color="primary" />
          </IconButton>
          <IconButton aria-label="edit" onClick={() => { handleClick(1, cellValues) }}>
            <Applications />
          </IconButton>
          <IconButton aria-label="edit" onClick={() => { handleClick(2, cellValues) }}>
            <Edit />
          </IconButton>
        </div>
      );
    }
  },
];

function handleClick(mode, selected) {
  switch (mode) {
    case 0:
      window.history.pushState({}, '', "/trip/view/" + selected.row.tripID);
      window.location.replace("/trip/view/" + selected.row.tripID);
      break;
    case 1:
      window.history.pushState({}, '', "/trip/apps/" + selected.row.tripID);
      window.location.replace("/trip/apps/" + selected.row.tripID);
      break;
    default:
      window.history.pushState({}, '', "/trip/edit/" + selected.row.tripID);
      window.location.replace("/trip/edit/" + selected.row.tripID);
      break;
  }

}

export default function DriverTable() {
  const [trips, setTrips] = useState([]);

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
    trip.date = moment(trip.date).format('LLLL');
    trip.maxPassengers = trip.passengers.length + "/" + trip.maxPassengers;
  })

  if (!trips) return <NotFound />;

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
        onCellDoubleClick={(params) => {
          Popup.alert(params.value)
        }}
      />
    </div>
  );
}