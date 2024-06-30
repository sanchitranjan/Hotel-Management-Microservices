package com.user.service.User.Service.Services.Impl;

import com.user.service.User.Service.Entities.Hotel;
import com.user.service.User.Service.Entities.Rating;
import com.user.service.User.Service.Entities.User;
import com.user.service.User.Service.Exceptions.ResourceNotFoundException;
import com.user.service.User.Service.External.Services.HotelService;
import com.user.service.User.Service.Repositories.UserRepository;
import com.user.service.User.Service.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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

        // generate unique userId
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        //implement rating service call: using Rest Template
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {

        //fetch user from db with help of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given Id not found on server !! : " + userId));

        //fetch rating of the above user from Rating Service
        //http://localhost:8083/ratings/users/b7914f46-387f-49ef-8c35-4303e1f0ba84

        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class );
        logger.info(" {} ", ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {

            //api call to hotel service to get hotel
            //http://localhost:8082/hotels/4689bd1b-370a-4408-9026-fc231bf23953
            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+ rating.getHotelId(), Hotel.class);

            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            //Hotel hotel = forEntity.getBody();
            // logger.info("response status code: {} ", forEntity.getStatusCode());

            //set the hotel to rating
            rating.setHotel(hotel);

            //return the rating
            return rating;
        }).collect(Collectors.toList());


        user.setRatings(ratingList);

        return user;
    }
}
