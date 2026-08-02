package com.taskmaster.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "teams")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToMany
    @JoinTable(
        name = "team_members",
        joinColumns = @JoinColumn(name = "team_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> members = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
