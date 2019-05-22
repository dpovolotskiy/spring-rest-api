package ssu.org.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ssu.org.epam.model.Employee;
import ssu.org.epam.service.EmployeeService;

import java.sql.SQLException;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/employee/fio")
    public List<Employee> allEmployeeFIO() throws SQLException, ClassNotFoundException {
        return employeeService.allEmployeeFIO();
    }

    @GetMapping(value = "/employee/full")
    public List<Employee> allEmployeeFull() throws SQLException, ClassNotFoundException {
        return employeeService.allEmployeeFullInfo();
    }

    @GetMapping(value = "/employee/id")
    public Employee findEmployeeById(@RequestParam int id) throws SQLException, ClassNotFoundException {
        return employeeService.employeeById(id);
    }

    @GetMapping(value = "/employee/name")
    public List<Employee> findEmployeeByName(@RequestParam String name) throws SQLException, ClassNotFoundException {
        return employeeService.employeeByName(name);
    }

    @PostMapping(value = "/employee/add")
    public void addEmployee(@RequestBody Employee employee) throws SQLException {
        employeeService.addEmployee(employee);
    }

    @GetMapping(value = "/employee/add_project")
    public void addEmployeeToProject(@RequestParam int employeeId, int projectId) throws SQLException {
        employeeService.addEmployeeToProjectById(employeeId, projectId);
    }

    @GetMapping(value = "/employee/fire_project")
    public void fireEmployeeFromProject(@RequestParam int employeeId, int projectId) throws SQLException {
        employeeService.fireEmployeeFromProjectById(employeeId, projectId);
    }

    @GetMapping(value = "/employee/salary")
    public void inreaseEmployeeSalary(@RequestParam int employeeId, int newSalary) throws SQLException {
        employeeService.increaseEmployeeSalary(employeeId, newSalary);
    }

    @GetMapping(value = "/employee/cabinet")
    public void changeEmployeeCabinet(@RequestParam int employeeId, int newCabinet) throws SQLException {
        employeeService.changeEmployeeCabinet(employeeId, newCabinet);
    }

    @GetMapping(value = "/employee/remove")
    public void removeEmployee(@RequestParam int employeeId) throws SQLException {
        employeeService.removeEmployee(employeeId);
    }
}
