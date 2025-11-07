package com.tss.department_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tss.department_service.dto.DepartmentApiResponse;
import com.tss.department_service.dto.DepartmentRequestDto;
import com.tss.department_service.dto.DepartmentResponseDto;
import com.tss.department_service.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/deptservice/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentResponseDto> createDepartment(@RequestBody DepartmentRequestDto requestDto) {
        DepartmentResponseDto response = departmentService.createDepartment(requestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments() {
        List<DepartmentResponseDto> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{deptId}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentById(@PathVariable Long deptId) {
        DepartmentResponseDto response = departmentService.getAllDepartments().stream()
                .filter(d -> d.getDeptId() == deptId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + deptId));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{deptId}/with-employees")
    public ResponseEntity<DepartmentApiResponse> getDepartmentWithEmployees(@PathVariable Long deptId) {
        DepartmentApiResponse response = departmentService.getDepartmentWithEmployees(deptId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-employee-department")
    public ResponseEntity<String> updateEmployeeDepartment(
            @RequestParam Long empId,
            @RequestParam Long deptId) {

        departmentService.updateEmployeeDepartment(empId, deptId);
        return ResponseEntity.ok("Employee " + empId + " moved to Department " + deptId);
    }
}
