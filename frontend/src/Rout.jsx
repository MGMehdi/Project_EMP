import React from 'react'
import {BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Employee from './components/Employee'

function Rout() {
    return (
        <div>
            <Router>
                <Switch>
                    <Route path="/user" component={Employee}></Route>
                </Switch>
            </Router>
        </div>
    )
}

export default Rout
