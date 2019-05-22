package ssu.org.epam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ssu.org.epam.exception.CustomBuisnesException;
import ssu.org.epam.model.Cabinet;
import ssu.org.epam.model.Employee;
import ssu.org.epam.model.Post;
import ssu.org.epam.repositories.Repository;
import ssu.org.epam.service.EmployeeService;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    @Qualifier(value = "employeeRepo")
    private Repository<Employee> employeeRepository;

    @Autowired
    @Qualifier(value = "projectRepo")
    private Repository<Employee> projectRepository;

    @Override
    public List<Employee> allEmployeeFIO() throws SQLException, ClassNotFoundException {
        List<Employee> allEmployee = new ArrayList<>();
        employeeRepository.findAllFIO(allEmployee);
        return allEmployee;
    }

    @Override
    public List<Employee> allEmployeeFullInfo() throws SQLException, ClassNotFoundException {
        List<Employee> allEmployee = new ArrayList<>();
        employeeRepository.findAllFullInfo(allEmployee);
        for (Employee e: allEmployee) {
            int id = e.getId();
            projectRepository.findById(id, e);
        }
        return allEmployee;
    }

    @Override
    public Employee employeeById(int id) throws SQLException, ClassNotFoundException {
        Employee employee = new Employee();
        employeeRepository.findById(id, employee);
        projectRepository.findById(id, employee);
        return employee;
    }

    @Override
    public List<Employee> employeeByName(String name) throws SQLException, ClassNotFoundException {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findByName(name, employees);
        for (Employee e: employees) {
            int id = e.getId();
            projectRepository.findById(id, e);
        }
        return employees;
    }

    @Override
    public void addEmployee(Employee employee) throws SQLException {
        validateEmployee(employee);
        employeeRepository.addNewEmployee(employee);
    }

    @Override
    public void addEmployeeToProjectById(int employeeId, int projectId) throws SQLException {
        employeeRepository.addEmployeeToProjectById(employeeId, projectId);
    }

    @Override
    public void fireEmployeeFromProjectById(int employeeId, int projectId) throws SQLException {
        employeeRepository.fireEmployeeFromProjectById(employeeId, projectId);
    }

    @Override
    public void increaseEmployeeSalary(int employeeId, int newSalary) throws SQLException {
        int previousSalary = employeeRepository.getSalaryById(employeeId);
        if (previousSalary >= newSalary) {
            throw new CustomBuisnesException("Valid only increase of salary!");
        }
        employeeRepository.increaseEmployeeSalaryById(employeeId, newSalary);
    }

    @Override
    public void changeEmployeeCabinet(int employeeId, int newCabinet) throws SQLException {
        employeeRepository.changeEmployeeCabinetById(employeeId, newCabinet);
    }

    @Override
    public void removeEmployee(int employeeId) throws SQLException {
        employeeRepository.removeEmployeeById(employeeId);
    }

    private void validateEmployee(Employee employee) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String check_date = sdf.format(employee.getDateOfBirth());
        try {
            sdf.setLenient(false);
            sdf.parse(check_date);
        } catch (ParseException e) {
            throw new CustomBuisnesException("Birthdate is not correct!");
        }
        Pattern rfc2822 = Pattern.compile(
                "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
        );
        if (!rfc2822.matcher(employee.getEmail()).matches()) {
            throw new CustomBuisnesException("Email is not correct!");
        }
        if (employee.getSalary() < 0) {
            throw new CustomBuisnesException("Salary is not correct!");
        }
        boolean isPost = false;
        for(Post p: Post.values()) {
            if(p.getValue() == employee.getPost().getValue()) {
                isPost = true;
            }
        }
        if (!isPost) {
            throw new CustomBuisnesException("Post is not correct!");
        }
        boolean isCabinet = false;
        for(Cabinet c: Cabinet.values()) {
            if(c.getValue() == employee.getNumberOfCabinet().getValue()) {
                isCabinet = true;
            }
        }
        if (!isCabinet) {
            throw new CustomBuisnesException("Number of cabinet is not correct!");
        }
    }
}
