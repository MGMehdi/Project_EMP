package be.heh.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.heh.employee.Employee;
import be.heh.employee.classification.Commission;
import be.heh.employee.classification.Hourly;
import be.heh.employee.classification.Salaried;
import be.heh.employee.classification.TimeCard;
import be.heh.employee.method.Deposit;
import be.heh.employee.method.Mailed;
import be.heh.employee.method.PayMaster;

/**
 * DatabaseHelper
 */
public class DatabaseHelper {
    private String url = "jdbc:postgresql://192.168.0.211/postgres";
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

    /**************************************************************************************************************************************************************************************************/

    public Employee AddEmployee(Employee e) {

        String sql;
        sql = "INSERT INTO public.employee (name) VALUES (?)";
        try {
            /** ADD EMPLOYEE **/
            PreparedStatement ps = this.connection.prepareStatement(sql, new String[] { "id" });
            ps.setString(1, e.get_name());
            int empId = ps.executeUpdate();
            if (empId > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        e.set_empID(rs.getInt("id"));
                        System.out.println(e.get_empID());
                    }
                } catch (SQLException se) {
                    System.out.println(se);
                }
            }
            AddInfo(e);

        } catch (SQLException se) {
            System.out.println("Add emp : " + se);
        }

        return e;
    }

    private Employee AddInfo(Employee e) {
        String sql;
        switch (e.get_Iclassification().getClass().getSimpleName()) {
        case "Salaried":
            sql = "INSERT INTO salaried (id, salary) VALUES ((SELECT id FROM employee WHERE id=?), ?)";
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                ps.setInt(1, e.get_empID());
                ps.setDouble(2, e.get_salary());
                ps.executeUpdate();

            } catch (Exception se) {
                System.out.println(se);
            }
            break;

        case "Hourly":
            sql = "INSERT INTO hourly (id, hourly_rate) VALUES ((SELECT id FROM employee WHERE id=?), ?)";
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                ps.setInt(1, e.get_empID());
                ps.setDouble(2, e.get_hourlyRate());
                ps.executeUpdate();
            } catch (Exception se) {
                System.out.println(se);
            }
            break;

        case "Commission":
            sql = "INSERT INTO commission (id, salary, commission_rate) VALUES ((SELECT id FROM employee WHERE id=?), ?, ?)";
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                ps.setInt(1, e.get_empID());
                ps.setDouble(2, e.get_salary());
                ps.setDouble(3, e.get_commissionRate());
                ps.executeUpdate();
            } catch (Exception se) {
                System.out.println(se);
            }
            break;
        default:
            break;
        }

        /* ADD METHOD */
        switch (e.get_Imethod().getClass().getSimpleName()) {
        case "Deposit":
            sql = "INSERT INTO deposit (id, account) VALUES ((SELECT id FROM employee WHERE id=?), ?)";
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                ps.setInt(1, e.get_empID());
                ps.setString(2, e.get_account());
                ps.executeUpdate();
            } catch (Exception se) {
                System.out.println(se);
            }
            break;
        case "Mailed":
            sql = "INSERT INTO mailed (id, address) VALUES ((SELECT id FROM employee WHERE id=?), ?)";
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                ps.setInt(1, e.get_empID());
                ps.setString(2, e.get_address());
                ps.executeUpdate();
            } catch (Exception se) {
                System.out.println(se);
            }
            break;
        case "PayMaster":
            sql = "INSERT INTO paymaster (id) VALUES ((SELECT id FROM employee WHERE id=?))";
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                ps.setInt(1, e.get_empID());
                ps.executeUpdate();
            } catch (Exception se) {
                System.out.println(se);
            }
            break;
        default:
            break;
        }
        return e;
    }

    /************************************************************************************************************************************************************************************************************************************************************************/

    public Employee GetEmployee(Employee e) {
        String sql = "SELECT * FROM employee WHERE name = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setString(1, e.get_name());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                e.set_empID(rs.getInt("id"));
                GetInfo(e);
            }
            if (e.get_empID() == 0) {
                System.out.println("User not found");
                return null;
            }

        } catch (SQLException se) {
            System.out.println(se);
        }
        return e;
    }

    public ArrayList GetAllEmployee(ArrayList employees) {
        Employee e = null;
        String sql = "SELECT * FROM employee";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                e = new Employee(rs.getInt("id"), rs.getString("name"));
                employees.add(GetInfo(e));
            }
        } catch (SQLException sq) {
            System.out.println(sq);
        }

        return employees;
    }

    private Employee GetInfo(Employee e) {
        // SALARIED
        String sql = "SELECT * FROM salaried WHERE id = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, e.get_empID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                e.set_salary(rs.getInt("salary"));
                e.set_Iclassification(new Salaried(e.get_salary()));
                e.set_classification(e.get_Iclassification().getClass().getSimpleName());
            }
            if (e.get_salary() == 0) {
                System.out.println("Not a salaried");
            }
        } catch (SQLException se) {
            System.out.println(se);
        }
        // COMMISSION
        sql = "SELECT * FROM commission WHERE id = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, e.get_empID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                e.set_salary(rs.getDouble("salary"));
                e.set_commissionRate(rs.getDouble("commission_rate"));
                e.set_Iclassification(new Commission(e.get_salary()));
                e.set_classification(e.get_Iclassification().getClass().getSimpleName());
            }
            if (e.get_salary() == 0 && e.get_commissionRate() == 0) {
                System.out.println("Not a commission");
            }
        } catch (SQLException se) {
            System.out.println(se);
        }
        // HOURLY
        sql = "SELECT * FROM hourly WHERE id = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, e.get_empID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                e.set_hourlyRate(rs.getDouble("hourly_rate"));
                e.set_Iclassification(new Hourly(e.get_hourlyRate(), e.get_hourList()));
                e.set_classification(e.get_Iclassification().getClass().getSimpleName());
            }
            if (e.get_hourlyRate() == 0) {
                System.out.println("Not a hourly");
            }
        } catch (SQLException se) {
            System.out.println(se);
        }
        // Mailed
        sql = "SELECT * FROM mailed WHERE id = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, e.get_empID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                e.set_address(rs.getString("address"));
                e.set_Imethod(new Mailed());
                e.set_method(e.get_Imethod().getClass().getSimpleName());
            }
            if (e.get_hourlyRate() == 0 && e.get_hour() == 0) {
                System.out.println("Not a mailed");
            }
        } catch (SQLException se) {
            System.out.println(se);
        }
        // Deposit
        sql = "SELECT * FROM deposit WHERE id = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, e.get_empID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                e.set_account(rs.getString("account"));
                e.set_Imethod(new Deposit());
                e.set_method(e.get_Imethod().getClass().getSimpleName());
            }
            if (e.get_hourlyRate() == 0 && e.get_hour() == 0) {
                System.out.println("Not a deposit");
            }
        } catch (SQLException se) {
            System.out.println(se);
        }
        // Paymaster
        sql = "SELECT * FROM paymaster WHERE id = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, e.get_empID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                e.set_address(rs.getString("address"));
                e.set_Imethod(new PayMaster());
                e.set_method(e.get_Imethod().getClass().getSimpleName());
            }
            if (e.get_hourlyRate() == 0 && e.get_hour() == 0) {
                System.out.println("Not a paymaster");
            }
        } catch (SQLException se) {
            System.out.println(se);
        }
        return e;
    }

    public void DeleteEmployee(Employee e) {
        String sql;
        sql = "DELETE FROM employee where id = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, e.get_empID());
            DeleteDetails(e);
            ps.executeUpdate();
        } catch (SQLException se) {
            System.out.println(se);
        }

    }

    private void DeleteDetails(Employee e) {
        String sql = "DELETE FROM hourly where id IN (SELECT ? FROM employee)";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, e.get_empID());
            ps.executeUpdate();
        } catch (SQLException se) {
            System.out.println(se);
        }
        sql = "DELETE FROM salaried where id IN (SELECT ? FROM employee)";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, e.get_empID());
            ps.executeUpdate();
        } catch (SQLException se) {
            System.out.println(se);
        }

        sql = "DELETE FROM commission where id IN (SELECT ? FROM employee)";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, e.get_empID());
            ps.executeUpdate();
        } catch (SQLException se) {
            System.out.println(se);
        }

        sql = "DELETE FROM mailed where id IN (SELECT ? FROM employee)";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, e.get_empID());
            ps.executeUpdate();
        } catch (SQLException se) {
            System.out.println(se);
        }

        sql = "DELETE FROM deposit where id IN (SELECT ? FROM employee)";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, e.get_empID());
            ps.executeUpdate();
        } catch (SQLException se) {
            System.out.println(se);
        }

        sql = "DELETE FROM paymaster where id IN (SELECT ? FROM employee)";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, e.get_empID());
            ps.executeUpdate();
        } catch (SQLException se) {
            System.out.println(se);
        }
    }

    /********************************************************************************************************************************************************************************************/

    public Employee UpdateEmployee(Employee e) {
        String sql = "UPDATE employee SET name = ? WHERE id = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setString(1, e.get_name());
            ps.setInt(2, e.get_empID());
            ps.executeUpdate();
        } catch (SQLException se) {
            System.out.println(se);
        }

        switch (e.get_Iclassification().getClass().getSimpleName()) {
        case "Salaried":
            sql = "UPDATE salaried SET salary = ? WHERE id = ?";
            try (PreparedStatement ps = this.connection.prepareStatement(sql, new String[] { "id" })) {
                ps.setDouble(1, e.get_salary());
                ps.setInt(2, e.get_empID());
                e.set_Iclassification(new Salaried(e.get_salary()));
                int id = ps.executeUpdate();
                if (id == 0) {
                    DeleteDetails(e);
                    AddInfo(e);
                }
            } catch (SQLException se) {
                System.out.println(se);
            }
            break;
        case "Commission":
            sql = "UPDATE commission SET salary = ?, commission_rate = ? WHERE id = ?";
            System.out.println("CACA");
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                ps.setDouble(1, e.get_salary());
                ps.setDouble(2, e.get_commissionRate());
                ps.setInt(3, e.get_empID());
                e.set_Iclassification(new Commission(e.get_salary()));
                ps.executeUpdate();
                int id = ps.executeUpdate();
                if (id == 0) {
                    DeleteDetails(e);
                    AddInfo(e);
                }
            } catch (SQLException se) {
                System.out.println(se);
            }
            break;
        case "Hourly":
            sql = "UPDATE hourly SET hourly_rate = ? WHERE id = ?";
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                ps.setDouble(1, e.get_hourlyRate());
                ps.setInt(2, e.get_empID());
                e.set_Iclassification(new Hourly(e.get_hourlyRate()));
                int id = ps.executeUpdate();
                if (id == 0) {
                    DeleteDetails(e);
                    AddInfo(e);
                }
            } catch (SQLException se) {
                System.out.println(se);
            }
            break;
        default:
            break;
        }

        switch (e.get_Imethod().getClass().getSimpleName()) {
        case "Deposit":
            sql = "UPDATE deposit SET account = ? WHERE id = ?";
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                ps.setString(1, e.get_account());
                ps.setInt(2, e.get_empID());
                e.set_Imethod(new Deposit());
                int id = ps.executeUpdate();
                if (id == 0) {
                    DeleteDetails(e);
                    AddInfo(e);
                }
            } catch (SQLException se) {
                System.out.println(se);
            }
            break;
        case "Mailed":
            sql = "UPDATE mailed SET address = ? WHERE id = ?";
            try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
                ps.setString(1, e.get_address());
                ps.setInt(2, e.get_empID());
                e.set_Imethod(new Mailed());
                int id = ps.executeUpdate();
                if (id == 0) {
                    DeleteDetails(e);
                    AddInfo(e);
                }
            } catch (SQLException se) {
                System.out.println(se);
            }
            break;
        case "PayMaster":
            e.set_Imethod(new PayMaster());
            DeleteDetails(e);
            AddInfo(e);
            break;
        default:
            break;
        }

        return e;
    }

    public void AddTimeCard(double hours, Employee e) {
        String sql = "INSERT INTO timecard(id, hours) VALUES (?, ?)";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, e.get_empID());
            ps.setDouble(2, hours);
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public Employee GetTimeCard(Employee e) {
        String sql = "SELECT * FROM timecard WHERE id=?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, e.get_empID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                e.addHours(rs.getDouble("hours"));
            }
            return e;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return e;
    }

}