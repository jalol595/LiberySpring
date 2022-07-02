package com.vompany.liberyspring.controller;

import com.vompany.liberyspring.dto.BookDto;
import com.vompany.liberyspring.dto.StudentBookDto;
import com.vompany.liberyspring.enums.StudentRole;
import com.vompany.liberyspring.service.BookService;
import com.vompany.liberyspring.service.StudentBookService;
import com.vompany.liberyspring.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private BookService bookService;

    @Autowired
    private StudentBookService studentBookService;

    @GetMapping("/list")
    public ResponseEntity<List<BookDto>> getlist(@RequestHeader("Authorization") String jwt) {
        JwtUtil.decode1(jwt, StudentRole.USER);
        List<BookDto> list = bookService.getList();
        return ResponseEntity.ok().body(list);
    }


    @PostMapping("/takeBook")
    public ResponseEntity<?> create(@RequestHeader("Authorization") String token, @RequestBody BookDto dto) {
        Integer decode = JwtUtil.decode(token);
        bookService.takeBook(decode, dto);
        return ResponseEntity.ok().body("marhamat");
    }

    @GetMapping("/takkenList")
    public ResponseEntity<List<StudentBookDto>> getlistOnlyStudent(@RequestHeader("Authorization") String token) {
        Integer decode = JwtUtil.decode(token);
        List<StudentBookDto> list = bookService.getTakkenList(decode);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/returnBook")
    public ResponseEntity<?> returnBook(@RequestHeader("Authorization") String token, @RequestBody BookDto dto) {
        Integer decode = JwtUtil.decode(token);
        bookService.returnBook(decode, dto);
        return ResponseEntity.ok().body("ber va sur");
    }

    @GetMapping("/List")
    public ResponseEntity<List<StudentBookDto>> getAllList(@RequestHeader("Authorization") String token) {
        Integer decode = JwtUtil.decode(token);
        List<StudentBookDto> list = studentBookService.getAllList(decode);
        return ResponseEntity.ok().body(list);
    }

}
