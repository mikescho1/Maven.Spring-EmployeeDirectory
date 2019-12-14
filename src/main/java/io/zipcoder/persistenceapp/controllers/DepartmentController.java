package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.ResourceNotFoundException;
import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.repositories.DepartmentRepository;
import io.zipcoder.persistenceapp.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController {

    private DepartmentService departmentService;
    private DepartmentRepository departmentRepository;
    private EmployeeController employeeController;

    @Autowired
    public DepartmentController() {
    }

    @GetMapping("/department")
    public ResponseEntity<Department> getDepartmentById(Long deptId)    {
        try {
            return new ResponseEntity<>(departmentService.getDepartmentById(deptId), HttpStatus.OK);
        }   catch (ResourceNotFoundException ex)    {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/department")
    public ResponseEntity<Department> create(@RequestBody Department department)    {
        return new ResponseEntity<>(departmentService.create(department), HttpStatus.CREATED);
    }


    @PutMapping("/department/{id}")
    public ResponseEntity<Department> setDepartmentManager(Long deptId, Long newManagerId) {
        try {
            verifyDepartment(deptId);
            employeeController.verifyEmployee(newManagerId);
            departmentService.setDepartmentManager(deptId, newManagerId);
            return new ResponseEntity<>(HttpStatus.OK);
        }   catch (ResourceNotFoundException ex)    {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/department/{id}/name")
    public ResponseEntity<Department> changeDepartmentName(Long deptId, String newName)    {
        try {
            verifyDepartment(deptId);
            departmentService.changeDepartmentName(deptId, newName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException ex)  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }



    }










    public void verifyDepartment(Long deptId) {
        if(departmentRepository.existsById(deptId))   {
            throw new ResourceNotFoundException("Department " + deptId + " not found.");
        }
    }


}
