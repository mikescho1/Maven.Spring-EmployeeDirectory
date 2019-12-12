package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.ResourceNotFoundException;
import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    public Department getDepartment(Long departmentId)  {
        return departmentRepository.findById(departmentId).get();
    }

    public Department create(Department department)   {
        return departmentRepository.save(department);
    }

    public Department setDepartmentManager(Long deptId, Employee newManager)  {
        Department department = getDepartment(deptId);
        newManager = new Employee();
        department.setDepartmentManager(newManager);

        return department != null && newManager != null ?
                departmentRepository.save(department) :
                null;
    }

    public Department changeDepartmentName(Long deptId, String newName) {
        Department department = getDepartment(deptId);
        newName = new String();
        department.setDepartmentName(newName);

        return department != null ?
                departmentRepository.save(department) :
                null;

    }

    public void verifyDepartment(Long deptId) {
        if(departmentRepository.existsById(deptId))   {
            throw new ResourceNotFoundException("Department " + deptId + " not found.");
        }
    }

}
