package org.ernmrkc.committracker.Modules.GitHub.QueryHandlers;

import org.ernmrkc.committracker.Modules.GitRepo.Models.GitRepo;
import org.ernmrkc.committracker.Modules.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class GetGitHubRepositoriesQueryHandlers implements Query<String, List<GitRepo>> {
    @Value("${github.api.url}")
    private String gitHubApiUrl;

    @Value("${github.token}")
    private String gitHubToken;
    private final RestTemplate restTemplate;

    public GetGitHubRepositoriesQueryHandlers(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<List<GitRepo>> execute(String githubUsername) {
        final String url = gitHubApiUrl + "/users/" + githubUsername + "/repos";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(gitHubToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        GitRepo[] repos = restTemplate.exchange(url, HttpMethod.GET, entity, GitRepo[].class).getBody();
        assert repos != null;
        List<GitRepo> gitRepoList = Arrays.asList(repos);
        return ResponseEntity.ok().body(gitRepoList);
    }
}
