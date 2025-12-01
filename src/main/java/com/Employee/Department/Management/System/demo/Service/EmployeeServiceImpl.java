package com.Employee.Department.Management.System.demo.Service;


import com.Employee.Department.Management.System.demo.Dto.EmployeeDto;
import com.Employee.Department.Management.System.demo.entity.Department;
import com.Employee.Department.Management.System.demo.entity.Employee;
import com.Employee.Department.Management.System.demo.Repositories.DepartmentRepository;
import com.Employee.Department.Management.System.demo.Repositories.EmployeeRepository;
import com.Employee.Department.Management.System.demo.Service.EmployeeService;
import com.Employee.Department.Management.System.demo.Mapper.MapperUtil;
import com.Employee.Department.Management.System.demo.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto dto) {
        Employee employee = MapperUtil.toEmployeeEntity(dto);
        if(dto.getDepartmentId() != null){
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + dto.getDepartmentId()));

            employee.setDepartment(department);
        }

        Employee saved = employeeRepository.save(employee);
        return MapperUtil.toEmployeeDto(saved);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employeelist = employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();

        for(Employee e:employeelist) {

            EmployeeDto employeeDto = MapperUtil.toEmployeeDto(e);
            employeeDtoList.add(employeeDto);

        }
         return employeeDtoList;
    }

    @Override
    public List<EmployeeDto> getEmployeesByDepartment(Long departmentId) {
        List<Employee> employeelist=  employeeRepository.findByDepartmentId(departmentId);

        List<EmployeeDto> employeeDtoList =new ArrayList<>();

        for (Employee e :employeelist){
           EmployeeDto employeeDto =  MapperUtil.toEmployeeDto(e);
           employeeDtoList.add(employeeDto);

        }
        return employeeDtoList;
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

