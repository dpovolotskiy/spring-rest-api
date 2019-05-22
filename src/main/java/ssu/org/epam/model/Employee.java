package ssu.org.epam.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;

public class Employee {
    private int Id;
    private String fullName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private Integer age;
    private String email;
    private int salary;

    private ArrayList<Project> projects;
    private Post post;
    private Cabinet numberOfCabinet;

    public Employee() {
    }

    public Employee(int id, String fullName, Date dateOfBirth, String email, int salary) {
        Id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.salary = salary;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setNumberOfCabinet(Cabinet numberOfCabinet) {
        this.numberOfCabinet = numberOfCabinet;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public int getId() {
        return Id;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public int getSalary() {
        return salary;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public Post getPost() {
        return post;
    }

    public Cabinet getNumberOfCabinet() {
        return numberOfCabinet;
    }
}
