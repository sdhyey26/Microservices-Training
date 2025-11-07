package com.tss.employee_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.employee_service.dto.EmployeeApiResponse;
import com.tss.employee_service.dto.EmployeeRequestDto;
import com.tss.employee_service.dto.EmployeeResponseDto;
import com.tss.employee_service.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/empservice/-employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeRequestDto dto) {
        return new ResponseEntity<>(employeeService.createEmployee(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeApiResponse> getEmployeeById(@PathVariable long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping("/department/{deptId}")
    public ResponseEntity<List<EmployeeResponseDto>> getEmployeesByDepartment(@PathVariable Long deptId) {
        return ResponseEntity.ok(employeeService.getEmployeesByDepartment(deptId));
    }

    @PutMapping("/{empId}/department/{deptId}")
    public ResponseEntity<EmployeeResponseDto> updateEmployeeDepartment(
            @PathVariable Long empId,
            @PathVariable Long deptId) {
        return ResponseEntity.ok(employeeService.updateEmployeeDepartment(empId, deptId));
    }
}
