package com.example.nuxel.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class PasswordDoNotMatchException extends Exception{

    public PasswordDoNotMatchException(String message) {
        super(message);
    }
}
