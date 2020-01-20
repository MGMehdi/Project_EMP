package be.heh.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.heh.db.DatabaseHelper;
import be.heh.employee.Employee;
import be.heh.employee.classification.Commission;
import be.heh.employee.classification.Hourly;
import be.heh.employee.classification.Salaried;
import be.heh.employee.method.Deposit;
import be.heh.employee.method.Mailed;
import be.heh.employee.method.PayMaster;

/**
 * Controller
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000") // Lie le backend au frontend via le lien du frontend
public class Controller {

    DatabaseHelper db = new DatabaseHelper();

    @GetMapping(value = "/")
    public ResponseEntity test() {
        return ResponseEntity.ok("TEST");
    }

    @GetMapping(value = "/helloworld")
    public ResponseEntity helloworld() {
        return ResponseEntity.ok("Hello REST");
    }

    @GetMapping(value = "/getuser/{name}")
    public ResponseEntity getuser(@PathVariable(name = "name") String name) {
        System.out.println("COUCOU");

        Employee e = new Employee();
        e.set_name(name);
        db.GetEmployee(e);
        db.GetTimeCard(e);

        e.set_salary(e.calculatePay());

        if (e.get_empID() == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(e);
        }

    }

    @DeleteMapping(value = "/deleteuser/{name}")
    public void deluser(@PathVariable(name = "name") String name) {
        Employee e = new Employee();
        e.set_name(name);
        db.GetEmployee(e);
        System.out.println(e.get_empID());
        db.DeleteEmployee(e);
    }

    @GetMapping(value = "/getusers")
    public ResponseEntity getAllUser() {
        Employee e = new Employee();
        ArrayList<Employee> employees = new ArrayList<>();
        employees = db.GetAllEmployee(employees);
        System.out.println(e.get_empID());
        return ResponseEntity.ok(employees);
    }

    @PostMapping(value = "/adduser")
    public ResponseEntity addUser(@RequestBody Employee e) {

        switch (e.get_classification()) {
        case "Salaried":
            e.set_Iclassification(new Salaried(e.get_salary()));
            break;
        case "Hourly":
            e.set_Iclassification(new Hourly(e.get_hourlyRate()));
            break;
        case "Commission":
            e.set_Iclassification(new Commission(e.get_salary()));
            break;
        default:
            break;
        }
        switch (e.get_method()) {
        case "Deposit":
            e.set_Imethod(new Deposit());
            break;
        case "Mailed":
            e.set_Imethod(new Mailed());
            break;
        case "PayMaster":
            e.set_Imethod(new PayMaster());
            break;
        default:
            break;
        }

        db.AddEmployee(e);
        System.out.println(e.get_Iclassification().get_pay());
        return ResponseEntity.ok(e);
    }

    @PostMapping(value = "/updateuser")
    public ResponseEntity updateUser(@RequestBody Employee e) {
        switch (e.get_classification()) {
        case "Salaried":
            e.set_Iclassification(new Salaried(e.get_salary()));
            break;
        case "Hourly":
            e.set_Iclassification(new Hourly(e.get_hourlyRate()));
            break;
        case "Commission":
            e.set_Iclassification(new Commission(e.get_salary()));
            break;
        default:
            break;
        }
        switch (e.get_method()) {
        case "Deposit":
            e.set_Imethod(new Deposit());
            break;
        case "Mailed":
            e.set_Imethod(new Mailed());
            break;
        case "PayMaster":
            e.set_Imethod(new PayMaster());
            break;
        default:
            break;
        }
        db.UpdateEmployee(e);
        System.out.println(e.get_empID() + " " + e.get_name() + " " + e.get_address() + " " + e.get_account() + " "
                + e.get_Imethod().getClass().getSimpleName() + " " + e.get_Iclassification().getClass().getSimpleName()
                + " " + e.get_salary() + " " + e.get_hourlyRate());

        return ResponseEntity.ok("\n" + e);
    }

    @PostMapping(value = "/timecard/{id}")
    public ResponseEntity AddTimeCard(@PathVariable(name = "id") int id, @RequestParam Double hours) {
        Employee e = new Employee();
        e.set_empID(id);
        System.out.println("Resultat " + " " + hours + " " + id);
        db.AddTimeCard(hours, e);
        return ResponseEntity.ok(0);
    }

    // public ResponseEntity addUser(@RequestBody String json) {
    // Employee e = new Employee();
    // try {
    // ObjectMapper mapper = new ObjectMapper();
    // e = mapper.readValue(json, Employee.class);
    // db.AddEmployee(e);
    // } catch (Exception ex) {
    // System.out.println("ERROR\n" + ex);
    // }
    // return ResponseEntity.ok(e);
    // }

}