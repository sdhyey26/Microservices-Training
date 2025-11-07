package com.tss.employee_service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tss.employee_service.dto.DepartmentDto;

@FeignClient(name="department-service")
public interface DepartmentApiClient {
	
    @GetMapping("/deptservice/departments/{deptId}")
	public DepartmentDto getDepartment(@PathVariable Long deptId);
	
}
