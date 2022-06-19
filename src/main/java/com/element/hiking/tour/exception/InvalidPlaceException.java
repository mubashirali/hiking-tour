package com.element.hiking.tour.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidPlaceException extends RuntimeException {
    private static final long serialVersionUID = -4960243231605273790L;

    public InvalidPlaceException(String message) {
        super(message);
    }
}
