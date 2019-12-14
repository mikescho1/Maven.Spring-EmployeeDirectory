package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;
    private EmployeeService employeeService;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    public Department getDepartmentById(Long departmentId)  {
        return departmentRepository.findById(departmentId).get();
    }

    public Department create(Department department)   {
        return departmentRepository.save(department);
    }

    public Department setDepartmentManager(Long deptId, Long newManagerId) {
        Department department = getDepartmentById(deptId);
        Employee newManager = employeeService.findEmployeeById(newManagerId);
        department.setDepartmentManager(newManager);
        return departmentRepository.save(department);
    }

    public Department changeDepartmentName(Long deptId, String newName) {
        Department department = getDepartmentById(deptId);
        department.setDepartmentName(newName);
        return departmentRepository.save(department);

    }



}
