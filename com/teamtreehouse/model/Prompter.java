package com.teamtreehouse.model;

import java.util.*;

public class Prompter {

    Scanner scanner = new Scanner(System.in);
    TeamsManager allTeams = new TeamsManager();

    public String displayMenu() {
        String option;
        System.out.printf("Menu %n %n Create - Create a new team. %n Add - Add a player to a team. %n Quit - Exits the program. %n%n Select an option: ");

        option = scanner.nextLine();
        return option.toLowerCase();
    }

    public void createNewTeam() {
        String nameTeam;
        String nameCoach;
        String description;
        Set<Player> team = new HashSet<>();

        System.out.print("What is the team name?  ");
        nameTeam = scanner.nextLine();
        System.out.print("What is the coach name? ");
        nameCoach = scanner.nextLine();
        description = "Team " + nameTeam + " coached by " + nameCoach + " added. ";
        Team newTeam = new Team(nameTeam, nameCoach, team, description);
        allTeams.addTeamToAllTeams(newTeam);

        System.out.printf("%n%n  %s %n%n", description);

    }

    public Team displayTeams() {

        Set<Team> teams = allTeams.getAllTeams();
        List<Team> teamsList = new ArrayList<>(teams);
        int num = 0;
        int option;

        System.out.printf("Available teams: %n ");

        for (Team team : teams) {
            num++;
            System.out.printf("%d.) %s %n", num, team.getDescription());
        }
        System.out.printf("%n%n Select an option: ");
        option = scanner.nextInt();
        return teamsList.get(option - 1);
    }

    public Player displayPlayers() {
        Player[] players = Players.load();
        int option;
        int num = 0;
        String experience;
        System.out.printf("List of players: %n%n ");
        for (Player player : players) {
            num++;
            if (player.isPreviousExperience()) {
                experience = "experienced";
            } else {
                experience = "inexperienced";
            }
            System.out.printf("%d.) %s %s (%s inches - %s ) %n ", num, player.getFirstName(), player.getLastName(), player.getHeightInInches(), experience);
        }
        System.out.printf("%nSelect the player to add to the team: ");
        option = scanner.nextInt();
        scanner.nextLine();
        return players[option - 1];
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
}
