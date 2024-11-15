package com.teamtreehouse.model;


import java.util.*;

public class TeamsManager {
    private final TreeSet<Team> allTeams = new TreeSet<>();

    // Team building method
    public void teamBuilding() {

        List<String> teamsName = Arrays.asList("Falcons", " Bears", "Sharks");
        List<String> coachName = Arrays.asList("Max", "Sam", "Zoe");
        for (int i = 0; i < 3; i++) {
            TreeSet<Player> teamsPlayers = new TreeSet<>();
            Team team = new Team(teamsName.get(i), coachName.get(i), teamsPlayers, "Team " + teamsName.get(i) + " coached by " + coachName.get(i) + " created.");
            team.addPlayersAutomatically(teamsPlayers);

            allTeams.add(team);

        }
    }

    // Method to obtain the list of all teams
    public TreeSet<Team> getAllTeams() {
        return allTeams;
    }

    //Method that adds a created team to the list of all the teams.
    public boolean addTeamToAllTeams(Team team) {

        return allTeams.add(team);
    }

    //Method in charge of creating the balance of all teams
    public Map<String, List<Integer>> createBalance(TreeSet<Team> allTeams) {
        TreeSet<Player> teamPlayers;
        Map<String, List<Integer>> balanceMap = new TreeMap<>();
        String nameTeam;

        for (Team team : allTeams) {
            int numExperienced = 0;
            int numInexperienced = 0;

            nameTeam = team.getTeamName();
            teamPlayers = team.getTeamPlayers();
            List<Integer> counts = Arrays.asList(0, 0);

            for (Player player : teamPlayers) {
                if (player.isPreviousExperience()) {
                    numExperienced++;
                } else {
                    numInexperienced++;
                }
            }
            counts.set(0, numExperienced);
            counts.set(1, numInexperienced);
            balanceMap.put(nameTeam, counts);

        }
        return balanceMap;
    }

}
