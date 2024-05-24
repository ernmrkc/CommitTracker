package org.ernmrkc.committracker.Modules.GitLab.QueryHandlers;

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
public class GetGitLabRepositoriesQueryHandlers implements Query<String, List<GitRepo>> {

    @Value("\"${gitlab.api.url}\"")
    private String gitLabApiUrl;

    @Value("${gitlab.token}")
    private String gitLabToken;
    private final RestTemplate restTemplate;

    public GetGitLabRepositoriesQueryHandlers(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<List<GitRepo>> execute(String gitlabUsername) {
        final String url = gitLabApiUrl + "/users/" + gitlabUsername + "/projects";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(gitLabToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        GitRepo[] repos = restTemplate.exchange(url, HttpMethod.GET, entity, GitRepo[].class).getBody();
        assert repos != null;
        List<GitRepo> gitRepoList = Arrays.asList(repos);
        return ResponseEntity.ok().body(gitRepoList);
    }
}
