package org.ernmrkc.committracker.Exceptions;

import org.ernmrkc.committracker.Exceptions.Models.CustomBaseException;
import org.ernmrkc.committracker.Exceptions.Models.SimpleResponse;
import org.springframework.http.HttpStatus;

public class DeveloperDataNotValidException extends CustomBaseException {
    public DeveloperDataNotValidException(String message) {
        super(HttpStatus.BAD_REQUEST, new SimpleResponse(message));
    }
}
