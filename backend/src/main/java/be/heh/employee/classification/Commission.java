package be.heh.employee.classification;

/**
 * Commission
 */
public class Commission implements I_Classification {

    private double _salary;

    public Commission(double _salary) {
        this._salary = _salary;
    }

    @Override
    public double get_pay() {
        return this._salary;
    }
}