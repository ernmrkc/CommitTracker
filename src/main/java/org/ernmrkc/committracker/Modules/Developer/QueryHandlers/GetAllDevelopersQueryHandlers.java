package org.ernmrkc.committracker.Modules.Developer.QueryHandlers;

import org.ernmrkc.committracker.Modules.Developer.DeveloperRepository;
import org.ernmrkc.committracker.Modules.Developer.Models.Developer;
import org.ernmrkc.committracker.Modules.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllDevelopersQueryHandlers implements Query<Void, List<Developer>> {
    private final DeveloperRepository developerRepository;

    public GetAllDevelopersQueryHandlers(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public ResponseEntity<List<Developer>> execute(Void input) {
        List<Developer> developerList = developerRepository.findAll();
        return ResponseEntity.ok().body(developerList);
    }
}
