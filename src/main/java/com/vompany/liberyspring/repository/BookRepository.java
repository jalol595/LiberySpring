package com.vompany.liberyspring.repository;

import com.vompany.liberyspring.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends CrudRepository<BookEntity, Integer> {

    Optional<BookEntity> findByNameAndAuthor(String name, String author);

    List<BookEntity> findAllByVisible(Boolean b);

    Optional<BookEntity> findByName(String name);


}
