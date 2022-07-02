package com.vompany.liberyspring.controller;

import com.vompany.liberyspring.dto.AuthDto;
import com.vompany.liberyspring.dto.StudentDto;
import com.vompany.liberyspring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<StudentDto> login(@RequestBody AuthDto dto) {

        StudentDto studentDto = authService.auth(dto);
        return ResponseEntity.ok(studentDto);

    }


}
