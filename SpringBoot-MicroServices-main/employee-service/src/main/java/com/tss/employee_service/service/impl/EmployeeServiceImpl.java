package com.tss.employee_service.service.impl;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.tss.employee_service.dto.DepartmentDto;
import com.tss.employee_service.dto.EmployeeApiResponse;
import com.tss.employee_service.dto.EmployeeRequestDto;
import com.tss.employee_service.dto.EmployeeResponseDto;
import com.tss.employee_service.entity.Employee;
import com.tss.employee_service.repo.EmployeeRepository;
import com.tss.employee_service.service.DepartmentApiClient;
import com.tss.employee_service.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final WebClient webClient;
    private final DepartmentApiClient deptClient;

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto requestDto) {

        // ✅ Step 1: Verify Department exists via Department Service API
        boolean departmentExists = false;
        try {
            departmentExists = webClient.get()
                    .uri("http://localhost:8081/deptservice/departments/" + requestDto.getDeptId())
                    .retrieve()
                    .toBodilessEntity()
                    .block() != null;
        } catch (Exception e) {
            throw new RuntimeException("Invalid Department ID: " + requestDto.getDeptId());
        }

        if (!departmentExists) {
            throw new RuntimeException("Department not found with ID: " + requestDto.getDeptId());
        }

        // ✅ Step 2: Create and save employee
        Employee employee = new Employee();
        employee.setName(requestDto.getEmpName());
        employee.setEmail(requestDto.getEmpEmail());
        employee.setSalary(requestDto.getSalary());
        employee.setDeptId(requestDto.getDeptId());

        Employee savedEmployee = employeeRepository.save(employee);
        return mapToResponseDto(savedEmployee);
    }


    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeApiResponse getEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));

//        DepartmentDto department = webClient.get()
//                .uri("http://localhost:8081/deptservice/departments/" + employee.getDeptId())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();
        
        DepartmentDto department = deptClient.getDepartment(employee.getDeptId());
        EmployeeApiResponse response = new EmployeeApiResponse();
        response.setEmployee(mapToResponseDto(employee));
        response.setDepartment(department);

        return response;
    }

    @Override
    public List<EmployeeResponseDto> getEmployeesByDepartment(Long deptId) {
        List<Employee> employees = employeeRepository.findByDeptId(deptId);
        List<EmployeeResponseDto> responseList = new ArrayList<>();
        for (Employee emp : employees) {
            EmployeeResponseDto dto = mapToResponseDto(emp);
            responseList.add(dto);
        }

        return responseList;
    }


    @Override
    public EmployeeResponseDto updateEmployeeDepartment(Long empId, Long deptId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + empId));

        employee.setDeptId(deptId);
        Employee updatedEmployee = employeeRepository.save(employee);

        return mapToResponseDto(updatedEmployee);
    }

    private EmployeeResponseDto mapToResponseDto(Employee employee) {
        return new EmployeeResponseDto(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getSalary(),
                employee.getDeptId()
        );
    }
}
