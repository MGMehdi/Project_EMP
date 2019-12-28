package be.heh.app;

import java.util.ArrayList;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import be.heh.app.db.DatabaseHelper;
import be.heh.app.employee.Employee;
import be.heh.app.employee.classification.*;
import be.heh.app.employee.method.Deposit;
import be.heh.app.employee.method.Mailed;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		Employee e = new Employee();
		ArrayList<Employee> employees = new ArrayList<>();
		e.set_name("Mehdi");
		 e.set_address("Erbisoeul");
		 e.set_salary(3000);
		 e.set_hourlyRate(10.0);
		 e.set_hour(10.0);
		 e.set_account("BE795");
		 e.set_classification(new Hourly(e.get_hour(), e.get_hourlyRate()));
		e.set_method(new Mailed());
		
		DatabaseHelper db = new DatabaseHelper();

		//db.AddEmployee(e);
		db.GetEmployee(e);
		//db.GetAllEmployee(employees);
		db.DeleteEmployee(e);

		System.out.println(e.get_empID() + " " + e.get_name() + " " + e.calculatePay());

		for (Employee employee : employees) {
			System.out.println(employee.get_empID() + " " + employee.get_name() + " " + employee.calculatePay());

		}

		

		
	}

}
