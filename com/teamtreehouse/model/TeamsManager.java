package com.teamtreehouse.model;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TeamsManager {
    private TreeSet<Team> allTeams = new TreeSet<>();

    public TreeSet<Team> getAllTeams() {
        return allTeams;
    }

    public void addTeamToAllTeams(Team team){

        allTeams.add(team);
    }

}
