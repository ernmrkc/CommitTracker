package org.ernmrkc.committracker.Modules.Developer.CommandHandlers;

import org.ernmrkc.committracker.Modules.Command;
import org.ernmrkc.committracker.Modules.Developer.DeveloperRepository;
import org.ernmrkc.committracker.Modules.Developer.Models.Developer;
import org.ernmrkc.committracker.Validations.DeveloperValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class CreateDeveloperCommandHandler implements Command<Developer, BindingResult, Developer> {
    private final DeveloperRepository developerRepository;
    private final DeveloperValidationService developerValidationService;

    public CreateDeveloperCommandHandler(DeveloperRepository developerRepository,
                                         DeveloperValidationService developerValidationService) {
        this.developerRepository = developerRepository;
        this.developerValidationService = developerValidationService;
    }

    @Override
    public ResponseEntity<Developer> execute(Developer developer, BindingResult bindingResult) {
        developerValidationService.validateDeveloper(developer, bindingResult);
        developerRepository.save(developer);
        return ResponseEntity.ok().body(developer);
    }
}
