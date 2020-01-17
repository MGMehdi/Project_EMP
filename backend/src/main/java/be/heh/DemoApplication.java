package be.heh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import be.heh.db.DatabaseHelper;
import be.heh.employee.Employee;
import be.heh.employee.classification.Commission;
import be.heh.employee.method.Deposit;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		//Employee e = new Employee();
		//DatabaseHelper db = new DatabaseHelper();

		
		//e.set_name("Idriss");
		//db.GetEmployee(e);

		//System.out.println(e.get_classification().getClass().getSimpleName());
		// e.set_address("Mons");
		// e.set_salary(4000);
		// e.set_account("BE795");
		// e.set_classification(new Commission(e.get_salary()));
		// e.set_method(new Deposit());

		// db.AddEmployee(e);

		// db.GetEmployee(e);
		// System.out.println("BEFORE " + e.get_empID() + " " + e.get_name() + " " +
		// e.calculatePay() + " " + e.get_classification().getClass().getSimpleName() +
		// " " + e.get_method().getClass().getSimpleName());

		// e.set_classification(new Salaried(e.get_salary()));
		// db.UpdateEmployee(e);
		// System.out.println(e.get_empID() + " " + e.get_name() + " " +
		// e.calculatePay() + " " + e.get_classification().getClass().getSimpleName() +
		// " " + e.get_method().getClass().getSimpleName());

		// db.GetEmployee(e);
		// System.out.println("AFTER" + e.get_empID() + " " + e.get_name() + " " +
		// e.calculatePay() + " " + e.get_classification().getClass().getSimpleName() +
		// " " + e.get_method().getClass().getSimpleName());

		// // db.GetEmployee(e);
		// // db.DeleteEmployee(e);

	}

}
