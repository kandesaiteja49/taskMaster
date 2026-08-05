package com.taskmaster.controller;

import com.taskmaster.dto.TeamRequest;
import com.taskmaster.model.*;
import com.taskmaster.repository.UserRepository;
import com.taskmaster.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;
    private final UserRepository userRepository;

    public TeamController(TeamService teamService, UserRepository userRepository) {
        this.teamService = teamService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(Authentication authentication, @RequestBody TeamRequest request) {
        User owner = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Team team = Team.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        return ResponseEntity.ok(teamService.createTeam(team, owner));
    }

    @PostMapping("/{teamId}/join")
    public ResponseEntity<Team> joinTeam(Authentication authentication, @PathVariable UUID teamId) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(teamService.joinTeam(teamId, user));
    }

    @GetMapping
    public ResponseEntity<Set<Team>> getMyTeams(Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(teamService.getUserTeams(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable UUID id) {
        return ResponseEntity.ok(teamService.getTeamById(id));
    }
}
