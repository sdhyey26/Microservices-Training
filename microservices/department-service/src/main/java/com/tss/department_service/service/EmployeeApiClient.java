package com.tss.department_service.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tss.department_service.dto.EmployeeDTO;

@FeignClient( name = "employee-service")
public interface EmployeeApiClient {

	
    @GetMapping("/api/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() ;
    
}
