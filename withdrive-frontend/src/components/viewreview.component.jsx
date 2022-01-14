import React, { useState} from "react";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import ReviewService from "../services/ReviewService";
import Rating from '@mui/material/Rating';


export default function BasicTable(props) {
    const [reviews,setReviews] = useState([]);
    React.useEffect(() => {
        ReviewService.getRatingsByDriver(props.id).then((response) => {
            setReviews(response.data);
        })
    }, [props.id])

  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 550 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Name</TableCell>
            <TableCell>Rating</TableCell>
            <TableCell>Review</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {reviews.map((row) => (
            <TableRow
              key={row.name}
            >
              <TableCell component="th" scope="row">
                {row.name}
              </TableCell>
              <TableCell><Rating name="read-only" value={row.rating} precision={0.5} readOnly/></TableCell>
              <TableCell align="left">{row.text}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
