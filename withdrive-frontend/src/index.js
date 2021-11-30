import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter } from "react-router-dom";
import "./index.css";
import App from "./App";
// import Dialog from '@mui/material/Dialog';
import Popup from 'react-popup';


ReactDOM.render(
    <BrowserRouter>
        <App/>
        <Popup />
    </BrowserRouter>,
    document.getElementById("root")
);

