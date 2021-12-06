import React,{Fragment} from 'react';
import { DataGrid } from '@mui/x-data-grid';
import { useEffect, useState } from "react";
import moment from 'moment';
import NotFound from '../pages/notfound';
import ApplicationService from '../services/ApplicationService';
import Button from 'react-bootstrap/Button'
import Popup from 'react-popup';
import SkyLight from 'react-skylight';

let id = window.location.pathname.split('/').pop();

const columns = [
  { field: 'fullName', headerName: 'Name', flex:1 },
  { field: 'age', headerName: 'Age', width:"100" },
  { field: 'gender', headerName: 'Gender', width:"100" },
  { field: 'status', headerName: 'Status', width:"100" },
  { field: 'message', headerName: 'Application',width:"200",
  renderCell: (cellValues) => {
    return (
      <Fragment>
        <Button variant="primary" onClick={() => {handleClick(2,cellValues);}}>View application</Button>
      </Fragment>
    );
  }},
  {
    field: "Actions",
    flex:1,
    renderCell: (cellValues) => {
      if(cellValues.row.trip.passengers.length<cellValues.row.trip.maxPassengers){
        if(cellValues.row.status === "PENDING"){
          return (
            <Fragment>
              <Button variant="success" onClick={() => {handleClick(0,cellValues);}}>Accept</Button>
              ㅤ
              <Button variant="danger" onClick={() => {handleClick(1,cellValues);}}>Reject</Button>
            </Fragment>
          );
        }else if(cellValues.row.status === "ACCEPTED"){
          return(<Fragment>
            Contact:ㅤ
            <Button variant="info" onClick={() => {Popup.alert(cellValues.row.user.email)}}>Email</Button>
            ㅤ
            <Button variant="info" onClick={() => {Popup.alert(cellValues.row.user.phoneNumber)}}>Phone</Button>
          </Fragment>)
        }else{
          return(<div></div>)
        }
      }else{
        return(<div>full</div>)
      }
    }
  },
  ];

  function handleClick(mode, selected){
    switch(mode){
      case 0:
        console.log(selected.row.user.userID);
        ApplicationService.acceptApp({trip:id,user:selected.row.user.userID})
        window.location.reload();
        break;
      case 1:
        ApplicationService.rejectApp({trip:id,user:selected.row.user.userID,})
        window.location.reload();
        break;
        
      default:
        // alert(selected.row.text);
        // <SkyLight hideOnOverlayClicked ref={ref => this.simpleDialog = ref} title="Hi, I'm a simple modal">
        // {selected.row.text}
        // </SkyLight>
        Popup.alert(selected.row.text);
        // <Dialog text={selected.row.text}/>
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
      app['status']=app.status
      app['message']=app.text  
    })

    if(!apps) return <NotFound/>;
  return (
    // <div></div>
    <center><div style={{ height: 600, flex:1 }}>
      <DataGrid
        rows={apps}
        columns={columns}
        pageSize={5}
        disableColumnSelector
        disableMultipleSelection={true}
        disableSelectionOnClick={true}
      />
    </div></center>
  );
}