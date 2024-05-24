package org.ernmrkc.committracker.Modules.Developer.CommandHandlers;

import org.ernmrkc.committracker.Exceptions.DeveloperNotFoundException;
import org.ernmrkc.committracker.Modules.Command;
import org.ernmrkc.committracker.Modules.Developer.DeveloperRepository;
import org.ernmrkc.committracker.Modules.Developer.Models.Developer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteDeveloperCommandHandler implements Command<Long, Void, Void> {
    private final DeveloperRepository developerRepository;

    public DeleteDeveloperCommandHandler(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Long id, Void bindingResult) {
        Optional<Developer> developerOptional = developerRepository.findById(id);
        if (developerOptional.isEmpty()){
            throw new DeveloperNotFoundException();
        }
        Developer developer = developerOptional.get();
        developerRepository.delete(developer);
        return ResponseEntity.ok().build();
    }
}
