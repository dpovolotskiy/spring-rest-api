package ssu.org.epam.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ssu.org.epam.mappers.Mapper;
import ssu.org.epam.model.Employee;
import ssu.org.epam.repositories.Repository;
import ssu.org.epam.service.ConnectionService;

import java.sql.*;
import java.util.List;

@Service
public class ProjectRepo implements Repository<Employee> {

    @Autowired
    @Qualifier(value = "projectMapper")
    private Mapper<Employee> mapper;

    @Autowired
    private ConnectionService connectionService;

    @Override
    public void findById(int id, Employee entity) throws SQLException {
        Connection connection = connectionService.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rsProject = stmt.executeQuery(String.format("select * from employee_projects where employeeId = %d", id));
        mapper.transformFromTableRecord(rsProject, entity);
        stmt.close();
    }

    @Override
    public void findByName(String name, List<Employee> entity) throws ClassNotFoundException, SQLException {
    }

    @Override
    public void findAllFIO(List<Employee> entity) throws ClassNotFoundException, SQLException {
    }

    @Override
    public void findAllFullInfo(List<Employee> entity) throws ClassNotFoundException, SQLException {
    }

    @Override
    public void addNewEmployee(Employee employee) throws SQLException {
    }

    @Override
    public void addEmployeeToProjectById(int employeeId, int projectId) throws SQLException {
    }

    @Override
    public void fireEmployeeFromProjectById(int employeeId, int projectId) throws SQLException {
    }

    @Override
    public void increaseEmployeeSalaryById(int employeeId, int newSalary) throws SQLException {
    }

    @Override
    public int getSalaryById(int employeeId) throws SQLException {
        return 0;
    }

    @Override
    public void changeEmployeeCabinetById(int employeeId, int newCabinet) throws SQLException {
    }

    @Override
    public void removeEmployeeById(int employeeId) throws SQLException {
    }
}
