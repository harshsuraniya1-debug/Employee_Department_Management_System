package com.Employee.Department.Management.System.demo.Repositories;

import com.Employee.Department.Management.System.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findByDepartmentId(Long departmentId);

}
