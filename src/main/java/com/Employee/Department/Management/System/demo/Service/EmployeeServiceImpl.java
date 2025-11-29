package com.Employee.Department.Management.System.demo.Service;


import com.Employee.Department.Management.System.demo.Dto.EmployeeDto;
import com.Employee.Department.Management.System.demo.entity.Department;
import com.Employee.Department.Management.System.demo.entity.Employee;
import com.Employee.Department.Management.System.demo.exception.ResourceNotFoundException;
import com.Employee.Department.Management.System.demo.Repositories.DepartmentRepository;
import com.Employee.Department.Management.System.demo.Repositories.EmployeeRepository;
import com.Employee.Department.Management.System.demo.Service.EmployeeService;
import com.Employee.Department.Management.System.demo.Mapper.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public EmployeeDto createEmployee(Long departmentId, EmployeeDto dto) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + departmentId));

        Employee employee = MapperUtil.toEmployeeEntity(dto);
        employee.setDepartment(department);

        Employee saved = employeeRepository.save(employee);
        return MapperUtil.toEmployeeDto(saved);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(MapperUtil::toEmployeeDto)
                .toList();
    }

    @Override
    public List<EmployeeDto> getEmployeesByDepartment(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId)
                .stream()
                .map(MapperUtil::toEmployeeDto)
                .toList();
    }

    @Override
    public EmployeeDto transferEmployee(Long empId, Long newDepartmentId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + empId));

        Department newDept = departmentRepository.findById(newDepartmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + newDepartmentId));

        employee.setDepartment(newDept);
        Employee updated = employeeRepository.save(employee);

        return MapperUtil.toEmployeeDto(updated);
    }
}

