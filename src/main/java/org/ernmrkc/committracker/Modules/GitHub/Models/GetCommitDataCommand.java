package org.ernmrkc.committracker.Modules.GitHub.Models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetCommitDataCommand {
    private String sha;
    private CommitData commit;

    @Data
    public static class CommitData{
        private String message;
        private Author author;
    }

    @Data
    public static class Author{
        private String name;
        private LocalDateTime date;
    }
}
