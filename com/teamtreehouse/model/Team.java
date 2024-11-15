package com.teamtreehouse.model;

import java.util.*;

public class Team implements Comparable<Team> {
    private String mTeamName;
    private String mCoachName;
    private TreeSet<Player> mTeamPlayers;
    private String mDescription;

    Prompter prompter = new Prompter();

    // Constructor
    public Team(String teamName, String coachName, TreeSet<Player> teamPlayers, String description) {
        mTeamName = teamName;
        mTeamPlayers = teamPlayers;
        mCoachName = coachName;
        mDescription = description;
    }

    // ------ Getters ------

    public String getTeamName() {
        return mTeamName;
    }

    public TreeSet<Player> getTeamPlayers() {
        return mTeamPlayers;
    }

    public String getDescription() {
        return mDescription;
    }

    // Method for adding a player to a team
    public void addPlayerToTeam(Player player) {
        mTeamPlayers.add(player);
        prompter.AddedPlayer();
    }

    // Method that validates if the player has all 11 players.
    public boolean completeTeam() {
        int sizeTeam = mTeamPlayers.size();
        return sizeTeam >= 11;
    }

    // Method that removes players from the team
    public void removePlayerToTeam(Player playerToRemove, Team team, String action, WaitingListManager waitList) {
        mTeamPlayers.remove(playerToRemove);
        prompter.playerRemoved(team,action,waitList);

    }

    // Method to create players reports
    public Map<String,Object> createReport(Team team){

        Map<String,Object> mapReportsMap = new HashMap<>();
        Map<Integer, Integer> reportMapByHeight = new TreeMap<>();
        Map<String, TreeSet<Player>> reportMap = new TreeMap<>();
        TreeSet<Player> group1 = new TreeSet<>();
        TreeSet<Player> group2 = new TreeSet<>();
        int heightPlayer;
        TreeSet<Player> listPlayers = team.mTeamPlayers;

        reportMap.put("35-41",group1);
        reportMap.put("42-47",group2);

        for( Player player: listPlayers ){

            heightPlayer=player.getHeightInInches();
            if(reportMapByHeight.containsKey(heightPlayer)){
                int count = reportMapByHeight.get(heightPlayer)+1;
                reportMapByHeight.put(heightPlayer,count);
            }else{

                reportMapByHeight.put(heightPlayer,1);
            }

            if (heightPlayer > 41){
                group2.add(player);
            }else{
                group1.add(player);
            }
        }
        mapReportsMap.put("reportMap",reportMap);
        mapReportsMap.put("reportMapByHeight",reportMapByHeight);
        return mapReportsMap;
    }

    @Override
    public int compareTo(Team o) {
        return this.mTeamName.compareTo(o.mTeamName);
    }

    // Method that allows the creation of teams automatically
    public void addPlayersAutomatically(TreeSet<Player> teamsPlayers) {
        TreeSet<Player> treeSetListPlayer= new TreeSet<>(List.of(Players.load()));
        List<Player> players = new ArrayList<>(treeSetListPlayer) ;
        Random random = new Random();
        int numRandom;
        Set<Integer> notAvailable = new HashSet<>();
        do{
            numRandom = random.nextInt(players.size());
            if(!notAvailable.contains(numRandom) ){
                notAvailable.add(numRandom);
                teamsPlayers.add(players.get(numRandom));
            }
        }while(teamsPlayers.size()<11);

    }
}
