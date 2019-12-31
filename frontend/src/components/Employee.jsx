import Axios from 'axios'
import React, { Component } from 'react'

export class Employee extends Component {
    constructor(props) {
        super(props)

        this.state = {
            employee: null,
            name: this.props.match.params.name,
            id: null
        }
    }

    componentDidMount() {
        Axios.get(`http://localhost:8080/user/${this.state.name}`)
            .then((res) => {
                console.log(res.data)
                this.setState({ employee: res.data })
                this.setState({ name: res.data._name })
                this.setState({ id: res.data._empID })

            }).catch(err => console.log(err))
    }

    render() {
        return (
            <div>
                <h1>Employee {this.props.match.params.name}</h1>

                <h2>{this.state.name}</h2>
                <h2>{this.state.id}</h2>
            </div>
        )
    }
}

export default Employee
