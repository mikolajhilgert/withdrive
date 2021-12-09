import React from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { BrowserRouter as Router, Switch, Route, Redirect} from "react-router-dom";
import AuthService from './services/AuthService';
import jwtDecode from 'jwt-decode';

import Index from "./pages";
import Login from "./pages/signin";
import SignUp from "./pages/signup";
import ViewTrips from "./pages/viewtrips";
import ViewTrip from "./pages/trip";
import Nav from "./components/nav.component";
import SignOut from "./pages/signout";
import CreateTrip from "./pages/createtrip";
import MyTrips from "./pages/mytrips";
import DriverTrips from "./pages/drivertrips";
import EditTrip from "./pages/editatrip";
import ApplyTrip from "./pages/apply";
import ViewTripApps from "./pages/viewApps"
import AdminViewUsers from "./pages/admin/viewusers"
import AdminSendAlerts from "./pages/admin/sendalert"
import MyProfile from "./pages/myprofile"
import LogOut from './pages/signout';
import NotFound from './pages/notfound';

function App() {
  function requireAuth() {
    if (AuthService.getCurrentUser()!==null ) {
      const token = JSON.parse(localStorage.getItem("user"))&& JSON.parse(localStorage.getItem("user"))["access_token"];
      if (jwtDecode(token).exp > Date.now() / 1000) {
        return true;
      }
    }
    else return false;

  }

  function requireAdminAuth() {
    if (AuthService.getCurrentUser()!==null ) {
      const token = JSON.parse(localStorage.getItem("user"))&& JSON.parse(localStorage.getItem("user"))["access_token"];
      if (jwtDecode(token).exp > Date.now() / 1000 && AuthService.getCurrentUser().roles.includes("ROLE_ADMIN")===true) {
        return true;

      }
    }
    else return false;

  }

  // function requireAdminAuth(){
  //   let result = true;
  //   AuthService.checkTokenAdmin().then(response=> {
  //     result=response;
  //   })
  //   if (AuthService.getCurrentUser()!==null && result===true ) {
  //     if(AuthService.getCurrentUser().roles.includes("ROLE_ADMIN")===true){
  //       return true;
  //     }
  //     else return false;
  //   }
  //   else return false;
  // }

  function LogOrNotFound(){
    if (AuthService.getCurrentUser()!==null){
      return <LogOut/>
    }
    return <NotFound/>
  }
  
  return (
    <Router>
      <Nav/>
        <Switch>
          <Route exact path='/' component={Index} />
          <Route path="/view-trips" component={ViewTrips} />
          <Route path="/sign-out" component={SignOut} />
          <Route path="/trip/view" component={ViewTrip} />
          
          <Route path="/sign-in"render={() => (requireAuth() ? (<Redirect to="/view-trips"/>) : (<Login/>))} />
          <Route path="/sign-up" render={() => (requireAuth() ? (<Redirect to="/sign-in"/>) : (<SignUp/>))} />
          <Route path="/create-trip" render={() => (requireAuth() ? (<CreateTrip/>) : (LogOrNotFound()))} />
          <Route path="/my-trips" render={() => (requireAuth() ? (<MyTrips/>) : (LogOrNotFound()))} />
          <Route path="/driver-trips" render={() => (requireAuth() ? (<DriverTrips/>) : (LogOrNotFound()))} />
          <Route path="/trip/edit" render={() => (requireAuth() ? (<EditTrip/>) : (LogOrNotFound()))} />
          <Route path="/trip/apply" render={() => (requireAuth() ? (<ApplyTrip/>) : (LogOrNotFound()))} />
          <Route path="/trip/apps" render={() => (requireAuth() ? (<ViewTripApps/>) : (LogOrNotFound()))} />
          <Route path="/my-profile" render={() => (requireAuth() ? (<MyProfile/>) : (LogOrNotFound()))} />

          <Route path="/view-users" render={() => (requireAdminAuth() ? (<AdminViewUsers/>) : (LogOrNotFound()))} />
          <Route path="/send-alerts" render={() => (requireAdminAuth() ? (<AdminSendAlerts/>) : (LogOrNotFound()))} />
          
          <Route component={<NotFound/>}/>
        </Switch>
      </Router>
  );
}

export default App;