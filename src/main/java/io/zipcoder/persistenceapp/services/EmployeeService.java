package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.ResourceNotFoundException;
import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentService departmentService;
    private Employee employee;
    private Department department;
    private Employee manager;


    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    public Iterable<Employee> findAllEmployees()    {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


    public Employee updateEmployeeDepartment(Long id, Long newDeptId) {
        employee = findEmployeeById(id);
        department = departmentService.getDepartment(newDeptId);
        employee.setDepartmentNumber(department.getDepartmentId());

        return employeeRepository.save(employee);
    }

    public Employee findEmployeeManager(Long employeeId) {
        employee = findEmployeeById(employeeId);
        manager = employee.getManager();
        return employeeRepository.findById(manager.getId()).get();
    }

    public Employee updateEmployeeManager(Long employeeId)  {
        manager = findEmployeeManager(employeeId);
        employee.setManager(manager);
        return employee != null && manager != null ?
                employeeRepository.save(employee) : null;


    }








    public Employee updateEmployeeManager(Long id, Long newManagerId) {
        Employee employee = findEmployeeById(id);
        employee.setManager(newManagerId);
        return employee.getManager() != null ? employeeRepository.save(employee) : null;
    }




    public Employee updateEmployeeHireDate(Long id, String hireDate) {
        Employee employee = findEmployeeById(id);
        employee.setHireDate(hireDate);

        return !employee.getHireDate().equals(null) ?
                employeeRepository.save(employee) : null;
    }


    public Iterable<Employee> getEmployeesByManager(Long mgrId) {
        return employeeRepository.findByManagerId(mgrId);
    }


    public Iterable<Employee> getEmployeeHierarchy(Long employeeId) {
        Employee employee = findEmployeeById(employeeId);
        List<Employee> managers = new ArrayList<>();

        while (employee.getManager() != null) {
            employee = employee.getManager();
            managers.add(employee);
        }
        return managers;
    }

//    public Iterable<Employee> getEmployeesWithNoAssignedManager()   {
//        return employeeRepository.findEmployeeByManagerIsNull();
//    }

    public Iterable<Employee> getEmployeesByDepartment(Long deptId) {
        return employeeRepository.findByDepartmentNumber(deptId);
    }


    public void verifyEmployee(Long employeeId) {
        if(employeeRepository.existsById(employeeId))   {
            throw new ResourceNotFoundException("Employee " + employeeId + " not found.");
        }
    }



}




