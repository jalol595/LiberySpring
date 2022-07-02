package com.vompany.liberyspring.service;

import com.vompany.liberyspring.dto.StudentBookDto;
import com.vompany.liberyspring.entity.StudentBookEntity;
import com.vompany.liberyspring.entity.StudentEntity;
import com.vompany.liberyspring.exps.ItemNotFoundEseption;
import com.vompany.liberyspring.repository.StudentBookRepository;
import com.vompany.liberyspring.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentBookService  {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentBookRepository studentBookRepository;

    public List<StudentBookDto> getAllList(Integer sId) {
        Optional<StudentEntity> student = studentRepository.findById(sId);
        if (student.isEmpty()){
            throw new ItemNotFoundEseption("not found student");
        }

        Iterable<StudentBookEntity> all = studentBookRepository.findByStudentId(sId);
        List<StudentBookDto> dtoList = new LinkedList<>();

        all.forEach(studentBookEntity -> {
            StudentBookDto studentBookDto = new StudentBookDto();
            studentBookDto.setStudentId(sId);
            studentBookDto.setBookName(studentBookEntity.getBookName());
            studentBookDto.setTakkenDate(studentBookEntity.getTakkenDate());
            studentBookDto.setReturnedDate(studentBookEntity.getReturnedDate());
            dtoList.add(studentBookDto);
        });
        return dtoList;
    }
}
