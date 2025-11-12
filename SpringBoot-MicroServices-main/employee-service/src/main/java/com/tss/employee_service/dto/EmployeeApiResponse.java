package com.tss.employee_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeApiResponse {
    private EmployeeResponseDto employee;
    private DepartmentDto department;
}
