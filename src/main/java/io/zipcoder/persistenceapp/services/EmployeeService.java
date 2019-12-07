package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee)   {
        return employeeRepository.save(employee);
    }

    public Iterable<Employee> findAllEmployees()    {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id)   {
        return employeeRepository.findById(id).get();
    }

    public Employee updateEmployeeDepartment(Long id, Employee employeeToUpdate)    {

        if(employeeRepository.findById(employeeToUpdate.getDepartmentNumber()).isPresent()) {
            Employee originalEmployee = employeeRepository.findById(employeeToUpdate.getDepartmentNumber()).get();
            originalEmployee.setDepartmentName(employeeToUpdate.getDepartmentName());
            return employeeRepository.save(originalEmployee);

        }
    }



}
