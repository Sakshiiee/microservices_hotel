package com.lcwd.user.service.UserService.Service.impl;

import com.lcwd.user.service.UserService.Service.UserService;
import com.lcwd.user.service.UserService.entities.Hotel;
import com.lcwd.user.service.UserService.entities.Rating;
import com.lcwd.user.service.UserService.entities.User;
import com.lcwd.user.service.UserService.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.UserService.external.services.HotelService;
import com.lcwd.user.service.UserService.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

//    @Override
//    public User getUser(String userId) {
//        //get user from db usig userrepo
//        User user =  userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User is not available with given id!!"+userId));
//        //fetch rating of the above user from RATING SERVICE
//        //calling rating service from user service
//        //http://localhost:8083/ratings/users/d35af56e-bc07-41bd-9d3e-a83a72a8aa10
//       // Rating[] ratingsOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/" +user.getUserId(), Rating[].class);
//       Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" +user.getUserId(), Rating[].class);
//        logger.info("{}",ratingsOfUser);
//
//        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
//
//        List<Rating> ratingList = ratings.stream().map(rating -> {
//            //api call to hotel service to get the hotel
//
//            //http://localhost:8082/hotels/19f70984-9823-4335-98f1-27d3472fc3b9
//            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8082/hotels/19f70984-9823-4335-98f1-27d3472fc3b9", Hotel.class);
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/19f70984-9823-4335-98f1-27d3472fc3b9", Hotel.class);
//            Hotel hotel = forEntity.getBody();
//            logger.info("response status code: {}",forEntity.getStatusCode());
//
//            //set the hotel to rating
//            rating.setHotel(hotel);
//
//            //return the rating
//             return rating;
//
//        }).collect(Collectors.toList());
//        user.setRatings(ratingList);
//
//        return  user;
//    }


    @Override
    public User getUser(String userId) {
        //get user from db usig userrepo
        User user =  userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User is not available with given id!!"+userId));
        //fetch rating of the above user from RATING SERVICE
        //calling rating service from user service
        //http://localhost:8083/ratings/users/d35af56e-bc07-41bd-9d3e-a83a72a8aa10
        // Rating[] ratingsOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/" +user.getUserId(), Rating[].class);
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" +user.getUserId(), Rating[].class);
        logger.info("{}",ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel

            //http://localhost:8082/hotels/19f70984-9823-4335-98f1-27d3472fc3b9
            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8082/hotels/19f70984-9823-4335-98f1-27d3472fc3b9", Hotel.class);
            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/19f70984-9823-4335-98f1-27d3472fc3b9", Hotel.class);

            Hotel hotel = hotelService.getHotel(rating.getHotelId());


<<<<<<< HEAD
           // logger.info("response status code: {}",forEntity.getStatusCode());
=======
            // logger.info("response status code: {}",forEntity.getStatusCode());
>>>>>>> 0dd046edfd830f67dceca0f850458a98bcf2012f

            //set the hotel to rating
            rating.setHotel(hotel);

            //return the rating
            return rating;

        }).collect(Collectors.toList());
        user.setRatings(ratingList);

        return  user;
    }
}
