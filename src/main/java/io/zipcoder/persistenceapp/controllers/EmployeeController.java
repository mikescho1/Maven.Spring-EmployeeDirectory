package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.ResourceNotFoundException;
import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import io.zipcoder.persistenceapp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;
    private DepartmentController departmentController;

    @Autowired
    public EmployeeController(EmployeeService employeeService)  {
        this.employeeService = employeeService;
    }

    @GetMapping("employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id)    {
        try {
            return new ResponseEntity<>(employeeService.findEmployeeById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException ex)  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("employee")
    public ResponseEntity<Iterable<Employee>> findAllEmployees() {
        return new ResponseEntity(employeeService.findAllEmployees(), HttpStatus.OK);
    }

    @PostMapping("employee")
    public ResponseEntity<Employee> create(@RequestBody Employee employee)  {
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployeeDepartment(@PathVariable Long employeeId, @RequestBody Long newDeptId)   {
        try {
            verifyEmployee(employeeId);
            departmentController.verifyDepartment(newDeptId);

            employeeService.updateEmployeeDepartment(employeeId, newDeptId);
            return new ResponseEntity(HttpStatus.OK);
    }   catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity findEmployeeManager(Long employeeId)  {
        try {
            verifyEmployee(employeeId);
            Long managerId = employeeService.findEmployeeManager(employeeId).getId();
            verifyEmployee(managerId);
            return new ResponseEntity(employeeService.findEmployeeManager(employeeId), HttpStatus.OK);
        }   catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }   return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/employee/{id}")
    public ResponseEntity updadateEmployeeManager(@PathVariable Long employeeId, @RequestBody Long newManagerId)    {
        return new ResponseEntity(employeeService.updateEmployeeManager(employeeId, newManagerId), HttpStatus.OK);
    }

    public void verifyEmployee(Long employeeId) {
        if(employeeRepository.existsById(employeeId))   {
            throw new ResourceNotFoundException("Employee " + employeeId + " not found.");
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<Iterable<Employee>> getEmployeesByManager(Long managerId)  {
        try {
            verifyEmployee(managerId);
            return new ResponseEntity<>(employeeService.getEmployeesByManager(managerId), HttpStatus.OK);
        }   catch (ResourceNotFoundException ex) {

        }   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/employees/no_manager")
    public ResponseEntity<Iterable<Employee>> getEmployeesWithNoManager(Long managerId)   {
        try {
            verifyEmployee(managerId);
            return new ResponseEntity<>(employeeService.getEmployeesWithNoManager(managerId), HttpStatus.OK);
        }   catch (ResourceNotFoundException ex)    {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("employees/{departmentId}/departmentEmployees")
    public ResponseEntity<Iterable<Employee>> getEmployeesByDepartment(Long departmentId)   {
        try {
            departmentController.verifyDepartment(departmentId);
            return new ResponseEntity<>(employeeService.getEmployeesByDepartment(departmentId), HttpStatus.OK);
        }   catch (ResourceNotFoundException ex)    {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}



