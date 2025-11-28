package com.Employee.Department.Management.System.demo.Dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String name;
    private String email;
    private Double salary;
    private Long departmentId;
}

