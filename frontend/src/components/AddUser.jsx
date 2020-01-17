import React, { Component, Fragment } from 'react'
import Axios from 'axios'

export class AddUser extends Component {

    constructor(props) {
        super(props)

        this.state = {
            name: "",
            address: "",
            account: "",
            Classification: "",
            Commission: "",
            Hourly: "",
            Salaried: "",
            Method: "",
            Deposit: "",
            Mailed: "",
            PayMaster: "",
            salary: 0,
            commission: "",
            hours: 0,
            hourly: 0
        }
    }


    render() {
        return (
            <Fragment>
                <form onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        <label>Name</label>
                        <input name="name" type="text" className="form-control" placeholder="Name" onChange={this.handleChange} />
                        <label>Address</label>
                        <input name="address" type="text" className="form-control" placeholder="Address" onChange={this.handleChange} />
                        <label>Account</label>
                        <input name="account" type="text" className="form-control" placeholder="Account" onChange={this.handleChange} />
                    </div>

                    <div className="form-group">
                        <label>Classification</label>
                        <select className="form-control" id="classification" onChange={this.handleChange}>
                            <option>---</option>
                            <option value="Commission">Commission</option>
                            <option value="Hourly">Hourly</option>
                            <option value="Salaried">Salaried</option>
                        </select>
                        <div>
                            <label>Salary</label>
                            <input type="number" className="form-control" placeholder="Salary" disabled={!this.state.Salaried} />
                        </div>
                        <div>
                            <label>Hours</label>
                            <input type="number" className="form-control" placeholder="Hours" disabled={!this.state.Hourly} />
                            <label>Hourly rate</label>
                            <input type="number" className="form-control" placeholder="Hourly rate" disabled={!this.state.Hourly} />
                        </div>
                        <div>
                            <label>Salary</label>
                            <input type="number" className="form-control" placeholder="Salary" disabled={!this.state.Commission} />
                            <label>Commission</label>
                            <input type="number" className="form-control" placeholder="Commission" disabled={!this.state.Commission} />
                        </div>
                    </div>

                    <div className="form-group">
                        <label>Paiement method</label>
                        <select className="form-control" id="method" onChange={this.handleChange}>
                            <option>---</option>
                            <option value="Deposit">Direct deposit</option>
                            <option value="Mailed">Mailed</option>
                            <option value="PayMaster">PayMaster</option>
                        </select>
                    </div>
                    <input type="submit" value="Submit" />
                </form>
            </Fragment>
        )
    }

    handleChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })
        switch (event.target.value) {
            case "Salaried":
                this.setState({
                    Commission: "",
                    Hourly: "",
                    Salaried: "Salaried",
                    Classification: "Salaried"
                })
                break;
            case "Hourly":
                this.setState({
                    Commission: "",
                    Hourly: "Hourly",
                    Salaried: "",
                    Classification: "Hourly"
                })
                break;
            case "Commission":
                this.setState({
                    Commission: "Commission",
                    Hourly: "",
                    Salaried: "",
                    Classification: "Commission"
                })
                break;
            case "Deposit":
                this.setState({
                    Deposit: "Deposit",
                    Mailed: "",
                    PayMaster: "",
                    Method: "Deposit"
                })
                break;
            case "Mailed":
                this.setState({
                    Deposit: "",
                    Mailed: "Mailed",
                    PayMaster: "",
                    Method: "Mailed"
                })
                break;
            case "PayMaster":
                this.setState({
                    Deposit: "",
                    Mailed: "",
                    PayMaster: "PayMaster",
                    Method: "PayMaster"
                })
                break;
            default:
                this.setState({
                    Commission: "",
                    Hourly: "",
                    Salaried: "",
                    Deposit: "",
                    Mailed: "",
                    PayMaster: "",
                    Classification: "",
                    Method: ""
                })
                break;
        }
    }

    handleSubmit = (event) => {
        console.log(this.state.Classification + " " + this.state.Method);

        Axios.post(`http://localhost:8080/add`, {
            _name: this.state.name,
            _address: this.state.address,
            _account: this.state.account,
            _classification: this.state.Classification,
            _method: this.state.Method,
            _hourlyRate: this.state.hourly,
            _hour: this.state.hours,
            _salary: this.state.salary,
            _commissionRate: this.state.commission
        })
            .then(res => {
                console.log(res);
                console.log(res.data);
            })
            .catch(err => {
                console.log(err);
            })
        event.preventDefault();
    }
}



export default AddUser
