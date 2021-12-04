import * as React from 'react';
import { DataGrid} from '@mui/x-data-grid';
import { useEffect, useState } from "react";
import TripService from "../services/TripService";
import moment from 'moment';
import NotFound from '../pages/notfound';
import { IconButton } from '@mui/material';
import Preview from '@material-ui/icons/Pageview';



const columns = [
  { field: 'date', headerName: 'Date', flex: 1 , minWidth: 300},
  { field: 'origin', headerName: 'From', flex: 1 , minWidth: 150},
  { field: 'destination', headerName: 'To', flex: 1 , minWidth: 150},
  { field: 'maxPassengers', headerName: 'Capacity', flex: 1 , minWidth: 150},
  {
    field: "Actions",
    width: "100",
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

function handleClick(mode, selected) {
  switch (mode) {
    default:
      window.history.pushState({}, '', "/trip/view/" + selected.row.tripID);
      window.location.replace("/trip/view/" + selected.row.tripID);
      break;
  }

}

export default function DriverTable() {
  const [trips, setTrips] = useState([]);

  useEffect(() => {
    getAvailableTrips()
  }, [])

  const getAvailableTrips = () => {
    TripService.getAllTripsByDriver().then((response) => {
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
        disableColumnSelector
        disableMultipleSelection={true}
        disableSelectionOnClick={true}
      />
    </div>
  );
}