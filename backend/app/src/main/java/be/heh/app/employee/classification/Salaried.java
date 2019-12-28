package be.heh.app.employee.classification;

/**
 * Salaried
 */
public class Salaried implements I_Classification {

    private double _salary;

    public Salaried(double _salary) {
        this._salary = _salary;
    }
    
    @Override
    public double get_pay() {
        return this._salary;
    }

    

    
}