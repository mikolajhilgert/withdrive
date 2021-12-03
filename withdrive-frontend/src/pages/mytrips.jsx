import React, { useState } from 'react'
import Switch from '@mui/material/Switch';
import IsAuthenticated from '../components/accessCheck.component';
import BackButton from '../components/backButton.component'
import form from '../modules/innerPage.module.css'
import UserTrips from '../components/usertrips.component'
import UserAllTrips from '../components/userAllTrips.component'
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import Rating from '@mui/material/Rating';
import Button from '@mui/material/Button';


const MyTrips = () => {
    const [select, setSelect] = useState(false);
    const [show, setShow] = useState(false);
    IsAuthenticated();
    const handleToggle = () => {
        setSelect(!select);
    }

    const [open, setOpen] = React.useState(false);
    const [value, setValue] = React.useState(0);

    const handleClose = () => {
        setOpen(false);
    };

    const LeaveRating = () =>{
        setOpen(true);
    }

    function Table() {
        if (select) {
            return <UserAllTrips rating={LeaveRating}/>
        }
        return <UserTrips/>
    }


    return(
        
        <div className={form.authwrapper}>  
            <div className={form.authinner_table}>
            <BackButton />
            <div>
                <h3>Your trips as a passenger:</h3>
                    All trips<Switch onClick={() => handleToggle()} defaultChecked /> Upcoming trips
                    <Table />
                </div>
            </div>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>Leave a review for your driver:</DialogTitle>

                <DialogContent>
                <DialogContentText>
                    This will help other users select safe trips!
                </DialogContentText>
                <Rating
                        name="simple-controlled"
                        precision={0.5}
                        value={value}
                        size="large"
                        onChange={(event, newValue) => {
                            setValue(newValue);
                        }}
                />
                <div>
                <TextField fullWidth
                id="outlined-multiline-flexible"
                label="Write a review.."
                multiline
                rows={4}
                maxRows={10}
                />
                </div>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button onClick={handleClose} onClick={console.log("posted")}>Post</Button>
                </DialogActions>
            </Dialog>
      </div>
    );
}
export default MyTrips;