package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController() {
    }

    @PostMapping("employee")
    public ResponseEntity<Employee> create(@RequestBody Employee employee)  {
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity updateEmployeeDepartment(@PathVariable Long id, @RequestBody Long newDeptId)   {
        return new ResponseEntity<>(employeeService.updateEmployeeDepartment(id, newDeptId), HttpStatus.OK);
    }
    @PutMapping("/employee/{id}")
    public ResponseEntity updadateEmployeeManager(@PathVariable Long id, @RequestBody Long newManagerId)    {
        return new ResponseEntity(employeeService.updateEmployeeManager(id, newManagerId), HttpStatus.OK);
    }




}
