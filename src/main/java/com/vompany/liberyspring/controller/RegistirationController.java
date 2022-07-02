package com.vompany.liberyspring.controller;

import com.vompany.liberyspring.dto.StudentDto;
import com.vompany.liberyspring.service.RegistirationService;
import com.vompany.liberyspring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegistirationController {

    @Autowired
    private RegistirationService registirationService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentDto studentDto) {
        StudentDto dto = registirationService.create(studentDto);
        return ResponseEntity.ok().body(dto);
    }


}
