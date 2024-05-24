package org.ernmrkc.committracker.Modules.GitRepo.CommandHandlers;

import org.ernmrkc.committracker.Modules.Command;
import org.ernmrkc.committracker.Modules.GitRepo.GitRepoRepository;
import org.ernmrkc.committracker.Modules.GitRepo.Models.GitRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateGitRepoCommandHandler implements Command<GitRepo, Void, Void> {
    private final GitRepoRepository gitRepoRepository;

    public CreateGitRepoCommandHandler(GitRepoRepository gitRepoRepository) {
        this.gitRepoRepository = gitRepoRepository;
    }

    @Override
    public ResponseEntity<Void> execute(GitRepo gitRepo, Void bindingResult) {
        gitRepoRepository.save(gitRepo);
        return ResponseEntity.ok().build();
    }
}
