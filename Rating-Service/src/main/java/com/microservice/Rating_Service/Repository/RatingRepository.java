package com.microservice.Rating_Service.Repository;

import com.microservice.Rating_Service.Entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, String> {

    // custom finder methods
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);

}
