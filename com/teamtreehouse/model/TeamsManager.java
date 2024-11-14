package com.teamtreehouse.model;

import java.util.*;

public class TeamsManager {
    private TreeSet<Team> allTeams = new TreeSet<>();

    public TreeSet<Team> getAllTeams() {
        return allTeams;
    }

    public void addTeamToAllTeams(Team team){

        allTeams.add(team);
    }

    public Map<String, List<Integer>> createBalance(TreeSet<Team>  allTeams){
        TreeSet<Player> teamPlayers;
        Map<String, List<Integer>>  balanceMap = new TreeMap<>();
        String nameTeam;

        for(Team team: allTeams){
            int numExperienced= 0;
            int numInexperienced= 0;

            nameTeam = team.getTeamName();
            teamPlayers= team.getTeamPlayers();
            List<Integer> counts = Arrays.asList(0,0);

            for (Player player: teamPlayers){
                if (player.isPreviousExperience()){
                    numExperienced++;
                }else {
                    numInexperienced++;
                }
            }
            counts.set(0,numExperienced);
            counts.set(1,numInexperienced);
            balanceMap.put(nameTeam,counts);

        }
        return balanceMap;
    }

}
