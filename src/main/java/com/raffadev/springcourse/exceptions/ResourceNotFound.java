package com.raffadev.springcourse.exceptions;

public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String message) {

        super(message);
    }
}
