package be.heh.controller;

import org.springframework.web.bind.annotation.RestController;

import be.heh.db.DatabaseHelper;
import be.heh.employee.Employee;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Controller
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000") //Lie le backend au frontend via le lien du frontend
public class Controller {

    DatabaseHelper db = new DatabaseHelper();

    @GetMapping(value="/")
    public ResponseEntity test() {
        return ResponseEntity.ok("TEST");
    }

    @GetMapping(value="/helloworld")
    public ResponseEntity helloworld() {
        return ResponseEntity.ok("Hello REST");
    }

    @GetMapping(value="/user/{name}")
    public ResponseEntity getuser(@PathVariable(name="name") String name) {
        Employee e = new Employee();
        e.set_name(name);
        db.GetEmployee(e);
        System.out.println(e.get_empID());
        return ResponseEntity.ok(e);
    }

    @GetMapping(value="/users")
    public ResponseEntity getAllUser() {
        Employee e = new Employee();
        ArrayList<Employee> employees = new ArrayList<>();
        employees = db.GetAllEmployee(employees);
        System.out.println(e.get_empID());
        return ResponseEntity.ok(employees);
    }
    
    
    
}