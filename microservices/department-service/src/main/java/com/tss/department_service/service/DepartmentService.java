package com.tss.department_service.service;

import java.util.List;

import com.tss.department_service.dto.DepartmentDTO;
import com.tss.department_service.dto.EmployeeDTO;

public interface DepartmentService {
    List<DepartmentDTO> getAllDepartments();
    DepartmentDTO getDepartmentById(Long id);
    
    List<EmployeeDTO> getEmployeesByDepartment(Long departmentId);
//    EmployeeDTO updateEmployeeDepartment(Long employeeId, Long newDepartmentId);
}
