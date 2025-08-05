package com.lcwd.ratings.RatingService.services.impl;

import com.lcwd.ratings.RatingService.entities.Rating;
import com.lcwd.ratings.RatingService.repositories.RatingRepositories;
import com.lcwd.ratings.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepositories ratingRepositories;

    @Override
    public Rating create(Rating rating) {
        return ratingRepositories.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepositories.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepositories.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingsByHotelId(String hotelId) {
        return ratingRepositories.findByHotelId(hotelId);
    }
}
