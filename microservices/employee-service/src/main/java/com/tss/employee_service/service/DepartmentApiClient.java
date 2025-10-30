package com.tss.employee_service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tss.employee_service.dto.DepartmentDto;

@FeignClient(url = "http://localhost:8081" , value = "department-service")
public interface DepartmentApiClient {

	
    @GetMapping("api/departments/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id);
}
