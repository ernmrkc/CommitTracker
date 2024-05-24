package org.ernmrkc.committracker.Modules.GitHub.Services;

import org.ernmrkc.committracker.Exceptions.DeveloperNotFoundException;
import org.ernmrkc.committracker.Modules.Commit.CommitRepository;
import org.ernmrkc.committracker.Modules.Commit.Models.Commit;
import org.ernmrkc.committracker.Modules.Commit.Models.Platform;
import org.ernmrkc.committracker.Modules.Developer.DeveloperRepository;
import org.ernmrkc.committracker.Modules.Developer.Models.Developer;
import org.ernmrkc.committracker.Modules.GitHub.Models.GetCommitCommand;
import org.ernmrkc.committracker.Modules.GitHub.Models.GetCommitDataCommand;
import org.ernmrkc.committracker.Modules.GitHub.QueryHandlers.GetGitHubCommitsQueryHandler;
import org.ernmrkc.committracker.Modules.GitRepo.Models.GitRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SyncGitHubCommitsService {
    private final DeveloperRepository developerRepository;
    private final GetGitHubCommitsQueryHandler getGitHubCommitsQueryHandler;
    private final CommitRepository commitRepository;

    public SyncGitHubCommitsService(DeveloperRepository developerRepository,
                                    GetGitHubCommitsQueryHandler getGitHubCommitsQueryHandler,
                                    CommitRepository commitRepository) {
        this.developerRepository = developerRepository;
        this.getGitHubCommitsQueryHandler = getGitHubCommitsQueryHandler;
        this.commitRepository = commitRepository;
    }

    public void execute(String githubUsername){
        Optional<Developer> developerOptional = developerRepository.findByGithubUsername(githubUsername);
        if (developerOptional.isEmpty()){
            throw new DeveloperNotFoundException();
        }
        Developer developer = developerOptional.get();
        for (GitRepo repository : developer.getRepoList()){
            GetCommitCommand getCommitCommand = new GetCommitCommand(githubUsername, repository.getName());
            GetCommitDataCommand[] commitData = getGitHubCommitsQueryHandler.execute(getCommitCommand).getBody();

            if (commitData != null){
                for(GetCommitDataCommand commitDataCommand : commitData){
                    Commit commit = new Commit(commitDataCommand);
                    commit.setGitRepo(repository);
                    commit.setDeveloper(developer);
                    commit.setPlatform(Platform.GITHUB);
                    commitRepository.save(commit);
                }
            }
        }
    }
}
