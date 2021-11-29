import React,{Fragment} from 'react';
import { DataGrid,GridToolbar } from '@mui/x-data-grid';
import { useEffect, useState } from "react";
import TripService from "../services/TripService";
import moment from 'moment';
import NotFound from '../pages/notfound';
import { IconButton } from '@mui/material';
import Edit from '@material-ui/icons/Edit';
import Applications from '@material-ui/icons/Assignment';
import Preview from '@material-ui/icons/Pageview';
import ApplicationService from '../services/ApplicationService';
import Button from 'react-bootstrap/Button'

let id = window.location.pathname.split('/').pop();

const columns = [
  { field: 'fullName', headerName: 'Name', width:"100" },
  { field: 'age', headerName: 'Age', width:"100" },
  { field: 'gender', headerName: 'Gender', width:"100" },
  { field: 'message', headerName: 'Application',flex:1 },
  {
    field: "Actions",
    width:"200",
    renderCell: (cellValues) => {
      return (
        <Fragment>
          <Button variant="success" onClick={() => {handleClick(0,cellValues);}}>Accept</Button>
          ㅤ
          <Button variant="danger" onClick={() => {handleClick(1,cellValues);}}>Reject</Button>
        </Fragment>
      );
    }
  },
  ];

  function handleClick(mode, selected){
    switch(mode){
      case 0:
        console.log(selected.row.user.userID);
        ApplicationService.acceptApp({trip:id,user:selected.row.user.userID})
        break;
      default:
        ApplicationService.rejectApp({trip:id,user:selected.row.user.userID,})
        break;
    }

  }

export default function AppTable() {
    const [apps,setApps] = useState([]);

    useEffect(() => {
        getApplications()
    }, [])

    const getApplications = () => {
        ApplicationService.getApps(id).then((response) => {
            setApps(response.data);
        });
    }   
    
    apps.map((app) => {
      app['id'] = app.user.userID;
      app['fullName'] = app.user.firstName +" "+app.user.lastName
      app['age']=moment().diff(app.user.dateOfBirth, 'years',false) + " Years old"
      app['gender']=app.user.gender
      app['message']=app.text  
    })

    if(!apps) return <NotFound/>;
  return (
    // <div></div>
    <div style={{ height: 600, width: 'flex' }}>
      <DataGrid
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