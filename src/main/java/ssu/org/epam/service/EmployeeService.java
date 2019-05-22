package ssu.org.epam.service;

import ssu.org.epam.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {
    List<Employee> allEmployeeFIO() throws SQLException, ClassNotFoundException;
    List<Employee> allEmployeeFullInfo() throws SQLException, ClassNotFoundException;
    Employee employeeById(int id) throws SQLException, ClassNotFoundException;
    List<Employee> employeeByName(String name) throws SQLException, ClassNotFoundException;
    void addEmployee(Employee employee) throws SQLException;
    void addEmployeeToProjectById(int employeeId, int projectId) throws SQLException;
    void fireEmployeeFromProjectById(int employeeId, int projectId) throws SQLException;
    void increaseEmployeeSalary(int employeeId, int newSalary) throws SQLException;
    void changeEmployeeCabinet(int employeeId, int newCabinet) throws SQLException;
    void removeEmployee(int employeeId) throws SQLException;
}
