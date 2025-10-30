package com.tss.department_service.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tss.department_service.dto.DepartmentDTO;
import com.tss.department_service.dto.EmployeeDTO;
import com.tss.department_service.entity.Department;
import com.tss.department_service.repository.DepartmentRepository;
import com.tss.department_service.service.DepartmentService;
import com.tss.department_service.service.EmployeeApiClient;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
//    private WebClient webClient ;
    private EmployeeApiClient employeeApiClient;
    

    public DepartmentServiceImpl(DepartmentRepository departmentRepository ,EmployeeApiClient employeeApiClient ) {
        this.departmentRepository = departmentRepository;
        this.employeeApiClient = employeeApiClient;
    }



    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(dept -> new DepartmentDTO(dept.getId(), dept.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        return new DepartmentDTO(department.getId(), department.getName());
    }
    
    @Override
    public List<EmployeeDTO> getEmployeesByDepartment(Long departmentId) {
        ResponseEntity<List<EmployeeDTO>> response = employeeApiClient.getAllEmployees();

        if (response == null || response.getBody() == null) {
            throw new RuntimeException("Could not fetch employees from Employee Service");
        }

        return response.getBody().stream()
                .filter(e -> departmentId.equals(e.getDepartmentId()))
                .collect(Collectors.toList());
    }


//    @Override
//    public EmployeeDTO updateEmployeeDepartment(Long employeeId, Long newDepartmentId) {
//        // Prepare update payload
//        EmployeeDTO updatedEmployee = new EmployeeDTO();
//        updatedEmployee.setDepartmentId(newDepartmentId);
//
//        return webClient.put()
//                .uri("http://localhost:8080/api/employees/" + employeeId + "/department/" + newDepartmentId)
//                .retrieve()
//                .bodyToMono(EmployeeDTO.class)
//                .block();
//    }
}
