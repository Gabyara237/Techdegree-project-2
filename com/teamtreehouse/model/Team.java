package com.teamtreehouse.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Team implements Comparable<Team> {
    private String mTeamName;
    private String mCoachName;
    private TreeSet<Player> mTeamPlayers;
    private String mDescription;

    Prompter prompter = new Prompter();

    public Team(String teamName, String coachName, TreeSet<Player> teamPlayers, String description) {
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

    public TreeSet<Player> getTeamPlayers() {
        return mTeamPlayers;
    }

    public String getDescription() {
        return mDescription;
    }

    public void addPlayerToTeam(Player player) {
        mTeamPlayers.add(player);
        prompter.AddedPlayer();
    }

    public boolean completeTeam() {
        int sizeTeam = mTeamPlayers.size();
        return sizeTeam >= 11;
    }

    public void removePlayerToTeam(Player playerToRemove) {
        mTeamPlayers.remove(playerToRemove);
        prompter.playerRemoved();
    }

    public Map<String, TreeSet<Player>> createReport(Team team){

        Map<String, TreeSet<Player>> reportMap = new TreeMap<>();
        TreeSet<Player> group1 = new TreeSet<>();
        TreeSet<Player> group2 = new TreeSet<>();
        TreeSet<Player> listPlayers = team.mTeamPlayers;

        reportMap.put("35-41",group1);
        reportMap.put("42-47",group2);

        for( Player player: listPlayers ){
            if (player.getHeightInInches()> 41){
                group2.add(player);
            }else{
                group1.add(player);
            }
        }
        return reportMap;
    }

    @Override
    public int compareTo(Team o) {
        return this.mTeamName.compareTo(o.mTeamName);
    }


}
