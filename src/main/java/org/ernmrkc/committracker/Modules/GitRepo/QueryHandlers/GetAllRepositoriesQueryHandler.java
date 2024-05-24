package org.ernmrkc.committracker.Modules.GitRepo.QueryHandlers;

import org.ernmrkc.committracker.Modules.GitRepo.GitRepoRepository;
import org.ernmrkc.committracker.Modules.GitRepo.Models.GitRepo;
import org.ernmrkc.committracker.Modules.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllRepositoriesQueryHandler implements Query<Void, List<GitRepo>> {
    private final GitRepoRepository gitRepoRepository;

    public GetAllRepositoriesQueryHandler(GitRepoRepository gitRepoRepository) {
        this.gitRepoRepository = gitRepoRepository;
    }

    @Override
    public ResponseEntity<List<GitRepo>> execute(Void input) {
        List<GitRepo> gitRepoList = gitRepoRepository.findAll();
        return ResponseEntity.ok().body(gitRepoList);
    }
}
