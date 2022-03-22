package com.project.gymmembership.service;

import com.project.gymmembership.dao.InstructorDao;
import com.project.gymmembership.entity.Class;
import com.project.gymmembership.entity.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService{

    private InstructorDao instructorDao;

    @Autowired
    public InstructorServiceImpl(InstructorDao instructorDao){
        this.instructorDao = instructorDao;
    }


    @Override
    @Transactional
    public List<Instructor> findAll() {
        return instructorDao.findAll();
    }

    @Override
    @Transactional
    public Instructor findById(int instructorId) {
        return null;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {

    }

    @Override
    @Transactional
    public void deleteById(int instructorId) {
        instructorDao.deleteById(instructorId);
    }

    @Override
    @Transactional
    public Instructor getInstructorForClass(Class theClass) {
        return instructorDao.getInstructorForClass(theClass);
    }

    @Override
    public Instructor findByName(String name) {
        return instructorDao.findByName(name);
    }
}
