package org.ernmrkc.committracker.Modules.Developer.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.ernmrkc.committracker.Modules.Commit.Models.Commit;
import org.ernmrkc.committracker.Modules.GitRepo.Models.GitRepo;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "developers")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "developer_id")
    private Long id;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;

    @Column(name = "github_username", unique = true)
    private String githubUsername;

    @Column(name = "gitlab_username", unique = true)
    private String gitlabUsername;

    @OneToMany(mappedBy = "developer")
    private List<Commit> commitList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "developer_gitrepo",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "repository_id")
    )
    private List<GitRepo> repoList = new ArrayList<>();
}
