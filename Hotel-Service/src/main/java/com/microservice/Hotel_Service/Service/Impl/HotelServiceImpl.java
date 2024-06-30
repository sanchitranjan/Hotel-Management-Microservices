package com.microservice.Hotel_Service.Service.Impl;

import com.microservice.Hotel_Service.Entities.Hotel;
import com.microservice.Hotel_Service.Exceptions.ResourceNotFoundException;
import com.microservice.Hotel_Service.Repository.HotelRepository;
import com.microservice.Hotel_Service.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel with the given Id not found !!"));
    }
}
