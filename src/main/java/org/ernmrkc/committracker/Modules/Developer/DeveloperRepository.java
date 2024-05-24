package org.ernmrkc.committracker.Modules.Developer;

import org.ernmrkc.committracker.Modules.Developer.Models.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    Optional<Developer> findByGithubUsername(String githubUsername);
}
