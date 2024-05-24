package org.ernmrkc.committracker.Exceptions;

import org.ernmrkc.committracker.Exceptions.Models.CustomBaseException;
import org.ernmrkc.committracker.Exceptions.Models.SimpleResponse;
import org.springframework.http.HttpStatus;

public class DeveloperNotFoundException extends CustomBaseException {
    public DeveloperNotFoundException() {
        super(HttpStatus.BAD_REQUEST, new SimpleResponse("Developer not found"));
    }
}
