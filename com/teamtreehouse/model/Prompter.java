package com.teamtreehouse.model;

import java.util.*;

public class Prompter {

    Scanner scanner = new Scanner(System.in);

    public String displayMenu() {
        String option;
        System.out.printf("Menu %n %n " +
                "Create - Create a new team. %n " +
                "Add - Add a player to a team. %n " +
                "Remove - Remove a player from a team.%n " +
                "Quit - Exits the program. %n%n Select an option: ");

        option = scanner.nextLine();
        return option.toLowerCase();
    }

    public void createNewTeam(TeamsManager allTeams) {
        String nameTeam;
        String nameCoach;
        String description;
        Set<Player> team = new HashSet<>();

        System.out.print("What is the team name?  ");
        nameTeam = scanner.nextLine();
        System.out.print("What is the coach name? ");
        nameCoach = scanner.nextLine();
        description = "Team " + nameTeam + " coached by " + nameCoach + " added.";
        Team newTeam = new Team(nameTeam, nameCoach, team, description);
        allTeams.addTeamToAllTeams(newTeam);

        System.out.printf("%n%n  %s %n%n", description);

    }

    public Team displayTeams(TeamsManager allTeams) {

        TreeSet<Team> teams = new TreeSet<>(allTeams.getAllTeams());
        List<Team> teamsList = new ArrayList<>(teams);
        String teamSelected;
        int num = 0;
        int option;

        System.out.printf("Available teams: %n");

        for (Team team : teams) {
            num++;
            System.out.printf("%d.) %s %n", num, team.getDescription());
        }
        System.out.printf("%nSelect an option: ");
        option = scanner.nextInt();
        scanner.nextLine();
        int numPlayers = teamsList.get(option - 1).getTeamPlayers().size();
        System.out.printf("%nThe selected team has %d players. %n",numPlayers);

        return teamsList.get(option - 1);
    }

    public Player displayPlayers(List<Player> listPlayer, String action){
        int num = 0;
        int option;
        String experience;
        System.out.printf("List of players: %n%n ");
        for (Player player : listPlayer) {
            num++;
            if (player.isPreviousExperience()) {
                experience = "experienced";
            } else {
                experience = "inexperienced";
            }
            System.out.printf("%d.) %s %s (%s inches - %s ) %n ", num, player.getLastName(), player.getFirstName() , player.getHeightInInches(), experience);
        }
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


    public void playerRemoved() {
        System.out.printf("Player successfully removed from the team!.%n");
    }

    public void noPlayersToRemove(String teamName) {
        System.out.printf("You cannot remove players from the team %s. %n%n", teamName);
    }

    public void teamsNotAvailable(String action){
        System.out.printf("%nThere are no teams available to %s players. %n%n", action);
    }
}
