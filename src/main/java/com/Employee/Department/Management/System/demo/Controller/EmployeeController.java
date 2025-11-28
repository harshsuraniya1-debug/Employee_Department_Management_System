package com.Employee.Department.Management.System.demo.Controller;

import com.Employee.Department.Management.System.demo.Dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto dto) {
        return employeeService.create(dto);
    }

    @GetMapping("/by-department/{deptId}")
    public List<EmployeeDto> employeesByDepartment(@PathVariable Long deptId) {
        return employeeService.employeesByDepartment(deptId);
    }

    @PutMapping("/{empId}/transfer/{deptId}")
    public EmployeeDto transfer(@PathVariable Long empId, @PathVariable Long deptId) {
        return employeeService.transfer(empId, deptId);
    }

    @GetMapping("/salary-report/{deptId}")
    public Double salaryReport(@PathVariable Long deptId) {
        return employeeService.salaryReport(deptId);
    }
}

