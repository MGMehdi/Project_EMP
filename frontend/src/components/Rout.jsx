import React from 'react'
import {BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Employee from './Employee'
import NavBar from './NavBar'
import FindUser from './FindUser';
import DisplayAll from './DisplayAll';
import AddUser from './AddUser';

function Rout() {
    return (
        <div>
            <Router>
                <NavBar></NavBar>
                <Switch>
                    <Route path="/user" component={FindUser}></Route>
                    <Route path="/users" component={DisplayAll}></Route>
                    <Route path="/user/:name" component={Employee}></Route>
                    <Route path="/add" component={AddUser}></Route>
                </Switch>
            </Router>
        </div>
    )
}

export default Rout
