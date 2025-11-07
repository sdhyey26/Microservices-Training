package com.tss.employee_service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class DepartmentDto {
	 private long id;
	    private String dept_name;
}
