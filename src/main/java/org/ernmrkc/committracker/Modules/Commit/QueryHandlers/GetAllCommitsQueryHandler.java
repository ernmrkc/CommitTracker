package org.ernmrkc.committracker.Modules.Commit.QueryHandlers;

import org.ernmrkc.committracker.Modules.Commit.CommitRepository;
import org.ernmrkc.committracker.Modules.Commit.Models.Commit;
import org.ernmrkc.committracker.Modules.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllCommitsQueryHandler implements Query<Void, List<Commit>> {
    private final CommitRepository commitRepository;

    public GetAllCommitsQueryHandler(CommitRepository commitRepository) {
        this.commitRepository = commitRepository;
    }

    @Override
    public ResponseEntity<List<Commit>> execute(Void input) {
        List<Commit> commitList = commitRepository.findAll();
        return ResponseEntity.ok().body(commitList);
    }
}
