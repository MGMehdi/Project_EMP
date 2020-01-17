package be.heh.employee;

import java.util.ArrayList;

import be.heh.employee.classification.*;
import be.heh.employee.method.I_Method;

/**
 * Employee
 */
public class Employee {

    private int _empID = 0;
    private String _name;
    private String _address;
    private String _account;
    private I_Classification _Iclassification;
    private String _classification;
    private I_Method _Imethod;
    private String _method;

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

    public I_Classification get_Iclassification() {
        return _Iclassification;
    }

    public void set_Iclassification(I_Classification _Iclassification) {
        this._Iclassification = _Iclassification;
    }

    public I_Method get_Imethod() {
        return _Imethod;
    }

    public void set_Imethod(I_Method _Imethod) {
        this._Imethod = _Imethod;
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

    public String get_classification() {
        return _classification;
    }

    public String get_method() {
        return _method;
    }

    public void set_classification(String _classification) {
        this._classification = _classification;
    }

    public void set_method(String _method) {
        this._method = _method;
    }

    /*****************************************************************************************************/

    public double calculatePay() {
        return this._Iclassification.get_pay();
    }

    
    

    

    

    

	

    

}