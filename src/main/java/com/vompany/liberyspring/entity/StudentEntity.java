package com.vompany.liberyspring.entity;

import com.vompany.liberyspring.enums.StudentRole;
import com.vompany.liberyspring.enums.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String surName;

    @Column(unique = true)
    private String phone;

    @Column
    private String password;


    @Enumerated(EnumType.STRING)
    private StudentStatus status = StudentStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    private StudentRole role;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(nullable = false)
    private boolean visible = Boolean.TRUE;


}