package com.Employee.Department.Management.System.demo.Controller;

import com.Employee.Department.Management.System.demo.Dto.DepartmentDto;
import com.Employee.Department.Management.System.demo.Repositories.DepartmentRepository;
import com.Employee.Department.Management.System.demo.Service.DepartmentService;
import com.Employee.Department.Management.System.demo.entity.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    @Autowired
    private final DepartmentService departmentService;

    @PostMapping
    public DepartmentDto create(@RequestBody DepartmentDto departmentDto) {
        return departmentService.createDepartment(departmentDto);
    }

    @GetMapping
    public List<DepartmentDto> getAll() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public DepartmentDto getById(long id){
        return departmentService.getDepartmentById(id);
    }
}

