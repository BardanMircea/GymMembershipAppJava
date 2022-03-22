package com.project.gymmembership.dao;

import com.project.gymmembership.entity.Class;
import com.project.gymmembership.entity.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class InstructorDaoImpl implements InstructorDao{

    private final EntityManager entityManager;

    @Autowired
    public InstructorDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public List<Instructor> findAll() {
        Query query = entityManager.createQuery("Select i from Instructor i");

        return query.getResultList();
    }

    @Override
    public Instructor findById(int instructorId) {
        return entityManager.find(Instructor.class, instructorId);
    }

    @Override
    public void save(Instructor course) {

    }

    @Override
    public void deleteById(int instructorId) {
        entityManager.remove(findById(instructorId));
    }

    @Override
    public Instructor getInstructorForClass(Class theClass) {

        Query query = entityManager.createQuery("select i from Instructor i join Class c on i.id=c.instructor where c.name=:name");
        query.setParameter("name", theClass.getDescription());

        return (Instructor) query.getResultList().stream().findFirst().get();

    }

    @Override
    public Instructor findByName(String name) {

        Query query = entityManager.createQuery("select i from Instructor i where i.name=:name");
        query.setParameter("name", name);

        Instructor instructor = (Instructor) query.getResultList().stream().findFirst().get();
        return instructor;
    }
}
