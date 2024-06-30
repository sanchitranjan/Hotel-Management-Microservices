package com.microservice.Hotel_Service.Service;

import com.microservice.Hotel_Service.Entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);

    List<Hotel> getAll();

    Hotel get(String id);
}
