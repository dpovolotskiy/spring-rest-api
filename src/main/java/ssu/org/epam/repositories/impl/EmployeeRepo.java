package ssu.org.epam.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ssu.org.epam.exception.CustomBuisnesException;
import ssu.org.epam.mappers.Mapper;
import ssu.org.epam.model.Employee;
import ssu.org.epam.repositories.Repository;
import ssu.org.epam.service.ConnectionService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;


@org.springframework.stereotype.Repository
public class EmployeeRepo implements Repository<Employee> {
    @Autowired
    @Qualifier(value = "employeeMapper")
    private Mapper<Employee> mapper;

    @Autowired
    private ConnectionService connectionService;

    @Override
    public void findById(int id, Employee employee) throws SQLException {
        Connection connection = connectionService.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rsEmployee = stmt.executeQuery(String.format("select * from employee where id = %d", id));
        mapper.transformFromTableRecord(rsEmployee, employee);
        stmt.close();
    }

    @Override
    public void findByName(String name, List<Employee> employee) throws SQLException {
        Connection connection = connectionService.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rsEmployee = stmt.executeQuery(String.format("select * from employee where fullName = \"%s\"", name));
        mapper.findByName(rsEmployee, employee);
        stmt.close();
    }

    @Override
    public void findAllFIO(List<Employee> entity) throws ClassNotFoundException, SQLException {
        Connection connection = connectionService.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rsEmployee = stmt.executeQuery(String.format("select * from employee"));
        mapper.findAllFIO(rsEmployee, entity);
        stmt.close();
    }

    @Override
    public void findAllFullInfo(List<Employee> entity) throws SQLException {
        Connection connection = connectionService.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rsEmployee = stmt.executeQuery(String.format("select * from employee"));
        mapper.findAllFullInfo(rsEmployee, entity);
        stmt.close();
    }

    @Override
    public void addNewEmployee(Employee employee) throws SQLException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Connection connection = connectionService.getConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(String.format("INSERT INTO employee(fullName, birthDay, email, salary, cabinet, post) VALUES(\"%s\", \"%s\", \"%s\", %d, %d, %d)", employee.getFullName(), formatter.format(employee.getDateOfBirth()), employee.getEmail(), employee.getSalary(), employee.getNumberOfCabinet().getValue(), employee.getPost().getValue()));
        stmt.close();
    }

    @Override
    public void addEmployeeToProjectById(int employeeId, int projectId) throws SQLException {
        Connection connection = connectionService.getConnection();
        Statement stmt = connection.createStatement();
        try {
            stmt.executeUpdate(String.format("INSERT INTO employee_projects(projectId, employeeId) VALUES(%d, %d)", projectId, employeeId));
        } catch (SQLException e) {
            throw new CustomBuisnesException("ProjectID: " +projectId+ " or EmployeeID: " + employeeId + " doesn't exist");
        }
    }

    @Override
    public void fireEmployeeFromProjectById(int employeeId, int projectId) throws SQLException {
        Connection connection = connectionService.getConnection();
        Statement stmt = connection.createStatement();
        try {
            stmt.executeUpdate(String.format("DELETE FROM employee_projects WHERE projectId=%d and employeeId=%d", projectId, employeeId));
        } catch (SQLException e) {
            throw new CustomBuisnesException("ProjectID: " +projectId+ " or EmployeeID: " + employeeId + " doesn't exist");
        }
    }

    @Override
    public void increaseEmployeeSalaryById(int employeeId, int newSalary) throws SQLException {
        Connection connection = connectionService.getConnection();
        Statement stmt = connection.createStatement();
        try {
            stmt.executeUpdate(String.format("UPDATE employee SET salary=%d WHERE Id=%d", newSalary, employeeId));
        } catch (SQLException e) {
            throw new CustomBuisnesException("EmployeeID: " + employeeId + " doesn't exist");
        }
    }

    @Override
    public int getSalaryById(int employeeId) throws SQLException {
        int previousSalary = 0;
        Connection connection = connectionService.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs;
        try {
            rs = stmt.executeQuery(String.format("SELECT salary FROM employee WHERE Id=%d", employeeId));
        } catch (SQLException e) {
            throw new CustomBuisnesException("EmployeeID: " + employeeId + " doesn't exist");
        }
        while (rs.next()){
            previousSalary = rs.getInt("salary");
        }
        return previousSalary;
    }

    @Override
    public void changeEmployeeCabinetById(int employeeId, int newCabinet) throws SQLException {
        Connection connection = connectionService.getConnection();
        Statement stmt = connection.createStatement();
        try {
            stmt.executeUpdate(String.format("UPDATE employee SET cabinet=%d WHERE Id=%d", newCabinet, employeeId));
        } catch (SQLException e) {
            throw new CustomBuisnesException("Cabinet: " +newCabinet+ " or EmployeeID: " + employeeId + " doesn't exist");
        }
    }

    @Override
    public void removeEmployeeById(int employeeId) throws SQLException {
        Connection connection = connectionService.getConnection();
        Statement stmt = connection.createStatement();
        try {
            stmt.executeUpdate(String.format("DELETE FROM employee_projects WHERE employeeId=%d", employeeId));
            stmt.executeUpdate(String.format("DELETE FROM employee WHERE Id=%d", employeeId));
        } catch (SQLException e) {
            throw new CustomBuisnesException("EmployeeID: " + employeeId + " doesn't exist");
        }
    }
}
