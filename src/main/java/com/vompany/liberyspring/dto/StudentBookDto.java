package com.vompany.liberyspring.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.vompany.liberyspring.enums.StudentBookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentBookDto {



    private int studentId;
    private String bookName;
    private StudentBookStatus status;
    private LocalDateTime takkenDate;
    private LocalDateTime returnedDate;




}
