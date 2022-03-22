package com.project.gymmembership.service;

import com.project.gymmembership.entity.Class;
import com.project.gymmembership.entity.Instructor;

import java.util.List;

public interface InstructorService {
    List<Instructor> findAll();

    Instructor findById(int instructorId);

    void save(Instructor instructor);

    void deleteById(int instructorId);

    Instructor getInstructorForClass(Class theClass);

    Instructor findByName(String name);
}
