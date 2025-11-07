package com.tss.employee_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto {
    private String empName;
    private String empEmail;
    private double salary;
    private long deptId;
}
