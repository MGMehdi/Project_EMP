package be.heh.app;

import java.util.ArrayList;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import be.heh.app.db.DatabaseHelper;
import be.heh.app.employee.Employee;
import be.heh.app.employee.emp_type.Commission;
import be.heh.app.employee.emp_type.Hourly;
import be.heh.app.employee.emp_type.Salaried;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		Employee e = new Employee();
		e.set_name("tata");
		e.set_address("tata city");
		e.set_bank("tata $");
		e.set_salary(2000.5);
		DatabaseHelper db = new DatabaseHelper();
		ArrayList<Double>hours = new ArrayList<>();

		hours.add(10.0);
		hours.add(11.0);
		hours.add(12.0);
		hours.add(13.0);

		e.set_hours(hours);
		

		e.set_empType(new Salaried(e.get_salary()));
		System.out.println(e.calculatePay());
		db.AddEmployee(e);
		System.out.println(e.get_name());

		
	}

}
