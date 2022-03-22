package com.project.gymmembership.service;

import com.project.gymmembership.dao.ReviewDao;
import com.project.gymmembership.entity.Review;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService{

    private ReviewDao reviewDao;

    public ReviewServiceImpl(ReviewDao reviewDao){
        this.reviewDao = reviewDao;
    }

    @Override
    @Transactional
    public void save(Review review) {
        reviewDao.save(review);
    }
}
