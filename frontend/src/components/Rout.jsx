import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import AddUser from './AddUser';
import DisplayAll from './DisplayAll';
import FindUser from './FindUser';
import NavBar from './NavBar';
import Timecard from './Timecard';
import UpdateUser from './UpdateUser';

function Rout() {
    return (
        <div>
            <Router>
                <NavBar></NavBar>
                <Switch>
                    <Route path="/user" component={FindUser}></Route>
                    <Route path="/users" component={DisplayAll}></Route>
                    <Route path="/add" component={AddUser}></Route>
                    <Route path="/update" component={UpdateUser}></Route>
                    <Route path="/timecard" component={Timecard}></Route>
                </Switch>
            </Router>
        </div>
    )
}

export default Rout
