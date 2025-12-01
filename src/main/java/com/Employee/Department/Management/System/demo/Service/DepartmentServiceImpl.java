package com.Employee.Department.Management.System.demo.Service;


import com.Employee.Department.Management.System.demo.Dto.DepartmentDto;
import com.Employee.Department.Management.System.demo.Repositories.EmployeeRepository;
import com.Employee.Department.Management.System.demo.entity.Department;
//import com.Employee.Department.Management.System.demo.exception.ResourceNotFoundException;
import com.Employee.Department.Management.System.demo.Repositories.DepartmentRepository;
import com.Employee.Department.Management.System.demo.Service.DepartmentService;
import com.Employee.Department.Management.System.demo.Mapper.MapperUtil;
import com.Employee.Department.Management.System.demo.entity.Employee;
import com.Employee.Department.Management.System.demo.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    private final EmployeeService employeeService;

    @Override
    public DepartmentDto createDepartment(DepartmentDto dto) {
        Department department = MapperUtil.toDepartmentEntity(dto);
        if(dto.getEmployeeIds() != null &&
        !dto.getEmployeeIds().isEmpty()){
            List<Employee> employees = fetchEmplyeeList(dto.getEmployeeIds());

            for(Employee emp : employees){
                emp.setDepartment(department);   // 🔥 set FK reference
            }

            department.setEmployeeslist(employees);        }
        Department savedDept = departmentRepository.save(department);
        return MapperUtil.toDepartmentDto(savedDept);
    }

    private List<Employee> fetchEmplyeeList(List<Long> employeeIds) {
        return employeeRepository.findAllById(employeeIds);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {

        List<Department> departmentList = departmentRepository.findAll();

        List<DepartmentDto> dtoList = new ArrayList<>();

        for (Department department : departmentList) {
            DepartmentDto dto = MapperUtil.toDepartmentDto(department);
            dtoList.add(dto);
        }

        return dtoList;
    }


    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));
        return MapperUtil.toDepartmentDto(dept);
    }
}

