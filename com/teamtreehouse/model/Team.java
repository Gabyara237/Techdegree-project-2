package com.teamtreehouse.model;

import java.util.HashSet;
import java.util.Set;

public class Team {
    private String mTeamName;
    private String mCoachName;
    private Set<Player> mTeam;

    public Team(String teamName,String coachName,Set<Player> team) {
        mTeamName = teamName;
        mTeam = team;
        mCoachName = coachName;
    }

    public String getTeamName() {
        return mTeamName;
    }

    public String getCoachName() {
        return mCoachName;
    }

    public Set getTeam() {
        return mTeam;
    }


    public Set<Player> addPlayer(Player player, Set<Player> team){
        team.add(player);
        return team;
    }

    public boolean completeTeam(Set<Player> team){

        int sizeTeam = team.size();

        return sizeTeam >= 11;

    }


}
