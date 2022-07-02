package com.vompany.liberyspring.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.vompany.liberyspring.enums.StudentBookStatus;
import com.vompany.liberyspring.enums.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {


    private String name;
    private String surName;
    private String phone;
    private String password;
    private StudentStatus status;

    private String jwt;

}
