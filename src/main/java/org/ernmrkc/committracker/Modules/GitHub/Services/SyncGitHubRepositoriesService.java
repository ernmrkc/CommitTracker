package org.ernmrkc.committracker.Modules.GitHub.Services;

import org.ernmrkc.committracker.Exceptions.DeveloperNotFoundException;
import org.ernmrkc.committracker.Modules.Commit.Models.Platform;
import org.ernmrkc.committracker.Modules.Developer.DeveloperRepository;
import org.ernmrkc.committracker.Modules.Developer.Models.Developer;
import org.ernmrkc.committracker.Modules.GitHub.QueryHandlers.GetGitHubRepositoriesQueryHandlers;
import org.ernmrkc.committracker.Modules.GitRepo.CommandHandlers.CreateGitRepoCommandHandler;
import org.ernmrkc.committracker.Modules.GitRepo.GitRepoRepository;
import org.ernmrkc.committracker.Modules.GitRepo.Models.GitRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SyncGitHubRepositoriesService {
    private final DeveloperRepository developerRepository;
    private final GitRepoRepository gitRepoRepository;
    private final GetGitHubRepositoriesQueryHandlers getGitHubRepositoriesQueryHandlers;
    private final CreateGitRepoCommandHandler createGitRepoCommandHandler;

    public SyncGitHubRepositoriesService(DeveloperRepository developerRepository,
                                         GitRepoRepository gitRepoRepository,
                                         GetGitHubRepositoriesQueryHandlers getGitHubRepositoriesQueryHandlers,
                                         CreateGitRepoCommandHandler createGitRepoCommandHandler) {
        this.developerRepository = developerRepository;
        this.gitRepoRepository = gitRepoRepository;
        this.getGitHubRepositoriesQueryHandlers = getGitHubRepositoriesQueryHandlers;
        this.createGitRepoCommandHandler = createGitRepoCommandHandler;
    }

    public void execute(String githubUsername) {

        List<GitRepo> repoList = getGitHubRepositoriesQueryHandlers.execute(githubUsername).getBody();
        Optional<Developer> developerOptional = developerRepository.findByGithubUsername(githubUsername);
        if (developerOptional.isEmpty()) {
            throw new DeveloperNotFoundException();
        }
        Developer developer = developerOptional.get();
        assert repoList != null;
        repoList.forEach(repo -> {
            repo.setPlatform(Platform.GITHUB);
            createGitRepoCommandHandler.execute(repo, null);
            developer.getRepoList().add(repo);
            repo.getDevelopers().add(developer);
            developerRepository.save(developer);
            gitRepoRepository.save(repo);
        });
    }
}
