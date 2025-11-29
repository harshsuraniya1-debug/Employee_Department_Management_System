package com.Employee.Department.Management.System.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private Double salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}

