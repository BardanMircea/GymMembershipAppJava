package com.project.gymmembership.service;

import com.project.gymmembership.dao.ClassDao;
import com.project.gymmembership.entity.Class;
import com.project.gymmembership.entity.Instructor;
import com.project.gymmembership.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassDao classDao;

    @Autowired
    public ClassServiceImpl(ClassDao classDao){
        this.classDao = classDao;
    }

    @Override
    @Transactional
    public List<Class> findAll() {
        return classDao.findAll();
    }

    @Override
    @Transactional
    public Class findById(int classId) {

        return classDao.findById(classId);
    }

    @Override
    @Transactional
    public void save(Class theClass) {

        classDao.save(theClass);
    }

    @Override
    @Transactional
    public void deleteById(int classId) {
        classDao.deleteById(classId);
    }

    @Override
    @Transactional
    public Instructor getInstructorForClass(Class theClass) {
        return classDao.getInstructorForClass(theClass);
    }

    @Override
    @Transactional
    public Class findByDescription(String description) {

        return classDao.findByDescription(description);
    }

    @Override
    @Transactional
    public List<Review> getReviewsForClass(Class theClass) {
        return classDao.getReviewsForClass(theClass);
    }
}
