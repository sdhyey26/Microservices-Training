package com.tss.department_service.service;

import java.util.List;

import com.tss.department_service.dto.DepartmentApiResponse;
import com.tss.department_service.dto.DepartmentRequestDto;
import com.tss.department_service.dto.DepartmentResponseDto;

public interface DepartmentService {
    DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto);
    DepartmentResponseDto getDepartmentById(long id);
    List<DepartmentResponseDto> getAllDepartments(); 
    DepartmentApiResponse getDepartmentWithEmployees(long deptId);
    void updateEmployeeDepartment(long empId, long deptId);
}
