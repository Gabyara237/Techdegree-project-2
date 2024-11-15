package com.teamtreehouse.model;

import java.util.*;

public class Prompter {


    Scanner scanner = new Scanner(System.in);

    public String displayMenu() {
        String option;
        System.out.printf("%nMenu %n %n " +
                "Create - Create a new team. %n " +
                "Add - Add a player to a team. %n " +
                "Remove - Remove a player from a team.%n " +
                "Report - View a report of a team by height.%n " +
                "Balance - View the League Balance Report.%n "+
                "Roster - View roster.%n "+
                "Build - Automatically Build Teams.%n "+
                "Wait - Add player to player waiting list.%n "+
                "Rotate - Remove a player from the team and add a player from the waiting list. %n "+
                "Quit - Exits the program. %n%n Select an option: ");

        option = scanner.nextLine();
        return option.toLowerCase();
    }

    public void createNewTeam(TeamsManager allTeams) {
        String nameTeam;
        String nameCoach;
        String description;
        TreeSet<Player> team = new TreeSet<>();

        System.out.print("What is the team name?  ");
        nameTeam = scanner.nextLine();
        System.out.print("What is the coach name? ");
        nameCoach = scanner.nextLine();
        description = "Team " + nameTeam + " coached by " + nameCoach + " added.";
        Team newTeam = new Team(nameTeam, nameCoach, team, description);
        allTeams.addTeamToAllTeams(newTeam);

        System.out.printf("%n%n  %s %n%n", description);

    }

    public void displayTeams(List<Team> allTeams,String action){

        if (action.equals("build")){
            System.out.printf("Successfully created teams! %n");
        }
        int num = 0;
        for (Team team : allTeams) {
            num++;
            System.out.printf("%d.) %s %n", num, team.getDescription());
        }
    }

    public Team displayTeams(TeamsManager allTeams) {

        TreeSet<Team> teams = allTeams.getAllTeams();
        List<Team> teamsList = new ArrayList<>(teams);
        String teamSelected;
        int num = 0;
        int option;

            System.out.printf("Available teams: %n");
            displayTeams(teamsList,"");
            System.out.printf("%nSelect an option: ");
            option = scanner.nextInt();
            scanner.nextLine();
            int numPlayers = teamsList.get(option - 1).getTeamPlayers().size();
            System.out.printf("%nThe selected team has %d players. %n", numPlayers);

            return teamsList.get(option - 1);
    }

    public Player displayPlayers(List<Player> listPlayer, String action){
        int option;
        displayPlayers( listPlayer, "", action);
        System.out.printf("%nSelect the player to %s in the team: ",action);
        option = scanner.nextInt();
        scanner.nextLine();
        return listPlayer.get(option - 1);


    }


    public void AddedPlayer() {
        System.out.printf("Player successfully added to the team!%n");
    }

    public void playerLimitReached() {
        System.out.println("The selected team already has the 11 players allowed.");
    }

    public void playerNoAvailable(boolean isInTheTeam) {
        if (isInTheTeam) {
            System.out.println("The selected player is already in the team. %n%n");
        } else {
            System.out.println("Player not available to be added to the team");
        }
    }


    public void playerRemoved(Team team, String action, WaitingListManager waitList) {
        TreeSet<Player> teamPlayers;
        Player player;
        int attempts=0;
        int option=0;
        System.out.printf("Player successfully removed from the team!.%n");
        if (action.equals("remove")) {
            if(!waitList.getWaitList().isEmpty()) {
                System.out.printf("There are players on the waiting list. Do you want to add the next player on the list to the team.%n%n 1.) Yes %n 2.) No %n%n Select an option: ");
                do {
                    if (attempts > 0) {
                        System.out.printf("Option does not exist, select an option from 1 to 2. %n");
                    }
                    option = scanner.nextInt();
                    attempts++;
                } while (option < 1 || option > 2);
                scanner.nextLine();

                if(option==1){
                    addPlayerFromWaitingList(waitList,team);
                }
            }
        }else{
            if(!waitList.getWaitList().isEmpty()) {
                addPlayerFromWaitingList(waitList, team);
            }else{
                System.out.printf("There are no player on the waiting list.%n");
            }
        }
    }

