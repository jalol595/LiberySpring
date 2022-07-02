package com.vompany.liberyspring.controller;

;
import com.vompany.liberyspring.entity.StudentEntity;
import com.vompany.liberyspring.enums.StudentRole;
import com.vompany.liberyspring.repository.StudentBookRepository;
import com.vompany.liberyspring.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/init")
public class InitController {

    @Autowired
    private StudentRepository studentRepository;


    @GetMapping("/initAdmin")
    public String initAdmin() {
        Optional<StudentEntity> optional = studentRepository.findByPhone("99894123456789");
        if (optional.isPresent()) {
            return "Exists";
        }

        StudentEntity entity = new StudentEntity();
        entity.setName("Adminjon");
        entity.setSurName("AdminAli");
        entity.setPhone("99894123456789");
        entity.setPassword("12345");
        entity.setRole(StudentRole.ADMIN);
        studentRepository.save(entity);
        return "Success";
    }
}
