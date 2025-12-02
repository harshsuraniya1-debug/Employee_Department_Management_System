package com.Employee.Department.Management.System.demo.Service;


import com.Employee.Department.Management.System.demo.Dto.DepartmentDto;
import com.Employee.Department.Management.System.demo.entity.Department;
//import com.Employee.Department.Management.System.demo.exception.ResourceNotFoundException;
import com.Employee.Department.Management.System.demo.Repositories.DepartmentRepository;
import com.Employee.Department.Management.System.demo.Service.DepartmentService;
import com.Employee.Department.Management.System.demo.Mapper.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto dto) {
        Department department = MapperUtil.toDepartmentEntity(dto);
<<<<<<< Updated upstream
=======
        if(dto.getEmployeeIds() != null &&
        !dto.getEmployeeIds().isEmpty()){
            List<Employee> employees = fetchEmplyeeList(dto.getEmployeeIds());

            for(Employee emp : employees){
                emp.setDepartment(department);
            }

            department.setEmployeeslist(employees);        }
>>>>>>> Stashed changes
        Department savedDept = departmentRepository.save(department);
        return MapperUtil.toDepartmentDto(savedDept);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(MapperUtil::toDepartmentDto)
                .toList();
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));
        return MapperUtil.toDepartmentDto(dept);
    }
}

