package be.heh.app.employee.emp_type;

/**
 * Salaried
 */
public class Salaried implements I_EmpType {

    private double _salary;

    public Salaried(double _salary) {
        this._salary = _salary;
    }
    
    @Override
    public double get_pay() {
        return this._salary;
    }

    

    
}