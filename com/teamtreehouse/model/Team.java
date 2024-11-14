package com.teamtreehouse.model;

import java.util.Set;

public class Team {
    private String mTeamName;
    private String mCoachName;
    private Set<Player> mTeamPlayers;
    private String mDescription;

    Prompter prompter = new Prompter();
    public Team(String teamName,String coachName,Set<Player> teamPlayers,  String description) {
        mTeamName = teamName;
        mTeamPlayers = teamPlayers;
        mCoachName = coachName;
        mDescription = description;
    }

    public String getTeamName() {
        return mTeamName;
    }

    public String getCoachName() {
        return mCoachName;
    }

    public Set<Player> getTeamPlayers() {
        return mTeamPlayers;
    }

    public String getDescription() {
        return mDescription;
    }

    public void addPlayerToTeam(Player player){

        mTeamPlayers.add(player);
        prompter.AddedPlayer();

    }

    public boolean completeTeam(){

        int sizeTeam = mTeamPlayers.size();

        return sizeTeam >= 11;

    }

    public void removePlayerToTeam(Player playerToRemove) {
        mTeamPlayers.remove(playerToRemove);
        prompter.playerRemoved();
    }
}
