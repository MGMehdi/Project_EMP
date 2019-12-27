package be.heh.app.employee;

import java.util.ArrayList;

import be.heh.app.employee.emp_type.I_EmpType;

/**
 * Employee
 */
public class Employee {

    private int _empID = 0;
    private String _name = "";
    private String _address = "";
    private String _bank = "";
    private I_EmpType _empType;

    private double _hourlyRate;
    private ArrayList <Double> _hours = new ArrayList();
    private double _salary;

    public Employee() {
    }

    public Employee(String _name, String _address) {
        this._name = _name;
        this._address = _address;
    }

    /**********************************************************************************************************/

    public int get_empID() {
        return _empID;
    }

    public void set_empID(int _empID) {
        this._empID = _empID;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_bank() {
        return _bank;
    }

    public void set_bank(String _bank) {
        this._bank = _bank;
    }

    public I_EmpType get_empType() {
        return _empType;
    }

    public void set_empType(I_EmpType _empType) {
        this._empType = _empType;
    }

    public double get_hourlyRate() {
        return _hourlyRate;
    }

    public void set_hourlyRate(double _hourlyRate) {
        this._hourlyRate = _hourlyRate;
    }

    public ArrayList get_hours() {
        return _hours;
    }

    public void set_hours(ArrayList<Double> _hours) {
        this._hours = _hours;
    }

    public double get_salary() {
        return _salary;
    }

    public void set_salary(double _salary) {
        this._salary = _salary;
    }

    /*****************************************************************************************************/

    public double calculatePay() {
        return this._empType.get_pay();
    }

    

}