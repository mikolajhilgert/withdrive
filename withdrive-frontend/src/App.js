import React from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { BrowserRouter as Router, Switch, Route} from "react-router-dom";

import Index from "./pages";
import Login from "./pages/signin";
import SignUp from "./pages/signup";
import ViewTrips from "./pages/viewtrips";
import ViewTrip from "./pages/trip";
import NotFound from "./pages/notfound";
import Nav from "./components/nav.component";
import SignOut from "./pages/signout";
import CreateTrip from "./pages/createtrip";

function App() {
  return (
    <Router>
      <Nav/>
        <Switch>
          <Route exact path='/' component={Index} />
          <Route path="/sign-in" component={Login} />
          <Route path="/sign-up" component={SignUp} />
          <Route path="/view-trips" component={ViewTrips} />
          <Route path="/create-trip" component={CreateTrip} />
          <Route path="/sign-out" component={SignOut} />
          <Route path="/trip/view" component={ViewTrip} />
          <Route component={NotFound}/>
        </Switch>
      </Router>
  );
}

export default App;