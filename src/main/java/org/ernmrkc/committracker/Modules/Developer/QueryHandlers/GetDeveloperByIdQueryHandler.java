package org.ernmrkc.committracker.Modules.Developer.QueryHandlers;

import org.ernmrkc.committracker.Exceptions.DeveloperNotFoundException;
import org.ernmrkc.committracker.Modules.Developer.DeveloperRepository;
import org.ernmrkc.committracker.Modules.Developer.Models.Developer;
import org.ernmrkc.committracker.Modules.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetDeveloperByIdQueryHandler implements Query<Long, Developer> {
    private final DeveloperRepository developerRepository;

    public GetDeveloperByIdQueryHandler(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public ResponseEntity<Developer> execute(Long id) {
        Optional<Developer> developerOptional = developerRepository.findById(id);
        if (developerOptional.isEmpty()){
            throw new DeveloperNotFoundException();
        }
        Developer developer = developerOptional.get();
        return ResponseEntity.ok().body(developer);
    }
}
