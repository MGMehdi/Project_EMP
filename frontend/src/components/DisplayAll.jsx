import React, { Component } from 'react'
import Axios from 'axios'

export class DisplayAll extends Component {
    constructor(props) {
        super(props)

        this.state = {
            employees: []
        }
    }

    render() {
        return (
            <div>
                <table className="table">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>ID</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.employees.map(
                                e =>
                                    <tr key={e._empID}>
                                        <td>{e._name}</td>
                                        <td>{e._empID}</td>
                                    </tr>
                            )
                        }
                    </tbody>
                </table>

            </div>
        )
    }

    componentDidMount() {
        this.getAllEmployee()
    }

    getAllEmployee = () => {
        Axios.get(`http://localhost:8080/getusers`)
            .then((res) => {
                this.setState({ employees: res.data })
            })
    }
}

export default DisplayAll
