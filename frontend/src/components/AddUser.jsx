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
                        <input type="text" className="form-control" id="name" name="name" placeholder="Name" onChange={this.handleChangeName} />
                        <label>Address</label>
                        <input type="text" className="form-control" id="address" name="address" placeholder="Address" />
                        <label>Account</label>
                        <input type="text" className="form-control" id="account" name="account" placeholder="Account" />

                    </div>

                    <div className="form-group">
                        <label>Classification</label>
                        <select className="form-control" id="classification" onChange={this.handleChangeclassification}>
                            <option>---</option>
                            <option value="Commission">Commission</option>
                            <option value="Hourly">Hourly</option>
                            <option value="Salaried">Salaried</option>
                        </select>
                        <div>
                            <label>Salary</label>
                            <input id="salary" type="number" className="form-control" placeholder="Salary" disabled={true} />
                        </div>
                        <div>
                            <label>Hours</label>
                            <label>Hourly rate</label>
                            <input id="hourly" type="number" className="form-control" placeholder="Hourly rate" disabled={true} />
                        </div>
                        <div>
                            <label>Salary</label>
                            <input id="commissionSalary" type="number" className="form-control" placeholder="Salary" disabled={true} />
                            <label>Commission</label>
                            <input id="commission" type="number" className="form-control" placeholder="Commission" disabled={true} />
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
            commission: 0,
            hours: 0,
            hourly: 0
        })
    }
    handleChangeCommission = (event) => {
        this.setState({
            [event.target.name]: event.target.value,
            hours: 0,
            hourly: 0
        })
    }
    handleChangeHours = (event) => {
        this.setState({
            [event.target.name]: event.target.value,
            commission: 0,
            salary: 0
        })
    }

    handleChangeInfo = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })
    }

    handleChangeclassification = (event) => {
        this.setState({
            selectedClassification: event.target.value
        })
        switch (event.target.value) {
            case "Commission":
                document.getElementById("salary").disabled = true
                document.getElementById("hourly").disabled = true
                document.getElementById("commission").disabled = false
                document.getElementById("commissionSalary").disabled = false

                break;
            case "Hourly":
                document.getElementById("salary").disabled = true
                document.getElementById("hourly").disabled = false
                document.getElementById("commission").disabled = true
                document.getElementById("commissionSalary").disabled = true
                break;
            case "Salaried":
                document.getElementById("salary").disabled = false
                document.getElementById("hourly").disabled = true
                document.getElementById("commission").disabled = true
                document.getElementById("commissionSalary").disabled = true
                break;
            default:
                document.getElementById("salary").disabled = true
                document.getElementById("hourly").disabled = true
                document.getElementById("commission").disabled = true
                document.getElementById("commissionSalary").disabled = true
                break;
        }
    }

    handleSubmit = (event) => {
        const emp = {
            _name: document.getElementById("name").value,
            _address: document.getElementById("address").value,
            _account: document.getElementById("account").value,
            _classification: document.getElementById("classification").value,
            _method: document.getElementById("method").value,
            _hourlyRate: document.getElementById("hourly").value,
            _salary: document.getElementById("salary").value,
            _commissionRate: document.getElementById("commission").value
        }
        switch (emp._classification) {
            case "Salaried":
                emp._salary = document.getElementById("salary").value
                break;
            case "Commission":
                emp._salary = document.getElementById("commissionSalary").value
                emp._commission = document.getElementById("commission").value
                break;
            case "Hourly":
                emp._hourlyRate = document.getElementById("hourly").value
                break;
            default:
                console.log("CACA");

                break;
        }
        console.log(this.state.Classification + " " + this.state.Method);
        Axios.post(`http://localhost:8080/adduser`, emp)
            .then(res => {
                console.log(res);
                console.log(res.data);
            })
            .catch(err => {
                console.log(err);
            })
    }
}

export default AddUser