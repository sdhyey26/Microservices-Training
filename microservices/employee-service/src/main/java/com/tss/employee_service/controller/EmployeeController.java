package com.tss.employee_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.employee_service.dto.EmployeeApiResponse;
import com.tss.employee_service.dto.EmployeeDTO;
import com.tss.employee_service.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeApiResponse> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.readAnEmployee(id));
    }
    
    @PutMapping("/{id}/department/{newDeptId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeDepartment(
            @PathVariable Long id,
            @PathVariable Long newDeptId) {
        return ResponseEntity.ok(employeeService.updateEmployeeDepartment(id, newDeptId));
    }
    
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeDTO));
    }

}
