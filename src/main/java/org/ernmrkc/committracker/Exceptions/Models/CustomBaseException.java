package org.ernmrkc.committracker.Exceptions.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CustomBaseException extends RuntimeException{
    private HttpStatus status;
    private SimpleResponse simpleResponse;
}