    public void noPlayersToRemove(String teamName) {
        System.out.printf("Players cannot be removed from the team %s. %n%n", teamName);
    }
    public void noPlayers(String teamName) {
        System.out.printf("The selected team does not have players to display the roster %s. %n%n", teamName);
    }

    public void noPlayersToReport(String teamName) {
        System.out.printf("No player report can be generated for de team %s %n",teamName);
    }

    public void teamsNotAvailable(String action){
        System.out.printf("%nThere are no teams available to %s. %n%n", action);
    }

    public void displayReport(Map<String, TreeSet<Player>> reportMap) {

        int numGroup=0;
        String action="report";
        String range;
        for(Map.Entry<String,TreeSet<Player>> group : reportMap.entrySet()){
            numGroup++;
            range= group.getKey();
            TreeSet<Player> listPlayers = group.getValue();
            List<Player> listTeamPlayer= new ArrayList<>(listPlayers);
            System.out.printf("%n*****  Group %d - Range %s inches  ***** %n%n",numGroup,range);
            displayPlayers( listTeamPlayer, "", action);
        }
        System.out.printf("%n");
    }

    public void displayBalance(Map<String, List<Integer>> balanceMap) {
        String teamName="";
        for(Map.Entry<String, List<Integer>> team : balanceMap.entrySet()){
            teamName= team.getKey();
            List<Integer> listCounts= team.getValue();
            System.out.printf("%n%n *** Team: %s *** %n%nTotal number of players: %d %nExperienced players: %d %nInexperienced players: %d %n",teamName,listCounts.get(0)+listCounts.get(1), listCounts.get(0),listCounts.get(1));

        }
    }

    public void displayPlayers(List<Player> listPlayers, String teamName, String action) {
        int num = 0;
        String experience;

        if("roster".equals(action)){
            System.out.printf("%n**** Team %s ****%n ",teamName);
        }
        if("add".equals(action)) {
            System.out.printf("List of players: %n%n ");
        }
        for (Player player : listPlayers) {
            num++;
            if (player.isPreviousExperience()) {
                experience = "experienced";
            } else {
                experience = "inexperienced";
            }
            System.out.printf("%d.) %s %s (%s inches - %s ) %n", num, player.getLastName(), player.getFirstName() , player.getHeightInInches(), experience);
        }
    }

    public void validationOfTeamsCreated() {
        System.out.printf("%nThere must be no previously created teams to use this option.%n%n" );
    }

    public void limitTeamsReached() {
        System.out.printf("%nLimit teams reached. %nYou cannot add more teams to the league. There are already 3 teams created");
    }

    public void addPlayerToWaitingList(WaitingListManager waitList) {
        String firstName;
        String lastName;
        int heightInInches;
        int attempts=0;
        int optionExperience;
        boolean previousExperience;
        System.out.print("What is the player's first name? ");
        firstName = scanner.nextLine();
        System.out.print("What is the player's last name? ");
        lastName = scanner.nextLine();
        System.out.print("What is the player's height in inches? ");
        heightInInches = scanner.nextInt();
        System.out.printf("The player is: %n 1.) Experienced. %n 2.) Inexperienced. %n Select an option: ");
        do {
            if (attempts>0){
                System.out.printf("Option does not exist, select an option from 1 to 2. %n");
            }

            optionExperience = scanner.nextInt();
            attempts++;
        }while(optionExperience< 1 || optionExperience > 2 );
        scanner.nextLine();

        previousExperience= optionExperience == 1;

        Player player = new Player(firstName,lastName,heightInInches,previousExperience );
        waitList.getWaitList().add(player);
        System.out.printf("%n Player %s %s successfully created" ,player.getFirstName(), player.getLastName());
    }

    private void addPlayerFromWaitingList(WaitingListManager waitList, Team team){
        TreeSet<Player> teamPlayers;
        Player player;
        teamPlayers= team.getTeamPlayers();
        player = waitList.getWaitList().removeFirst();
        teamPlayers.add(player);
        System.out.printf("The player %s %s, who was next on the waiting list, has been successfully added to the team.%n", player.getFirstName(),player.getLastName());

    }
}
