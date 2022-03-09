package com.capg.repository;

import org.springframework.data.repository.CrudRepository;

import com.capg.entity.Student;

public interface StudentRepository extends CrudRepository<Student , Integer> {

}
