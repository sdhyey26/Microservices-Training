package com.tss.department_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.tss.department_service.dto.DepartmentApiResponse;
import com.tss.department_service.dto.DepartmentRequestDto;
import com.tss.department_service.dto.DepartmentResponseDto;
import com.tss.department_service.dto.EmployeeDto;
import com.tss.department_service.entity.Department;
import com.tss.department_service.repo.DepartmentRepository;
import com.tss.department_service.service.DepartmentService;
import com.tss.department_service.service.EmployeeApiClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final WebClient webClient;
    private final EmployeeApiClient empClient;


    @Override
    public DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto) {
        Department department = new Department();
        department.setDeptName(requestDto.getDeptName());
        department.setDeptDescription(requestDto.getDeptDescription());
        Department saved = departmentRepository.save(department);
        return mapToResponse(saved);
    }

    @Override
    public DepartmentResponseDto getDepartmentById(long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + id));
        return mapToResponse(department);
    }

    private DepartmentResponseDto mapToResponse(Department department) {
        return new DepartmentResponseDto(department.getDeptId(), department.getDeptName(), department.getDeptDescription());
    }
    
    @Override
    public DepartmentApiResponse getDepartmentWithEmployees(long deptId) {
        // 🔹 1️⃣ Fetch department details from local DB
        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + deptId));

        // 🔹 2️⃣ Call Employee Service to fetch employees of this department
//        List<EmployeeDto> employees = webClient.get()
//                .uri("http://localhost:8080/empservice/employees/department/" + deptId)
//                .retrieve()
//                .bodyToFlux(EmployeeDto.class)
//                .collectList()
//                .block();
        
        List<EmployeeDto> employees = empClient.getEmployeesByDepartment(deptId);

        // 🔹 3️⃣ Build and return response
        DepartmentApiResponse response = new DepartmentApiResponse();
        response.setDepartment(new DepartmentResponseDto(department.getDeptId(), department.getDeptName(), department.getDeptDescription()));
        response.setEmployee(employees);

        return response;
    }

    @Override
    public void updateEmployeeDepartment(long empId, long deptId) {
    	Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + deptId));
    	
        try {
            webClient.put()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("http")
                            .host("localhost")
                            .port(8080)
                            .path("/empservice/employees/{empId}/department/{deptId}")
                            .build(empId, deptId))
                    .retrieve()
                    .toBodilessEntity()
                    .block(); // Wait for the request to complete

            System.out.println("✅ Employee " + empId + " successfully updated to department " + deptId);
        } catch (Exception e) {
            System.err.println("❌ Failed to update employee department: " + e.getMessage());
            throw new RuntimeException("Error while updating employee department for empId=" + empId, e);
        }
    }


    
    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


}
