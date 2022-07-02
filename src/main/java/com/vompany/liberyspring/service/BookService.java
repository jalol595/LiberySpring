package com.vompany.liberyspring.service;

import com.vompany.liberyspring.dto.BookDto;
import com.vompany.liberyspring.dto.StudentBookDto;
import com.vompany.liberyspring.dto.StudentDto;
import com.vompany.liberyspring.entity.BookEntity;
import com.vompany.liberyspring.entity.StudentBookEntity;
import com.vompany.liberyspring.entity.StudentEntity;
import com.vompany.liberyspring.enums.StudentBookStatus;
import com.vompany.liberyspring.enums.StudentStatus;
import com.vompany.liberyspring.exps.AlreadyExist;
import com.vompany.liberyspring.exps.BadRequestException;
import com.vompany.liberyspring.exps.ItemNotFoundEseption;
import com.vompany.liberyspring.repository.BookRepository;
import com.vompany.liberyspring.repository.StudentBookRepository;
import com.vompany.liberyspring.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentBookRepository studentBookRepository;

    public void create(BookDto dto) {
        Optional<BookEntity> bookEntity = bookRepository.findByNameAndAuthor(dto.getName(), dto.getAuthor());
        if (bookEntity.isPresent()) {
            throw new AlreadyExist("Alredy exist book");
        }

        isValid(dto);

        BookEntity book = new BookEntity();
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        bookRepository.save(book);


    }

    private void isValid(BookDto dto) {
        if (dto.getName() == null || dto.getName().length() < 3) {
            throw new BadRequestException("wrong name");
        }

        if (dto.getAuthor() == null || dto.getAuthor().length() < 4) {
            throw new BadRequestException("author required.");
        }


    }

    public List<BookDto> getList() {
        Iterable<BookEntity> all = bookRepository.findAllByVisible(true);
        List<BookDto> dtoList = new LinkedList<>();

        all.forEach(bookEntity -> {
            BookDto postDto = new BookDto();
            postDto.setName(bookEntity.getName());
            postDto.setAuthor(bookEntity.getAuthor());
            dtoList.add(postDto);
        });
        return dtoList;
    }

    public void update(Integer id, BookDto dto) {
        Optional<BookEntity> book = bookRepository.findById(id);

        if (book.isEmpty()) {
            throw new ItemNotFoundEseption("not found book");
        }


        isValid(dto);

        BookEntity bookEntity = book.get();

        bookEntity.setName(dto.getName());
        bookEntity.setAuthor(dto.getAuthor());
        bookRepository.save(bookEntity);
    }

    public void delete(Integer id) {
        Optional<BookEntity> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new ItemNotFoundEseption("not found book");
        }
        if (!book.get().isVisible()) {
            throw new AlreadyExist("IsVisible False edi");
        }

        book.get().setVisible(Boolean.FALSE);

        bookRepository.save(book.get());
    }

    public List<StudentDto> getListStudent() {
        Iterable<StudentEntity> all = studentRepository.findAll();
        List<StudentDto> dtoList = new LinkedList<>();

        all.forEach(studentEntity -> {
            StudentDto studentDto = new StudentDto();
            studentDto.setName(studentEntity.getName());
            studentDto.setSurName(studentEntity.getSurName());
            studentDto.setPhone(studentEntity.getPhone());
            dtoList.add(studentDto);
        });
        return dtoList;
    }

    public void updateStatus(StudentDto dto) {

        Optional<StudentEntity> student = studentRepository.findByPhone(dto.getPhone());
        if (student.isEmpty()){
            throw new ItemNotFoundEseption("not found student");
        }

        if (student.get().getStatus().equals(StudentStatus.BLOCK)){
            throw new AlreadyExist("this bloceked allaqachon");
        }

        StudentEntity studentEntity = student.get();
        studentEntity.setStatus(StudentStatus.BLOCK);
        studentRepository.save(studentEntity);

    }

    public void takeBook(Integer sId, BookDto dto) {
        Optional<StudentEntity> student = studentRepository.findById(sId);
        if (student.isEmpty()){
            throw new ItemNotFoundEseption("not found student");
        }

        Optional<BookEntity> book = bookRepository.findByName(dto.getName());
        if (book.isEmpty()){
            throw new ItemNotFoundEseption("not found book");
        }

        StudentBookEntity studentBookEntity=new StudentBookEntity();
        studentBookEntity.setStudentId(sId);
        studentBookEntity.setBookName(dto.getName());
        studentBookEntity.setTakkenDate(LocalDateTime.now());
        studentBookRepository.save(studentBookEntity);
    }

    public List<StudentBookDto> getTakkenList(Integer sId) {
        Optional<StudentEntity> student = studentRepository.findById(sId);
        if (student.isEmpty()){
            throw new ItemNotFoundEseption("not found student");
        }

        Iterable<StudentBookEntity> all = studentBookRepository.findAllById(sId);
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

    public List<StudentBookDto> getTakkenListForAdmin() {


        Iterable<StudentBookEntity> all = studentBookRepository.findAll();
        List<StudentBookDto> dtoList = new LinkedList<>();

        all.forEach(studentBookEntity -> {
            StudentBookDto studentBookDto = new StudentBookDto();
            studentBookDto.setStudentId(studentBookEntity.getStudentId());
            studentBookDto.setBookName(studentBookEntity.getBookName());
            studentBookDto.setTakkenDate(studentBookEntity.getTakkenDate());
            studentBookDto.setReturnedDate(studentBookEntity.getReturnedDate());
            dtoList.add(studentBookDto);
        });
        return dtoList;
    }

    public void returnBook(Integer sId, BookDto dto) {
        Optional<StudentEntity> student = studentRepository.findById(sId);
        if (student.isEmpty()){
            throw new ItemNotFoundEseption("not found student");
        }

        Optional<StudentBookEntity> book = studentBookRepository.findByBookName(dto.getName());
        if (book.isEmpty()){
            throw new ItemNotFoundEseption("not found book");
        }

        StudentBookEntity studentBook = book.get();
        studentBook.setStatus(StudentBookStatus.RETURNED);
        studentBook.setReturnedDate(LocalDateTime.now());

        studentBookRepository.save(studentBook);
    }

}
