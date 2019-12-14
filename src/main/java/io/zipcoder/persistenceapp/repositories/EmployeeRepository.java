package io.zipcoder.persistenceapp.repositories;

import io.zipcoder.persistenceapp.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Iterable<Employee> findEmployeesByManager_Id(Long managerId);

    Iterable<Employee> findEmployeesByManagerIsNull(Long managerId);

    Iterable<Employee> findEmployeeByDepartmentNumber(Long departmentId);























}