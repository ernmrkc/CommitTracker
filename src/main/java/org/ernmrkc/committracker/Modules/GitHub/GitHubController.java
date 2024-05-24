package org.ernmrkc.committracker.Modules.GitHub;

import org.ernmrkc.committracker.Modules.GitHub.Services.SyncGitHubCommitsService;
import org.ernmrkc.committracker.Modules.GitHub.Services.SyncGitHubRepositoriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GitHubController {
    private final SyncGitHubRepositoriesService syncGitHubRepositoriesService;
    private final SyncGitHubCommitsService syncGitHubCommitsService;

    public GitHubController(SyncGitHubRepositoriesService syncGitHubRepositoriesService,
                            SyncGitHubCommitsService syncGitHubCommitsService) {
        this.syncGitHubRepositoriesService = syncGitHubRepositoriesService;
        this.syncGitHubCommitsService = syncGitHubCommitsService;
    }

    @GetMapping("/sync-repositories")
    public ResponseEntity<String> syncUserRepositories(@RequestParam(value = "githubUsername") String githubUsername){
        try {
            syncGitHubRepositoriesService.execute(githubUsername);
            return ResponseEntity.ok("Repositories synced successfully for GitHub user: " + githubUsername);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error syncing repositories: " + e.getMessage());
        }
    }

    @GetMapping("/sync-commits")
    public ResponseEntity<String> syncUserCommits(@RequestParam(value = "githubUsername") String githubUsername){
        try {
            syncGitHubCommitsService.execute(githubUsername);
            return ResponseEntity.ok("Commits synced successfully for GitHub user: " + githubUsername);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error syncing Commits: " + e.getMessage());
        }
    }

}


