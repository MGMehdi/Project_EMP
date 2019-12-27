package be.heh.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.heh.app.employee.Employee;

/**
 * DatabaseHelper
 */
public class DatabaseHelper {
    private String url = "jdbc:postgresql://192.168.1.128/test";
    private String user = "postgres";
    private String password = "postgres";
    private Connection connection = null;

    public DatabaseHelper() {
        Connect();
    }

    private Connection Connect() {
        try {
            this.connection = DriverManager.getConnection(this.url, this.user, this.password);
            System.out.println("Connected to database successfully");
        } catch (SQLException e) {
            System.out.println("Cannot connect to database\n" + e.getMessage());
        }
        return this.connection;
    }

    /********************************************************************************************************************************/

    public Employee AddEmployee(Employee e) {

        String sql;
        sql = "INSERT INTO public.employee (name, address, bank) VALUES (?, ?, ?)";
        try (PreparedStatement ps = this.connection.prepareStatement(sql, new String[] {"id"})) {
            ps.setString(1, e.get_name());
            ps.setString(2, e.get_address());
            ps.setString(3, e.get_bank());
            int empId = ps.executeUpdate();
            if (empId > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()){
                    if (rs.next()) {
                        e.set_empID(rs.getInt("id"));
                        System.out.println(e.get_empID());
                    }
                } catch (SQLException se) {
                    System.out.println(se);
                }
            }
             switch (e.get_empType().getClass().getSimpleName()) {
             case "Salaried":
                 sql = "INSERT INTO salaried (id, salary) VALUES ((SELECT id FROM employee WHERE id=?), ?)";
                 PreparedStatement p = this.connection.prepareStatement(sql);
                 p.setInt(1, e.get_empID());
                 System.out.println(e.get_salary());
                 p.setDouble(2, e.get_salary());
                 p.executeUpdate();
                 break;

             case "Hourly":
                 System.out.println("Hourly");

                 break;

             case "Commission":
                 System.out.println("Commission");
                 break;
             default:
                 break;
             }
        } catch (SQLException se) {
            System.out.println("Add emp : " + se);
        }

        return e;
    }

    public Employee GetEmployee(Employee e) {
        String sql = "SELECT * FROM employee WHERE id=?";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, e.get_empID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                e.set_name(rs.getString("name"));
            }
            if (e.get_name() == null) {
                System.out.println("User not found");
            }
        } catch (Exception se) {
            System.out.println(se);
        }
        return e;
    }

}