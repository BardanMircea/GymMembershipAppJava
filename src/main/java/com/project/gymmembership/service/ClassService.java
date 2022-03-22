package com.project.gymmembership.service;

import com.project.gymmembership.entity.Class;
import com.project.gymmembership.entity.Instructor;
import com.project.gymmembership.entity.Review;


import java.util.List;

public interface ClassService {
    List<Class> findAll();

    Class findById(int classId);

    void save(Class theClass);

    void deleteById(int classId);

    Instructor getInstructorForClass(Class theClass);

    Class findByDescription(String description);

    List<Review> getReviewsForClass(Class theClass);
}
