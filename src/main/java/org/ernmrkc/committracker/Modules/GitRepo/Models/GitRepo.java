package org.ernmrkc.committracker.Modules.GitRepo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.ernmrkc.committracker.Modules.Commit.Models.Platform;
import org.ernmrkc.committracker.Modules.Developer.Models.Developer;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "git_repositories")
public class GitRepo {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "repository_id")
    private Long id;

    @NotBlank(message = "Repository name cannot be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "repoList", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Developer> developers = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "platform", nullable = false)
    private Platform platform;
}
