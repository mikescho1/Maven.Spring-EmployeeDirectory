package io.zipcoder.persistenceapp.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;
    private String departmentName;
    @ManyToOne
    private Employee departmentManager;

    private
    @Transient
    @ManyToMany
    Set<Employee> deptEmployeeList = new HashSet<>();

    public Department() {
    }

    public Department(Long departmentId, String departmentName, Employee departmentEmployee) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public void addEmployeeToDepartment(Employee employee)  {
        deptEmployeeList.add(employee);
    }

    public void clearEmployees()  {
        deptEmployeeList.clear();
    }

    public void removeEmployeeFromDepartment(Employee employee)   {
        deptEmployeeList.remove(employee);
    }

    public void addGroupOfEmployeesToDepartment(Iterable<Employee> employees)   {
        for (Employee e : employees)    {
            deptEmployeeList.add(e);
        }
    }



    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentNumber) {
        this.departmentId = departmentNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Employee getDepartmentManager() {
        return departmentManager;
    }

    public void setDepartmentManager(Employee departmentManager) {
        this.departmentManager = departmentManager;
    }
}
