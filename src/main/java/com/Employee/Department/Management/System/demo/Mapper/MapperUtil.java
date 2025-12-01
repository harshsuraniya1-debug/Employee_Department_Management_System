package com.Employee.Department.Management.System.demo.Mapper;


import com.Employee.Department.Management.System.demo.Dto.DepartmentDto;
import com.Employee.Department.Management.System.demo.Dto.EmployeeDto;
import com.Employee.Department.Management.System.demo.entity.Department;
import com.Employee.Department.Management.System.demo.entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MapperUtil {

    // 🔹 DepartmentDto → Department
    public static Department toDepartmentEntity(DepartmentDto dto) {
        if (dto == null) return null;

        Department department = new Department();
        department.setName(dto.getName());
        department.setDescription(dto.getDescription());

        return department;
    }

    // 🔹 Department → DepartmentDto
    public static DepartmentDto toDepartmentDto(Department department) {
        if (department == null) return null;

        DepartmentDto dto = new DepartmentDto();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setDescription(department.getDescription());
        dto.setEmployeeIds(
                department.getEmployeeslist().stream().map(Employee::getId).toList()
        );
        return dto;
    }

    // 🔹 Employee → EmployeeDto (with Department)
    public static EmployeeDto toEmployeeDto(Employee employee) {
        if (employee == null) return null;

        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setSalary(employee.getSalary());
        if(employee.getDepartment() != null){
            dto.setDepartmentId(employee.getDepartment().getId());
        }
        return dto;
    }


    // 🔹 EmployeeDto → Employee
    public static Employee toEmployeeEntity(EmployeeDto dto) {
        if (dto == null) return null;

        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setSalary(dto.getSalary());
        return employee;
    }
}

