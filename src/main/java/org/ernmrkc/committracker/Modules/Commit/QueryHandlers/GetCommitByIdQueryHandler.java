package org.ernmrkc.committracker.Modules.Commit.QueryHandlers;

import org.ernmrkc.committracker.Modules.Commit.CommitRepository;
import org.ernmrkc.committracker.Modules.Commit.Models.Commit;
import org.ernmrkc.committracker.Modules.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetCommitByIdQueryHandler implements Query<Long, Commit> {
    private final CommitRepository commitRepository;

    public GetCommitByIdQueryHandler(CommitRepository commitRepository) {
        this.commitRepository = commitRepository;
    }

    @Override
    public ResponseEntity<Commit> execute(Long id) {
        Optional<Commit> commitOptional = commitRepository.getCommitById(id);
        if (commitOptional.isEmpty()){
            throw new RuntimeException("Commit not found");
        }
        Commit commit = commitOptional.get();
        return ResponseEntity.ok().body(commit);
    }
}
