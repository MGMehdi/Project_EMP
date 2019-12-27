package be.heh.app.employee.emp_type;

/**
 * Commission
 */
public class Commission implements I_EmpType {

    private double _salary;

    public Commission(double _salary) {
        this._salary = _salary;
    }

    @Override
    public double get_pay() {
        return this._salary;
    }
}