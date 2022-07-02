package com.vompany.liberyspring.service;

import com.vompany.liberyspring.dto.StudentDto;
import com.vompany.liberyspring.entity.StudentEntity;
import com.vompany.liberyspring.enums.StudentRole;
import com.vompany.liberyspring.exps.AlreadyExistNameAndSurName;
import com.vompany.liberyspring.exps.AlreadyExistPhone;
import com.vompany.liberyspring.exps.BadRequestException;
import com.vompany.liberyspring.repository.StudentRepository;
import com.vompany.liberyspring.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistirationService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentDto create(StudentDto studentDto) {

        Optional<StudentEntity> studentEntity = studentRepository.findByNameAndSurName(studentDto.getName(), studentDto.getSurName());
        if (studentEntity.isPresent()) {
            throw new AlreadyExistNameAndSurName("name and surname busy");
        }


        Optional<StudentEntity> phone = studentRepository.findByPhone(studentDto.getPhone());
        if (phone.isPresent()) {
            throw new AlreadyExistPhone("This phone number busy");
        }


        isValid(studentDto);

        StudentEntity student = new StudentEntity();
        student.setName(studentDto.getName());
        student.setSurName(studentDto.getSurName());
        student.setPhone(studentDto.getPhone());
        student.setPassword(studentDto.getPassword());
        student.setRole(StudentRole.USER);
        studentRepository.save(student);

        StudentDto responseDTO = new StudentDto();
        responseDTO.setName(studentDto.getName());
        responseDTO.setSurName(studentDto.getSurName());
        responseDTO.setPassword(studentDto.getPassword());
        responseDTO.setJwt(JwtUtil.encode1(student.getId(), student.getRole()));
        return responseDTO;
    }

    private void isValid(StudentDto dto) {
        if (dto.getName() == null || dto.getName().length() < 3) {
            throw new BadRequestException("wrong name");
        }

        if (dto.getSurName() == null || dto.getSurName().length() < 4) {
            throw new BadRequestException("surname required.");
        }

        if (dto.getPassword() == null || dto.getPassword().length() < 3) {
            throw new BadRequestException("password required.");
        }

    }

}
