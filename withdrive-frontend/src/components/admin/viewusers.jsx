import * as React from 'react';
import { DataGrid,GridToolbar} from '@mui/x-data-grid';
import { useEffect, useState } from "react";
import UserService from '../../services/UserService'
import NotFound from '../../pages/notfound';
import { IconButton } from '@mui/material';
import DeleteIcon from '@material-ui/icons/Delete';
import EmailIcon from '@material-ui/icons/Email';


const columns = [
  { field: 'userID', headerName: 'ID', flex: 1 , minWidth: 300},
  { field: 'email', headerName: 'Email', flex: 1 , minWidth: 300},
  { field: 'name', headerName: 'Full Name', flex: 1 , minWidth: 150},
  { field: 'dateOfBirth', headerName: 'Date of birth', flex: 1 , minWidth: 150},
  { field: 'gender', headerName: 'Gender', flex: 1 , minWidth: 150},
  {
    field: "Actions",
    flex: 1 , minWidth: 250,
    renderCell: (cellValues) => {
      return (
        <div>
          <IconButton aria-label="view" onClick={() => { handleClick(0, cellValues) }}>
            <DeleteIcon color="error" />
          </IconButton>
          <IconButton aria-label="view" onClick={() => { handleClick(1, cellValues) }}>
            <EmailIcon color="primary" />
          </IconButton>
        </div>
      );
    }
  },
];

function handleClick(mode, selected) {
  switch (mode) {
    case 0:
      var r = window.confirm("Are you sure you want to delete this user?");
      if (r === true) {
        UserService.deleteUser(selected.row.userID);
        window.location.reload();
      }
      break;
    case 1:
      window.location.href = "mailto:"+selected.row.email;
      break;
  }

}

export default function DriverTable() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    getUsers()
  }, [])

  const getUsers = () => {
    UserService.getUsers().then((response) => {
      var users = [];
      (response.data).forEach(function(element) {
        if (element.email !== "admin@withdrive.com") {
          users.push(element);
        }
      });
      setUsers(users);
    });
  }
  users.map((u) => {
    u.name = u.firstName+" "+u.lastName;
  })

  if (!users) return <NotFound />;

  return (
    <div style={{ height: 600, width: 'flex' }}>
      <DataGrid
        getRowId={row => row.userID}
        density="comfortable"
        rows={users}
        hideToolbar={false}
        columns={columns}
        pageSize={5}
        disableColumnSelector
        disableMultipleSelection={true}
        disableSelectionOnClick={true}
        components={{
          Toolbar: GridToolbar,
        }}
      />
    </div>
  );
}