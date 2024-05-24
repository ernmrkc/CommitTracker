package org.ernmrkc.committracker.Modules.Commit.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ernmrkc.committracker.Modules.Developer.Models.Developer;
import org.ernmrkc.committracker.Modules.GitHub.Models.GetCommitDataCommand;
import org.ernmrkc.committracker.Modules.GitRepo.Models.GitRepo;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "commits")
public class Commit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commit_id")
    private Long id;

    @NotBlank(message = "Hash cannot be empty")
    @Column(nullable = false, unique = true)
    private String hash;

    @Column(name = "timestamp", nullable = false, updatable = false)
    private LocalDateTime timestamp;

    @Column(name = "message", nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "developer_id", nullable = false)
    @JsonIgnore
    private Developer developer;

    @ManyToOne
    @JoinColumn(name = "repository_id", nullable = false)
    private GitRepo gitRepo;

    @Enumerated(EnumType.STRING)
    @Column(name = "platform", nullable = false)
    private Platform platform;

    public Commit(GetCommitDataCommand getCommitDataCommand){
        this.hash = getCommitDataCommand.getSha();
        this.message = getCommitDataCommand.getCommit().getMessage();
        this.timestamp = getCommitDataCommand.getCommit().getAuthor().getDate();
    }
}
