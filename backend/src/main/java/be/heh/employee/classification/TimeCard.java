package be.heh.employee.classification;

/**
 * TimeCard
 */
public class TimeCard {

    private double _hour;

    public TimeCard() {
    }

    public TimeCard(double _hour) {
        this._hour = _hour;
    }

    public double get_hour() {
        return _hour;
    }

    public void set_hour(double _hour) {
        this._hour = _hour;
    }    
}