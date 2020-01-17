import React, { Component } from 'react'
import Employee from './Employee'
import Axios from 'axios'

export class FindUser extends Component {
    constructor(props) {
        super(props)

        this.state = {
            employee: null,
            name: "",
            id: null,
            address : "",
            account : "",
            classification:""
        }
    }
    render() {
        console.log(this.state.employee)

        return (
            <div>
                <h1>Find an employee</h1>

                <p>Enter the name of the employee <input name="name" type="text" value={this.state.name} id="ipt_name" onChange={this.onChange} /></p>
                <button onClick={this.onClick} className="btn btn-success">Submit</button>

                <div className="container">
                    <p>id : {this.state.id}</p>
                    <p>account : {this.state.account}</p>
                    <p>address : {this.state.address}</p>
                    <p>classification : {this.state.classification}</p>
                    {JSON.stringify(this.state.employee)}
                </div>
            </div>
        )
    }

    onChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })
    }

    onClick = () => {
        Axios.get(`http://localhost:8080/user/${this.state.name}`)
            .then((res) => {
                this.setState({
                    employee: res.data,
                    id: res.data._empID,
                    account: res.data._account,
                    address: res.data._address,
                    classification:res.data._classification
                })
                
            }).catch(err => console.log(err))
    }
}
export default FindUser
