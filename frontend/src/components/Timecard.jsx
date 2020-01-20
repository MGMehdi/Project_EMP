import React, { Component } from 'react'
import Axios from 'axios'

export class Timecard extends Component {

    constructor(props) {
        super(props)

        this.state = {
            name: "",
            employee: null,
            _hours:0
        }
    }


    render() {
        return (
            <div>
                <label htmlFor="">Find employee</label>
                <input type="text" name="name" placeholder="Name" onChange={this.HandleChange} />
                <input type="button" value="Find" onClick={this.FindUser} />
                <label htmlFor="">Hours</label>
                <input type="number" name="_hours" onChange={this.HandleChange}/>
                <input type="button" value="Submit" onClick={this.AddHour} />

            </div>
        )
    }

    HandleChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })
        console.log(event.target.name + " " + event.target.value);
    }

    FindUser = () => {
        Axios.get(`http://localhost:8080/getuser/${this.state.name}`)
            .then(res => {
                this.setState({
                    employee: res.data
                })
                console.log(this.state.employee._empID);
            })
            .catch(err => {
                console.log(err);
            })
    }

    AddHour = () => {
        Axios.post(`http://localhost:8080/timecard/${this.state.employee._empID}`, null, { params: {
            hours: this.state._hours
        }})
        .then(res => {
            console.log(res);
        })
        .catch(err => {
            console.log(err);
        })
    }


}

export default Timecard
