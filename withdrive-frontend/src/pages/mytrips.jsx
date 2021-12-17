import React, { useState } from 'react'
import Switch from '@mui/material/Switch';

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
import ReviewService from '../services/ReviewService'
import AuthService from '../services/AuthService';

const MyTrips = () => {
    AuthService.checkToken();
    const [select, setSelect] = useState(false);
    const [show, setShow] = useState(false);
    const [trip, setTrip] = React.useState("");
    const text = React.useRef();
    const [open, setOpen] = React.useState(false);
    const [value, setValue] = React.useState(0);



    
    const handleToggle = () => {
        setSelect(!select);
    }

    function post(){
        const review = {
            trip: trip,
            rating: value,
            text: text.current.value,
            type: "PASSENGER",
        };
        ReviewService.postRating(review)
        setOpen(false);
    }

    const handleClose = () => {
        setOpen(false);
    };

    const LeaveRating = (data) => {
        setTrip(data);
        setOpen(true);
    }

    function Table() {
        if (select) {
            return <UserAllTrips rating={LeaveRating} />
        }
        return <UserTrips />
    }

    const DialogComponent = (
        <Dialog open={open} onClose={handleClose}>
            <DialogTitle>Leave a review for your driver:</DialogTitle>

            <DialogContent>
                <DialogContentText>
                    This will help other users select safe trips!
                </DialogContentText>
                <Rating
                    name="simple-controlled"
                    defaultValue={1}
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
                        inputRef={text}
                    />
                </div>
            </DialogContent>
            <DialogActions>
                <Button onClick={handleClose}>Cancel</Button>
                <Button onClick={post}>Post</Button></DialogActions>
        </Dialog>
    );

    return (

        <div className={form.authwrapper}>
            <div className={form.authinner_table}>
                <BackButton />
                <div>
                    <h3>Your trips as a passenger:</h3>
                    All trips<Switch onClick={() => handleToggle()} defaultChecked /> Upcoming trips
                    <Table />
                    {DialogComponent}
                </div>
            </div>

        </div>
    );
}
export default MyTrips;