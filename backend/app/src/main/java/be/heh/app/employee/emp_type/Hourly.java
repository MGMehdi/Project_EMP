package be.heh.app.employee.emp_type;

import java.util.ArrayList;

/**
 * Hourly
 */
public class Hourly implements I_EmpType {

    private ArrayList<Double> _hours;
    private double _hourlyRate;

    public Hourly(ArrayList _hours, double _hourlyRate) {
        this._hours = _hours;
        this._hourlyRate = _hourlyRate;
    }
    
    @Override
    public double get_pay() {
        double salary = 0.0;

        for (Double d : _hours) {
            if (d > 8) {
                salary += this._hourlyRate * 8;
                salary += this._hourlyRate * ((d - 8) * 1.5);
            }else{
                salary += this._hourlyRate * d;
            }
        }
        return salary;
    }



    
  
}