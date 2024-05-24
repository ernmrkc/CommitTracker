package org.ernmrkc.committracker.Modules.GitLab;

import org.ernmrkc.committracker.Modules.GitLab.QueryHandlers.GetGitLabRepositoriesQueryHandlers;
import org.ernmrkc.committracker.Modules.GitRepo.Models.GitRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GitLabController {
    private final GetGitLabRepositoriesQueryHandlers getGitLabRepositoriesQueryHandlers;

    public GitLabController(GetGitLabRepositoriesQueryHandlers getGitLabRepositoriesQueryHandlers) {
        this.getGitLabRepositoriesQueryHandlers = getGitLabRepositoriesQueryHandlers;
    }

}
