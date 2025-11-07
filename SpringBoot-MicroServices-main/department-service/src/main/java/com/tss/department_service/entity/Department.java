package com.tss.department_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deptId;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "dept_description")
    private String deptDescription;
}
