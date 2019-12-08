package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.DepartmentRepository;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private DepartmentService departmentService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).get();
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Iterable<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).get();
    }

    public Employee updateEmployeeManager(Long id, Employee employeeToUpdate) {
        if (employeeRepository.findById(id).isPresent()) {
            Employee originalEmployee = employeeRepository.findById(employeeToUpdate.getDepartmentNumber()).get();
            originalEmployee.setDepartmentName(employeeToUpdate.getDepartmentName());
            return employeeRepository.save(originalEmployee);
        }
        return null;
    }

    public Employee updateEmployeeDepartment(Long id, Long newDeptId) {
        Employee employee = getEmployee(id);
        Department department = departmentService.getDepartment(newDeptId);

        if (employee != null && department != null) {
            employee.setDepartmentNumber(department.getDepartmentId());
            return employeeRepository.save(employee);
        }
        return null;
    }


    public Employee updateEmployeeHireDate(Long id, String hireDate) {
        Employee employee = getEmployee(id);
        employee.setHireDate(hireDate);

        return !employee.getHireDate().equals(null) ? employeeRepository.save(employee) : null;
    }
}




