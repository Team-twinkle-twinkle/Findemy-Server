package com.founderz.findemy.global.exception.domain.auth;

public class NotAuthenticatedException extends RuntimeException {

    public NotAuthenticatedException(String message) {
        super(message);
    }
}