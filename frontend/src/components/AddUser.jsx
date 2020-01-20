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
            hourly: 0
        }
    }

    render() {
        return (
            <Fragment>
                <form onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        <label>Name</label>
                        <input name="name" type="text" className="form-control" placeholder="Name" onChange={this.handleChangeInfo} />
                        <label>Address</label>
                        <input name="address" type="text" className="form-control" placeholder="Address" onChange={this.handleChangeInfo} />
                        <label>Account</label>
                        <input name="account" type="text" className="form-control" placeholder="Account" onChange={this.handleChangeInfo} />
                    </div>

                    <div className="form-group">
                        <label>Classification</label>
                        <select className="form-control" id="classification" onChange={this.handleChangeclassificationMethod}>
                            <option>---</option>
                            <option value="Commission">Commission</option>
                            <option value="Hourly">Hourly</option>
                            <option value="Salaried">Salaried</option>
                        </select>
                        <div>
                            <label>Salary</label>
                            <input name="salary" type="number" className="form-control" placeholder="Salary" disabled={!this.state.Salaried} onChange={this.handleChangeSalary}/>
                        </div>
                        <div>
                            <label>Hourly rate</label>
                            <input name="hourly" type="number" className="form-control" placeholder="Hourly rate" disabled={!this.state.Hourly} onChange={this.handleChangeHours}/>
                        </div>
                        <div>
                            <label>Salary</label>
                            <input name="salary" type="number" className="form-control" placeholder="Salary" disabled={!this.state.Commission} onChange={this.handleChangeCommission}/>
                            <label>Commission</label>
                            <input name="commission" type="number" className="form-control" placeholder="Commission" disabled={!this.state.Commission} onChange={this.handleChangeCommission}/>
                        </div>
                    </div>

                    <div className="form-group">
                        <label>Paiement method</label>
                        <select className="form-control" id="method" onChange={this.handleChangeclassificationMethod}>
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

    handleChangeSalary = (event) => {
        this.setState({
            [event.target.name]: event.target.value,
            commission:0,
            hours:0,
            hourly:0
        })
    }
    handleChangeCommission = (event) => {
        this.setState({
            [event.target.name]: event.target.value,
            hours:0,
            hourly:0
        })
    }
    handleChangeHours = (event) => {
        this.setState({
            [event.target.name]: event.target.value,
            commission:0,
            salary:0
        })
    }

    handleChangeInfo = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })
    }

    handleChangeclassificationMethod = (event) => {
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
        Axios.post(`http://localhost:8080/adduser`, {
            _name: this.state.name,
            _address: this.state.address,
            _account: this.state.account,
            _classification: this.state.Classification,
            _method: this.state.Method,
            _hourlyRate: this.state.hourly,
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