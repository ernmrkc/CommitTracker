package org.ernmrkc.committracker.Modules.GitHub.QueryHandlers;

import org.ernmrkc.committracker.Modules.GitHub.Models.GetCommitCommand;
import org.ernmrkc.committracker.Modules.GitHub.Models.GetCommitDataCommand;
import org.ernmrkc.committracker.Modules.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class GetGitHubCommitsQueryHandler implements Query<GetCommitCommand, GetCommitDataCommand[]>{

    @Value("${github.api.url}")
    private String gitHubApiUrl;

    @Value("${github.token}")
    private String gitHubToken;
    private final RestTemplate restTemplate;

    public GetGitHubCommitsQueryHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<GetCommitDataCommand[]> execute(GetCommitCommand getCommitCommand) {
        String sinceDate = ZonedDateTime.now().minusMonths(1).format(DateTimeFormatter.ISO_INSTANT);
        String url = String.format("%s/repos/%s/%s/commits?since=%s",
                gitHubApiUrl, getCommitCommand.getUsername(), getCommitCommand.getRepoName(), sinceDate);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(gitHubToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        GetCommitDataCommand[] commitData = restTemplate.exchange(url, HttpMethod.GET, entity, GetCommitDataCommand[].class).getBody();
        return ResponseEntity.ok().body(commitData);
    }
}
