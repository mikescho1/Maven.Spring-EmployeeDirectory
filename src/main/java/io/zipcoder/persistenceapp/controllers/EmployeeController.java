package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    public EmployeeController() {
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> create(@RequestBody Employee employee)  {
        return null;
    }

    public ResponseEntity




}
