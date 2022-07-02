package com.vompany.liberyspring.controller;

import com.vompany.liberyspring.dto.BookDto;
import com.vompany.liberyspring.dto.StudentBookDto;
import com.vompany.liberyspring.dto.StudentDto;
import com.vompany.liberyspring.enums.StudentRole;
import com.vompany.liberyspring.service.BookService;
import com.vompany.liberyspring.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookService bookService;

    @PostMapping("/createBook")
    public ResponseEntity<?> create(@RequestBody BookDto dto, @RequestHeader("Authorization") String jwt) {
        JwtUtil.decode1(jwt, StudentRole.ADMIN);
        bookService.create(dto);
        return ResponseEntity.ok().body("succsessfully created");
    }


    @GetMapping("/list")
    public ResponseEntity<List<BookDto>> getlistBook(@RequestHeader("Authorization") String jwt) {
        JwtUtil.decode1(jwt, StudentRole.ADMIN);
        List<BookDto> list = bookService.getList();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody BookDto dto, @RequestHeader("Authorization") String jwt) {
        JwtUtil.decode1(jwt, StudentRole.ADMIN);
        bookService.update(id, dto);
        return ResponseEntity.ok().body("Succsessfully updated");
    }

    @PutMapping("/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Integer id, @RequestHeader("Authorization") String jwt) {
        JwtUtil.decode1(jwt, StudentRole.ADMIN);
        bookService.delete(id);
        return ResponseEntity.ok().body("Sucsessfully deleted");
    }

    @GetMapping("/listStudent")
    public ResponseEntity<List<StudentDto>> getStudentlist(@RequestHeader("Authorization") String jwt) {
        JwtUtil.decode1(jwt, StudentRole.ADMIN);
        List<StudentDto> list = bookService.getListStudent();
        return ResponseEntity.ok().body(list);
    }


    @PutMapping("/update")
    private ResponseEntity<?> updateStudentStatus(@RequestBody StudentDto dto, @RequestHeader("Authorization") String jwt) {
        JwtUtil.decode1(jwt, StudentRole.ADMIN);
        bookService.updateStatus(dto);
        return ResponseEntity.ok().body("Blacked");
    }

    @GetMapping("/takkenList")
    public ResponseEntity<List<StudentBookDto>> getlist(@RequestHeader("Authorization") String jwt) {
        JwtUtil.decode1(jwt, StudentRole.ADMIN);
        List<StudentBookDto> list = bookService.getTakkenListForAdmin();
        return ResponseEntity.ok().body(list);
    }


}
