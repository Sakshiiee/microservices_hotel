package com.lcwd.ratings.RatingService.services;

import com.lcwd.ratings.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating create(Rating rating);

    //get all ratings
    List<Rating> getRatings();

    //get all by user id
    List<Rating> getRatingByUserId(String userId);

    //get all by hotels
    List<Rating> getRatingsByHotelId(String hotelId);
}
