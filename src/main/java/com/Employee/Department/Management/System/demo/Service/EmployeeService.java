package com.Employee.Department.Management.System.demo.Service;


import com.Employee.Department.Management.System.demo.Dto.EmployeeDto;
import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(Long departmentId, EmployeeDto dto);
    List<EmployeeDto> getAllEmployees();
    List<EmployeeDto> getEmployeesByDepartment(Long departmentId);
    EmployeeDto transferEmployee(Long empId, Long newDepartmentId);
}
