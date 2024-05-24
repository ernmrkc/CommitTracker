package org.ernmrkc.committracker.Exceptions.ExceptionHandler;

import org.ernmrkc.committracker.Exceptions.DeveloperDataNotValidException;
import org.ernmrkc.committracker.Exceptions.Models.CustomBaseException;
import org.ernmrkc.committracker.Exceptions.Models.SimpleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DeveloperDataNotValidException.class)
    public ResponseEntity<SimpleResponse> handleDeveloperDataNotValidException(CustomBaseException exception){
        return ResponseEntity.status(exception.getStatus()).body(exception.getSimpleResponse());
    }
}
