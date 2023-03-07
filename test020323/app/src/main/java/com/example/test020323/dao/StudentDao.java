package com.example.test020323.dao;

import com.example.test020323.domain.Student;

import java.util.List;

public interface StudentDao {

    long insert(Student student);
    List<Student> findAll();
    Student findById(long id);
    int update(long id, Student newStudent);
    int delete(long id);

}
