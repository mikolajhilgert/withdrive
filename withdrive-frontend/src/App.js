import React from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { BrowserRouter as Router, Switch, Route} from "react-router-dom";

import Index from "./pages";
import Login from "./pages/signin";
import SignUp from "./pages/signup";
import ViewTrips from "./pages/viewtrips";
import ApiTest from "./pages/apitest";
import NotFound from "./pages/notfound";
import Nav from "./components/nav.component";
import SignOut from "./pages/signout";
import CreateTrip from "./pages/createtrip";

function App() {
  return (
    <Router>
      <Nav/>
      <div className="auth-wrapper">  
            <div className="auth-inner">
              <Switch>
                <Route exact path='/' component={Index} />
                <Route path="/sign-in" component={Login} />
                <Route path="/sign-up" component={SignUp} />
                <Route path="/view-trips" component={ViewTrips} />
                <Route path="/create-trip" component={CreateTrip} />
                <Route path="/sign-out" component={SignOut} />
                <Route component={NotFound}/>
              </Switch>
            </div>
          </div>
      </Router>
  );
}

export default App;