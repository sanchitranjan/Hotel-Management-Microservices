package com.microservice.Hotel_Service.Exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String s) {
        super(s);
    }

    public ResourceNotFoundException() {
        super("Resource Not found !!");
    }
}
