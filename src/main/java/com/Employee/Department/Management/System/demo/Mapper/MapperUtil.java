package com.Employee.Department.Management.System.demo.Mapper;


import com.Employee.Department.Management.System.demo.Dto.DepartmentDto;
import com.Employee.Department.Management.System.demo.Dto.EmployeeDto;
import com.Employee.Department.Management.System.demo.entity.Department;
import com.Employee.Department.Management.System.demo.entity.Employee;

import java.util.stream.Collectors;

public class MapperUtil {

    // 🔹 Department → DepartmentDto
    public static DepartmentDto toDepartmentDto(Department department) {
        if (department == null) return null;

        DepartmentDto dto = new DepartmentDto();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setLocation(department.getLocation());

        // Convert employees list only if present
        if (department.getEmployees() != null) {
            dto.setEmployees(department.getEmployees()
                    .stream()
                    .map(MapperUtil::toEmployeeDtoWithoutDepartment) // prevent infinite loop
                    .collect(Collectors.toList())
            );
        }
        return dto;
    }

    // 🔹 DepartmentDto → Department
    public static Department toDepartmentEntity(DepartmentDto dto) {
        if (dto == null) return null;

        Department department = new Department();
        department.setId(dto.getId());
        department.setName(dto.getName());
        department.setLocation(dto.getLocation());
        return department;
    }

    // 🔹 Employee → EmployeeDto (with Department)
    public static EmployeeDto toEmployeeDto(Employee employee) {
        if (employee == null) return null;

        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setAge(employee.getAge());
        dto.setSalary(employee.getSalary());
        dto.setDepartment(toDepartmentDto(employee.getDepartment())); // includes department
        return dto;
    }

    // 🔹 Employee → EmployeeDto (WITHOUT Department)
    public static EmployeeDto toEmployeeDtoWithoutDepartment(Employee employee) {
        if (employee == null) return null;

        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setAge(employee.getAge());
        dto.setSalary(employee.getSalary());
        return dto;
    }

    // 🔹 EmployeeDto → Employee
    public static Employee toEmployeeEntity(EmployeeDto dto) {
        if (dto == null) return null;

        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setAge(dto.getAge());
        employee.setSalary(dto.getSalary());
        return employee;
    }
}

