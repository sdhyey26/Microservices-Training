package com.tss.employee_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "department_id")
    private Long departmentId;

    public Employee() {}

    public Employee(String name, Long departmentId) {
        this.name = name;
        this.departmentId = departmentId;
    }


}
