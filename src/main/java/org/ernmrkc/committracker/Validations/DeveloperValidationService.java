package org.ernmrkc.committracker.Validations;

import org.ernmrkc.committracker.Exceptions.DeveloperDataNotValidException;
import org.ernmrkc.committracker.Modules.Developer.CommandHandlers.CreateDeveloperCommandHandler;
import org.ernmrkc.committracker.Modules.Developer.Models.Developer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.stream.Collectors;

@Service
public class DeveloperValidationService {
    private static final Logger logger = LoggerFactory.getLogger(CreateDeveloperCommandHandler.class);

    public void validateDeveloper(Developer developer, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            errorMessage += "--> Developer Data: " + developer;
            System.out.println(errorMessage);
            throw new DeveloperDataNotValidException(errorMessage);
        }
    }
}
