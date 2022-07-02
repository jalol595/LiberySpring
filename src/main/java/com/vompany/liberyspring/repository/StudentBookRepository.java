package com.vompany.liberyspring.repository;

import com.vompany.liberyspring.entity.BookEntity;
import com.vompany.liberyspring.entity.StudentBookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StudentBookRepository extends CrudRepository<StudentBookEntity, Integer> {

    List<StudentBookEntity> findByStudentId(Integer sId);

    Optional<StudentBookEntity> findByBookName(String name);

    List<StudentBookEntity> findAllById(Integer id);
}
