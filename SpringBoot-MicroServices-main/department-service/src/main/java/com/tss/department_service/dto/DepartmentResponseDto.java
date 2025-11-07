package com.tss.department_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponseDto {
    private long deptId;
    private String deptName;
    private String deptDescription;
}
