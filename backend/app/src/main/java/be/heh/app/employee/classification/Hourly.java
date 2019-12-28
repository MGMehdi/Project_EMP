package be.heh.app.employee.classification;

import java.util.ArrayList;

/**
 * Hourly
 */
public class Hourly implements I_Classification {

    private ArrayList<Double> _hours;
    private double _hour;
    private double _hourlyRate;

    public Hourly(double _hour, double _hourlyRate) {
        this._hour = _hour;
        this._hourlyRate = _hourlyRate;
    }

    public Hourly(ArrayList _hours, double _hourlyRate) {
        this._hours = _hours;
        this._hourlyRate = _hourlyRate;
    }

    @Override
    public double get_pay() {
        double salary = 0.0;

        if (this._hour > 8) {
            salary += this._hourlyRate * 8;
            salary += this._hourlyRate * ((this._hour - 8) * 1.5);
        } else {
            salary += this._hourlyRate * this._hour;
        }
        // for (Double d : _hours) {
        // if (d > 8) {
        // salary += this._hourlyRate * 8;
        // salary += this._hourlyRate * ((d - 8) * 1.5);
        // }else{
        // salary += this._hourlyRate * d;
        // }
        // }
        return salary;
    }

}