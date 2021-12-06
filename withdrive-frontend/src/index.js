import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter } from "react-router-dom";
import "./index.css";
import App from "./App";
import Popup from 'react-popup';
import './data/popup.css'
import ReactNotification from 'react-notifications-component'
import 'react-notifications-component/dist/theme.css'


ReactDOM.render(
    <BrowserRouter>
        <Popup />
        <ReactNotification />

        <App/>
    </BrowserRouter>,
    document.getElementById("root")
);

