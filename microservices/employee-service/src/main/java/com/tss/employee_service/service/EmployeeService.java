package com.tss.employee_service.service;

import java.util.List;

import com.tss.employee_service.dto.EmployeeApiResponse;
import com.tss.employee_service.dto.EmployeeDTO;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();
    EmployeeApiResponse readAnEmployee(long employeeId);
    EmployeeDTO updateEmployeeDepartment(Long id, Long newDepartmentId);
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

}
