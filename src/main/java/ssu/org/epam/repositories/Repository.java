package ssu.org.epam.repositories;

import ssu.org.epam.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    void findAllFIO(List<T> entity) throws ClassNotFoundException, SQLException;
    void findAllFullInfo(List<T> entity) throws ClassNotFoundException, SQLException;
    void findById(int id, T entity) throws ClassNotFoundException, SQLException;
    void findByName(String name, List<T> entity) throws ClassNotFoundException, SQLException;
    void addNewEmployee(Employee employee) throws SQLException;
    void addEmployeeToProjectById(int employeeId, int projectId) throws SQLException;
    void fireEmployeeFromProjectById(int employeeId, int projectId) throws SQLException;
    void increaseEmployeeSalaryById(int employeeId, int newSalary) throws SQLException;
    void changeEmployeeCabinetById(int employeeId, int newCabinet) throws SQLException;
    int getSalaryById(int employeeId) throws SQLException;
    void removeEmployeeById(int employeeId) throws SQLException;
}
