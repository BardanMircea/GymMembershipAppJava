package com.project.gymmembership.dao;

import com.project.gymmembership.entity.Class;
import com.project.gymmembership.entity.Instructor;

import java.util.List;

public interface InstructorDao {
    List<Instructor> findAll();

    Instructor findById(int id);

    void save(Instructor instructor);

    void deleteById(int instructorId);

    Instructor getInstructorForClass(Class aClass);

    Instructor findByName(String name);
}
