import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter } from "react-router-dom";
import "./index.css";
import App from "./App";
// import Dialog from '@mui/material/Dialog';
import Popup from 'react-popup';
import ReactNotification from 'react-notifications-component'
import 'react-notifications-component/dist/theme.css'


ReactDOM.render(
    <BrowserRouter>
        <ReactNotification />
        <Popup />
        <App/>
    </BrowserRouter>,
    document.getElementById("root")
);

