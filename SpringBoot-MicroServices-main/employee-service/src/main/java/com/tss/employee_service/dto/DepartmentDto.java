package com.tss.employee_service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class DepartmentDto {
	 private long deptId;
	    private String deptName;
}
