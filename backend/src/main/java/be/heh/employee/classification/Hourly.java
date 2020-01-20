package be.heh.employee.classification;

import java.util.ArrayList;

/**
 * Hourly
 */
public class Hourly implements I_Classification {

    private ArrayList<Double> _hourList;
    // private double _hour;
    private double _hourlyRate;

    public Hourly() {
    }

    public Hourly(double _hourlyRate) {
        this._hourlyRate = _hourlyRate;
        System.out.println("ICI ");

    }

    public Hourly(double _hourlyRate, ArrayList hourList) {
        this._hourlyRate = _hourlyRate;
        this._hourList = hourList;
    }

    public double get_hourlyRate() {
        return _hourlyRate;
    }

    public void set_hourlyRate(double _hourlyRate) {
        this._hourlyRate = _hourlyRate;
    }

    public ArrayList<Double> getTimeCards() {
        return _hourList;
    }

    public void setTimeCards(ArrayList<Double> hourList) {
        this._hourList = hourList;
    }

    @Override
    public double get_pay() {
        double salary = 0.0;
        try {

            for (Double hour : this._hourList) {
                

                if (hour > 8) {
                    System.out.println("LA");
                    salary += this._hourlyRate * 8;
                    salary += this._hourlyRate * ((hour - 8) * 1.5);
                } else {
                    System.out.println("LAAAA " + _hourlyRate + " " + hour);
                    salary += this._hourlyRate * hour;
                }
            }
            return salary;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }

        // if (this._hour > 8) {
        // salary += this._hourlyRate * 8;
        // salary += this._hourlyRate * ((this._hour - 8) * 1.5);
        // } else {
        // salary += this._hourlyRate * this._hour;
        // }

    }

}