package com.vompany.liberyspring.entity;

import com.vompany.liberyspring.enums.StudentBookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "studentBook")
public class StudentBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int studentId;

    @Column
    private String bookName;

    @Enumerated(EnumType.STRING)
    private StudentBookStatus status=StudentBookStatus.TAKEN;

    @Column
    private LocalDateTime takkenDate;

    @Column
    private LocalDateTime returnedDate;


}
