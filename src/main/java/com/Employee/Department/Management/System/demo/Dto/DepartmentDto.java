package com.Employee.Department.Management.System.demo.Dto;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentDto {
    private Long id;
    private String name;
    private String description;
    private List<Long> employeeIds;
}

