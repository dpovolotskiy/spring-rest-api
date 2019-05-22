package ssu.org.epam.mappers.impl;

import org.springframework.stereotype.Service;
import ssu.org.epam.exception.CustomBuisnesException;
import ssu.org.epam.mappers.Mapper;
import ssu.org.epam.model.Cabinet;
import ssu.org.epam.model.Employee;
import ssu.org.epam.model.Post;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class EmployeeMapper implements Mapper<Employee> {

    @Override
    public void transformFromTableRecord(ResultSet dbResponse, Employee employee) {
        boolean isNull = true;
        try {
            while (dbResponse.next()) {
                int Id = dbResponse.getInt("Id");
                String fullName = dbResponse.getString("fullName");
                Date dateOfBirth = dbResponse.getDate("birthDay");
                String email = dbResponse.getString("email");
                int salary = dbResponse.getInt("salary");
                int postId = dbResponse.getInt("post");
                int cabinetId = dbResponse.getInt("cabinet");
                employee.setId(Id);
                employee.setFullName(fullName);
                employee.setDateOfBirth(dateOfBirth);
                employee.setEmail(email);
                employee.setSalary(salary);
                for (Post p : Post.values()) {
                    if (p.getValue() == postId) {
                        employee.setPost(p);
                    }
                }
                for (Cabinet c : Cabinet.values()) {
                    if (c.getValue() == cabinetId) {
                        employee.setNumberOfCabinet(c);
                    }
                }
                isNull = false;
            }
        } catch (SQLException e) {
            throw new CustomBuisnesException("Specified id doesn't exist");
        }
        if (isNull){
            throw new CustomBuisnesException("Specified id doesn't exist");
        }
    }

    @Override
    public void findAllFIO(ResultSet dbResponse, List<Employee> employees) throws SQLException {
        while (dbResponse.next()) {
            Employee employee = new Employee();
            String fullName = dbResponse.getString("fullName");
            employee.setFullName(fullName);
            employees.add(employee);
        }
    }

    @Override
    public void findAllFullInfo(ResultSet dbResponse, List<Employee> entity) throws SQLException {
        while (dbResponse.next()) {
            Employee employee = new Employee();
            int Id = dbResponse.getInt("Id");
            String fullName = dbResponse.getString("fullName");
            Date dateOfBirth = dbResponse.getDate("birthDay");
            String email = dbResponse.getString("email");
            int salary = dbResponse.getInt("salary");
            int postId = dbResponse.getInt("post");
            int cabinetId = dbResponse.getInt("cabinet");
            employee.setId(Id);
            employee.setFullName(fullName);
            employee.setDateOfBirth(dateOfBirth);
            employee.setEmail(email);
            employee.setSalary(salary);
            for (Post p : Post.values()) {
                if (p.getValue() == postId) {
                    employee.setPost(p);
                }
            }
            for (Cabinet c : Cabinet.values()) {
                if (c.getValue() == cabinetId) {
                    employee.setNumberOfCabinet(c);
                }
            }
            Integer age = Period.between(dateOfBirth.toLocalDate(), LocalDate.now()).getYears();
            employee.setAge(age);
            entity.add(employee);

        }
    }

    @Override
    public void findByName(ResultSet dbResponse, List<Employee> entity) throws SQLException {
        boolean isNull = true;
        try {
            while (dbResponse.next()) {
                Employee employee = new Employee();
                int Id = dbResponse.getInt("Id");
                String fullName = dbResponse.getString("fullName");
                Date dateOfBirth = dbResponse.getDate("birthDay");
                String email = dbResponse.getString("email");
                int salary = dbResponse.getInt("salary");
                int postId = dbResponse.getInt("post");
                int cabinetId = dbResponse.getInt("cabinet");
                employee.setId(Id);
                employee.setFullName(fullName);
                employee.setDateOfBirth(dateOfBirth);
                employee.setEmail(email);
                employee.setSalary(salary);
                for (Post p : Post.values()) {
                    if (p.getValue() == postId) {
                        employee.setPost(p);
                    }
                }
                for (Cabinet c : Cabinet.values()) {
                    if (c.getValue() == cabinetId) {
                        employee.setNumberOfCabinet(c);
                    }
                }
                entity.add(employee);
                isNull = false;
            }
        } catch (SQLException e) {
            throw new CustomBuisnesException("Specified name doesn't exist");
        }
        if (isNull){
            throw new CustomBuisnesException("Specified name doesn't exist");
        }
    }
}
