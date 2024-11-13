package com.teamtreehouse.model;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Prompter {
    private String teamNameInput;
    private String coachNameInput;
    Scanner scanner = new Scanner(System.in);


    public String displayMenu(){
        String option;
        System.out.printf("Menu %n %n Create - Create a new team. %n Quit - Exits the program. %n%n Select an option: ");
        option= scanner.nextLine();
        return option;
    }

    public void createNewTeam(){
        String nameTeam;
        String nameCoach;
        Set<Player> team = new HashSet<>();
        TeamsManager allTeams = new TeamsManager();

        System.out.print("What is the team name?  ");
        nameTeam = scanner.nextLine();
        System.out.print("What is the coach name? ");
        nameCoach = scanner.nextLine();
        Team newTeam = new Team(nameTeam,nameCoach,team);
        allTeams.addTeamToAllTeams(newTeam);

        System.out.printf("%n%n Team %s coached by %s added.%n%n.", nameTeam,nameCoach);

    }
}
