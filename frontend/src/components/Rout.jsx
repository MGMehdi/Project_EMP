import React from 'react'
import {BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Employee from './Employee'
import NavBar from './NavBar'
import FindUser from './FindUser';
import DisplayAll from './DisplayAll';
import AddUser from './AddUser';
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
                </Switch>
            </Router>
        </div>
    )
}

export default Rout
