package com.project.SafetyNet.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RessourceNotFoundException extends Exception {
    public RessourceNotFoundException(String message) {
        super(message);

    }

}
