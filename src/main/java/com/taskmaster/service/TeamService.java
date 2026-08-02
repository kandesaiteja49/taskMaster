package com.taskmaster.service;

import com.taskmaster.exception.ResourceNotFoundException;
import com.taskmaster.model.*;
import com.taskmaster.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    @Transactional
    public Team createTeam(Team team, User owner) {
        team.setOwner(owner);
        team.getMembers().add(owner);
        return teamRepository.save(team);
    }

    @Transactional
    public Team joinTeam(UUID teamId, User user) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + teamId));
        team.getMembers().add(user);
        return teamRepository.save(team);
    }

    public Set<Team> getUserTeams(User user) {
        return teamRepository.findAll().stream()
                .filter(team -> team.getMembers().contains(user))
                .collect(java.util.stream.Collectors.toSet());
    }

    public Team getTeamById(UUID id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + id));
    }
}
