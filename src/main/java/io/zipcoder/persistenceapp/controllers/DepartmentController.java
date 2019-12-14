package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.ResourceNotFoundException;
import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.DepartmentRepository;
import io.zipcoder.persistenceapp.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    private DepartmentService departmentService;
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentController() {
    }

    @PostMapping("/department")
    public ResponseEntity<Department> create(@RequestBody Department department)    {
        return new ResponseEntity<>(departmentService.create(department), HttpStatus.CREATED);
    }

    @PutMapping("/department/{id}")
    public ResponseEntity<Department> setDepartmentManager(Long deptId, Employee newManager) {
        return new ResponseEntity<>(departmentService.setDepartmentManager(deptId, newManager), HttpStatus.OK);
    }

    public void verifyDepartment(Long deptId) {
        if(departmentRepository.existsById(deptId))   {
            throw new ResourceNotFoundException("Department " + deptId + " not found.");
        }
    }


}
