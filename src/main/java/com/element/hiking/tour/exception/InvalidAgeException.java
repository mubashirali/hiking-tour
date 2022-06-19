package com.element.hiking.tour.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidAgeException extends RuntimeException {
    private static final long serialVersionUID = -4960243231605273790L;

    public InvalidAgeException(String message) {
        super(message);
    }
}
