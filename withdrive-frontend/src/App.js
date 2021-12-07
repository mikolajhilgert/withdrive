import React from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { BrowserRouter as Router, Switch, Route, Redirect} from "react-router-dom";
import AuthService from './services/AuthService';

import Index from "./pages";
import Login from "./pages/signin";
import SignUp from "./pages/signup";
import ViewTrips from "./pages/viewtrips";
import ViewTrip from "./pages/trip";
import NotFound from "./pages/notfound";
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

function App() {
  function requireAuth() {
    let result = true;
    AuthService.checkToken().then(response=> {
      result=response;
    })
    if (AuthService.getCurrentUser()!==null && result===true ) {
      return true;
    }
    else return false;
  }

  function requireAdminAuth(){
    let result = true;
    AuthService.checkTokenAdmin().then(response=> {
      result=response;
    })
    if (AuthService.getCurrentUser()!==null && result===true ) {
      if(AuthService.getCurrentUser().roles.includes("ROLE_ADMIN")===true){
        return true;
      }
      else return false;
    }
    else return false;
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
          <Route path="/create-trip" render={() => (requireAuth() ? (<CreateTrip/>) : (<NotFound/>))} />
          <Route path="/my-trips" render={() => (requireAuth() ? (<MyTrips/>) : (<NotFound/>))} />
          <Route path="/driver-trips" render={() => (requireAuth() ? (<DriverTrips/>) : (<NotFound/>))} />
          <Route path="/trip/edit" render={() => (requireAuth() ? (<EditTrip/>) : (<NotFound/>))} />
          <Route path="/trip/apply" render={() => (requireAuth() ? (<ApplyTrip/>) : (<NotFound/>))} />
          <Route path="/trip/apps" render={() => (requireAuth() ? (<ViewTripApps/>) : (<NotFound/>))} />
          <Route path="/my-profile" render={() => (requireAuth() ? (<MyProfile/>) : (<NotFound/>))} />

          <Route path="/view-users" render={() => (requireAdminAuth() ? (<AdminViewUsers/>) : (<NotFound/>))} />
          <Route path="/send-alerts" render={() => (requireAdminAuth() ? (<AdminSendAlerts/>) : (<NotFound/>))} />
          
          <Route component={NotFound}/>
        </Switch>
      </Router>
  );
}

export default App;