import Axios from 'axios'
import React, { Component } from 'react'

export class FindUser extends Component {
    constructor(props) {
        super(props)

        this.state = {
            employee: "",
            name: ""
        }
    }
    render() {
        console.log(this.state.employee)

        return (
            <div>
                <h1>Find an employee</h1>

                <p>Enter the name of the employee <input name="name" type="text" value={this.state.name} id="ipt_name" onChange={this.onChange} /></p>
                <button onClick={this.Find} className="btn btn-success">Submit</button>

                <div className="container">
                    <p>id : {this.state.employee._empID}</p>
                    <p>account : {this.state.employee._account}</p>
                    <p>address : {this.state.employee._address}</p>
                    <p>classification : {this.state.employee._classification}</p>
                    <p>method : {this.state.employee._method}</p>
                    <p>salary : {this.state.employee._salary}</p>
                </div>
                <button onClick={this.Delete} className="btn btn-danger">Delete</button>
            </div>
        )
    }

    onChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })
    }

    Find = () => {
        Axios.get(`http://localhost:8080/getuser/${this.state.name}`)
            .then((res) => {
                this.setState({
                    employee: res.data,
                })
                console.log(res.data._salary);

            }).catch(err => console.log(err))
    }

    Delete = () => {
        Axios.delete(`http://localhost:8080/deleteuser/${this.state.name}`)
            .then((res) => {
                console.log("DELETED\n" + res)
            })
            .catch((err) =>
                console.log(err)
            )
    }
}
export default FindUser
