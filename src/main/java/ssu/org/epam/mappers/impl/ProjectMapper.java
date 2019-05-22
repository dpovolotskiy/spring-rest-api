package ssu.org.epam.mappers.impl;

import org.springframework.stereotype.Service;
import ssu.org.epam.mappers.Mapper;
import ssu.org.epam.model.Employee;
import ssu.org.epam.model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectMapper implements Mapper<Employee> {

    @Override
    public void transformFromTableRecord(ResultSet dbResponse, Employee employee) throws SQLException {
        ArrayList<Project> projects = new ArrayList<>();
        while (dbResponse.next()){
            int projectId = dbResponse.getInt("projectId");
            for(Project p: Project.values()) {
                if(p.getValue() == projectId) {
                    projects.add(p);
                }
            }
        }
        employee.setProjects(projects);
    }

    @Override
    public void findAllFIO(ResultSet dbResponse, List<Employee> entity) throws SQLException {
    }

    @Override
    public void findAllFullInfo(ResultSet dbResponse, List<Employee> entity) throws SQLException {
    }

    @Override
    public void findByName(ResultSet dbResponse, List<Employee> entity) throws SQLException {

    }
}
