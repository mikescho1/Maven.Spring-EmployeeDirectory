package io.zipcoder.persistenceapp.repositories;

import io.zipcoder.persistenceapp.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findEmployeeByManagerId(Long mgrId);

    List<Employee> findEmployeeByManagerIsNull();

    List<Employee> findEmployeeByDepartmentNumber(Long deptId);
    }







}