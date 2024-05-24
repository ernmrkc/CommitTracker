package org.ernmrkc.committracker.Modules.Patch.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.ernmrkc.committracker.Modules.Commit.Models.Commit;

@Entity
@Data
@Table(name = "patch")
public class Patch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Patch details cannot be empty")
    @Column(name = "details", nullable = false)
    private String details;

    @ManyToOne
    @JoinColumn(name = "commit_id", nullable = false)
    private Commit commit;
}
