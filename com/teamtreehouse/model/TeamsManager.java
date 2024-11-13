package com.teamtreehouse.model;

import java.util.HashSet;
import java.util.Set;

public class TeamsManager {
    private Set<Team> allTeams = new HashSet<>();

    public Set<Team> getAllTeams() {
        return allTeams;
    }

    public void addTeamToAllTeams(Team team){

        allTeams.add(team);
    }
}
