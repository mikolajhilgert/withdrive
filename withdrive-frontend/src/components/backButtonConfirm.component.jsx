import { useHistory } from "react-router-dom";
import { IconButton } from '@mui/material';
import * as React from 'react';
import Button from '@mui/material/Button';
import ArrowBackIos from '@material-ui/icons/ArrowBackIos';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';

const Item = () => {
    let history = useHistory();
    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
      setOpen(true);
    };
  
    const handleClose = () => {
      setOpen(false);
    };

    return (
        <>
        <IconButton aria-label="delete" onClick={handleClickOpen}>
            <ArrowBackIos/>
        </IconButton>
        <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
          {"Potential unsaved changes!"}
        </DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            Are you sure you want to go back? Any unsaved changes will not be stored!
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button onClick={() => history.goBack()} autoFocus>
            Ok
          </Button>
        </DialogActions>
      </Dialog>
        </>
    );
};
export default Item;