package org.ernmrkc.committracker.Modules.Commit;

import org.ernmrkc.committracker.Modules.Commit.Models.Commit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommitRepository extends JpaRepository<Commit, Long> {
    Optional<Commit> getCommitById(Long id);
}
