package org.ernmrkc.committracker.Modules.GitRepo;

import org.ernmrkc.committracker.Modules.GitRepo.Models.GitRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GitRepoRepository extends JpaRepository<GitRepo, Long> {
}
