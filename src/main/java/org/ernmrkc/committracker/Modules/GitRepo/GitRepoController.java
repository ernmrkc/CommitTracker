package org.ernmrkc.committracker.Modules.GitRepo;

import org.ernmrkc.committracker.Modules.GitRepo.Models.GitRepo;
import org.ernmrkc.committracker.Modules.GitRepo.QueryHandlers.GetAllRepositoriesQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gitrepos")
public class GitRepoController {
    private final GetAllRepositoriesQueryHandler getAllRepositoriesQueryHandler;

    public GitRepoController(GetAllRepositoriesQueryHandler getAllRepositoriesQueryHandler) {
        this.getAllRepositoriesQueryHandler = getAllRepositoriesQueryHandler;
    }

    @GetMapping("/get-all-repos")
    public ResponseEntity<List<GitRepo>> getAllGitRepos(){
        return getAllRepositoriesQueryHandler.execute(null);
    }
}
