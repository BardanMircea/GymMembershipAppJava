package com.project.gymmembership.dao;

import com.project.gymmembership.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ReviewDaoImpl implements ReviewDao{

    private final EntityManager entityManager;

    @Autowired
    public ReviewDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void save(Review review) {
        entityManager.persist(review);
    }
}
