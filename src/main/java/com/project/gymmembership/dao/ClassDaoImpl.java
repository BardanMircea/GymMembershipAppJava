package com.project.gymmembership.dao;

import com.project.gymmembership.entity.Class;
import com.project.gymmembership.entity.Instructor;
import com.project.gymmembership.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@Repository
public class ClassDaoImpl implements ClassDao {

    private final EntityManager entityManager;

    @Autowired
    public ClassDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Class> findAll() {
        Query query = entityManager.createQuery("select c from Class c");


        return query.getResultList();
    }

    @Override
    public Class findById(int classId) {

        return entityManager.find(Class.class, classId);
    }

    @Override
    public void save(Class theClass) {
        entityManager.persist(theClass);

    }

    @Override
    public void deleteById(int classId) {
        entityManager.remove(findById(classId));
    }

    @Override
    public Instructor getInstructorForClass(Class theClass) {

        Query query = entityManager.createQuery("select i from Instructor i join Class c on i.id=c.instructor " +
                                                "where c.instructor=:param");

        query.setParameter("param", theClass.getInstructor());

        return (Instructor) query.getResultStream().findFirst().get();
    }

    @Override
    public Class findByDescription(String description) {

        Query query = entityManager.createQuery("select c from Class c where c.description=:description");
        query.setParameter("description", description);

        return (Class) query.getResultStream().findFirst().get();
    }

    @Override
    public List<Review> getReviewsForClass(Class theClass) {

        Query query = entityManager.createQuery("select r from Review r where r.theClass=:theClass");

        query.setParameter("theClass", theClass);

        return query.getResultList();
    }
}
