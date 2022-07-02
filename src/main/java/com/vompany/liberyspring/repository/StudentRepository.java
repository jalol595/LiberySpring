package com.vompany.liberyspring.repository;

import com.vompany.liberyspring.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

    Optional<StudentEntity> findByPhone(String phone);
    Optional<StudentEntity> findByNameAndSurName(String name, String surName);

}
