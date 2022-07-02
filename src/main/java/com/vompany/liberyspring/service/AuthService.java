package com.vompany.liberyspring.service;

import com.vompany.liberyspring.dto.AuthDto;
import com.vompany.liberyspring.dto.StudentDto;
import com.vompany.liberyspring.entity.StudentEntity;
import com.vompany.liberyspring.enums.StudentStatus;
import com.vompany.liberyspring.exps.ItemNotFoundEseption;
import com.vompany.liberyspring.repository.StudentRepository;
import com.vompany.liberyspring.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private StudentRepository studentRepository;


    public StudentDto auth(AuthDto authDto) {

        Optional<StudentEntity> student = studentRepository.findByPhone(authDto.getPhone());
        if (student.isEmpty()) {
            throw new ItemNotFoundEseption("student not found");
        }

        StudentEntity entity = student.get();

        if (!entity.getPhone().equals(authDto.getPhone())) {
            throw new ItemNotFoundEseption("student not found");
        }

        if (!entity.getStatus().equals(StudentStatus.ACTIVE)) {
            throw new ItemNotFoundEseption("You blocked");
        }

        StudentDto dto = new StudentDto();

        dto.setName(entity.getName());
        dto.setSurName(entity.getSurName());
        dto.setJwt(JwtUtil.encode1(entity.getId(), entity.getRole()));

        return dto;
    }
}
