package io.zipcoder.persistenceapp.models;

import javax.persistence.*;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;
    private String departmentName;
    @ManyToOne
    private Employee departmentManager;

    public Department() {
    }

    public Department(Long departmentId, String departmentName, Employee departmentEmployee) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
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
