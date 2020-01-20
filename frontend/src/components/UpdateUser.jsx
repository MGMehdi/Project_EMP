import React, { Component } from 'react'
import Axios from 'axios'

export class UpdateUser extends Component {

    constructor(props) {
        super(props)

        this.state = {
            name: "",
            employee: null,
            selectedClassification: ""
        }
    }


    render() {
        return (
            <div>
                <p>{JSON.stringify(this.state.employee)}</p>
                <form onSubmit={this.UpdateUser}>
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
                    <input type="button" value="Submit" onClick={this.UpdateUser} />
                </form>
                <button onClick={this.findUser}>HOHE</button>
            </div>
        )
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

    handleChangeName = (event) => {
        this.setState({
            name: event.target.value
        })
    }

    findUser = () => {
        Axios.get(`http://localhost:8080/getuser/${this.state.name}`)
            .then((res) => {
                this.setState({
                    employee: res.data,
                })
                console.log(this.state.employee);
                document.getElementById("address").value = this.state.employee._address
                document.getElementById("account").value = this.state.employee._account
                switch (this.state.employee._classification) {
                    case "Salaried":
                        document.getElementById("classification").value = "Salaried"
                        document.getElementById("salary").value = this.state.employee._salary
                        break;
                    case "Hourly":
                        document.getElementById("classification").value = "Hourly"
                        document.getElementById("hourly").value = this.state.employee._hourlyRate
                        break;
                    case "Commission":
                        document.getElementById("classification").value = "Commission"
                        document.getElementById("commissionSalary").value = this.state.employee._salary
                        document.getElementById("commission").value = this.state.employee._commission
                        break;
                    default:
                        break;
                }
                switch (this.state.employee._method) {
                    case "Deposit":
                        document.getElementById("method").value = "Deposit"
                        break;
                    case "Mailed":
                        document.getElementById("method").value = "Mailed"
                        break;
                    case "PayMaster":
                        document.getElementById("method").value = "PayMaster"
                        break;
                    default:
                        break;
                }
                

            }).catch(err => console.log(err))
    }

    UpdateUser = () => {
        console.log(this.state.employee._empID);

        const emp = {
            _empID: this.state.employee._empID,
            _name: document.getElementById("name").value,
            _address: document.getElementById("address").value,
            _account: document.getElementById("account").value,
            _classification: document.getElementById("classification").value,
            _method: document.getElementById("method").value,
            _salary: 0,
            _hourlyRate: 0,
            _commission: 0,
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
        console.log(emp);

        Axios.post(`http://localhost:8080/updateuser`, emp)
            .then(res => {
                console.log(res);
            }).catch(err => {
                console.log(err);
            })
    }


}

export default UpdateUser
