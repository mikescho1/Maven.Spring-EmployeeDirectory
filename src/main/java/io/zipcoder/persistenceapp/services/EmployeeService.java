package io.zipcoder.persistenceapp.services;

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
        Employee employee = findEmployeeById(id);
        Department department = departmentService.getDepartmentById(newDeptId);
        employee.setDepartmentNumber(department.getDepartmentId());
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeManager(Long employeeId) {
        Employee employee = findEmployeeById(employeeId);
        Employee manager = employee.getManager();
        return employeeRepository.findById(manager.getId()).get();
    }

    public Employee updateEmployeeManager(Long employeeId, Long newManagerId)  {
        Employee employee = findEmployeeById(employeeId);
        Employee manager = findEmployeeManager(newManagerId);
        employee.setManager(manager);
        return employeeRepository.save(employee);
    }













    public Employee updateEmployeeHireDate(Long id, String hireDate) {
        Employee employee = findEmployeeById(id);
        employee.setHireDate(hireDate);

        return !employee.getHireDate().equals(null) ?
                employeeRepository.save(employee) : null;
    }


    public Iterable<Employee> getEmployeesByManager(Long mgrId) {
        return employeeRepository.findEmployeesByManager_Id(mgrId);
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

    public Iterable<Employee> getEmployeesWithNoManager(Long managerId)  {
      return  employeeRepository.findEmployeesByManagerIsNull(managerId);
    }

    public Iterable<Employee> getEmployeesByDepartment(Long departmentId)   {
        return employeeRepository.findEmployeeByDepartmentNumber(departmentId);
    }


    }











