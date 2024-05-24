package org.ernmrkc.committracker.Modules.GitHub.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCommitCommand {
    private String username;
    private String repoName;
}
