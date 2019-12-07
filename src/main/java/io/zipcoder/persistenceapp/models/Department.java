package io.zipcoder.persistenceapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer departmentId;
    private String departmentName;
    private String departmentManager;

    public Department() {
    }

    public Department(Integer departmentId, String departmentName, Employee departmentEmployee) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }










    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentNumber) {
        this.departmentId = departmentNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

}
