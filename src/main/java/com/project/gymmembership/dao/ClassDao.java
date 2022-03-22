package com.project.gymmembership.dao;

import com.project.gymmembership.entity.Class;
import com.project.gymmembership.entity.Instructor;
import com.project.gymmembership.entity.Review;

import java.util.List;

public interface ClassDao {
    List<Class> findAll();

    Class findById(int id);

    void save(Class aClass);

    void deleteById(int id);

    Instructor getInstructorForClass(Class aClass);

    Class findByDescription(String description);

    List<Review> getReviewsForClass(Class aClass);
}
