package com.tss.employee_service.service;

import java.util.List;

import com.tss.employee_service.dto.EmployeeApiResponse;
import com.tss.employee_service.dto.EmployeeRequestDto;
import com.tss.employee_service.dto.EmployeeResponseDto;
import com.tss.employee_service.entity.Employee;

public interface EmployeeService {
    EmployeeResponseDto createEmployee(EmployeeRequestDto requestDto);
    List<EmployeeResponseDto> getAllEmployees();
    EmployeeApiResponse getEmployeeById(long id);
    List<EmployeeResponseDto> getEmployeesByDepartment(Long deptId);

    EmployeeResponseDto updateEmployeeDepartment(Long empId, Long deptId);
}
