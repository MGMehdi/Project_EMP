package be.heh.app.employee;

import java.util.ArrayList;

import be.heh.app.employee.classification.*;
import be.heh.app.employee.method.I_Method;

/**
 * Employee
 */
public class Employee {

    private int _empID = 0;
    private String _name = "";
    private String _address = "";
    private String _account = "";
    private I_Classification _classification;
    private I_Method _method;

    private double _hourlyRate;
    private double _hour;
    private ArrayList <Double> _hours = new ArrayList<>();
    private double _salary;
    private double _commissionRate;

    public Employee() {
    }

    public Employee(int _id, String _name) {
        this._empID = _id;
        this._name = _name;
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

    public String get_account() {
        return _account;
    }

    public void set_account(String _account) {
        this._account = _account;
    }

    public I_Classification get_classification() {
        return _classification;
    }

    public void set_classification(I_Classification _classification) {
        this._classification = _classification;
    }

    public I_Method get_method() {
        return _method;
    }

    public void set_method(I_Method _method) {
        this._method = _method;
    }
    
    public double get_hourlyRate() {
        return _hourlyRate;
    }

    public void set_hourlyRate(double _hourlyRate) {
        this._hourlyRate = _hourlyRate;
    }

    public double get_hour() {
        return _hour;
    }

    public void set_hour(Double _hour) {
        this._hour = _hour;
    }

    public double get_salary() {
        return _salary;
    }

    public void set_salary(double _salary) {
        this._salary = _salary;
    }
    
    public double get_commissionRate() {
        return _commissionRate;
    }

    public void set_commissionRate(double _commissionRate) {
        this._commissionRate = _commissionRate;
    }
    /*****************************************************************************************************/

    public double calculatePay() {
        return this._classification.get_pay();
    }

    

    

    

	

    

}