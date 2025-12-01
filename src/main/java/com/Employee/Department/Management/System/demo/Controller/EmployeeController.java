package com.Employee.Department.Management.System.demo.Controller;

import com.Employee.Department.Management.System.demo.Dto.EmployeeDto;
import com.Employee.Department.Management.System.demo.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto dto) {
        return employeeService.createEmployee(dto);
    }

    @GetMapping("/by-department/{deptId}")
    public List<EmployeeDto> employeesByDepartment(@PathVariable Long deptId) {
     return employeeService.getEmployeesByDepartment(deptId);
         }

    @PutMapping("/{empId}/transfer/{deptId}")
    public EmployeeDto transfer(@PathVariable Long empId, @PathVariable Long deptId) {
        return employeeService.transferEmployee(empId,deptId);
    }

    @GetMapping()
    public List<EmployeeDto> getall() {
        return employeeService.getAllEmployees();
    }
}

