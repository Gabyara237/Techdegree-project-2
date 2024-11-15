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
                "Balance - View the League Balance Report.%n " +
                "Roster - View roster.%n " +
                "Build - Automatically Build Teams.%n " +
                "Wait - Add player to player waiting list.%n " +
                "Rotate - Remove a player from the team and add a player from the waiting list. %n " +
                "Quit - Exits the program. %n%n Select an option: ");

        option = scanner.nextLine();
        return option.toLowerCase();
    }

    //Method for creating new teams
    public void createNewTeam(TeamsManager allTeams) {
        String nameTeam;
        String nameCoach;
        String description;
        TreeSet<Player> team = new TreeSet<>();

        System.out.print("What is the team name?  ");
        nameTeam = scanner.nextLine();
        System.out.print("What is the coach name? ");
        nameCoach = scanner.nextLine();
        description = "Team " + capitalizeInput(nameTeam) + " coached by " + capitalizeInput(nameCoach) + " added.";
        Team newTeam = new Team(capitalizeInput(nameTeam), capitalizeInput(nameCoach), team, description);
        boolean added = allTeams.addTeamToAllTeams(newTeam);

        if (added) {
            System.out.printf("%n%n  %s %n%n", description);
        } else {
            System.out.printf("Team already exists. Try adding another team. %n");
        }
    }

    //Auxiliary method to display teams
    public void displayTeams(List<Team> allTeams, String action) {

        if (action.equals("build")) {
            System.out.printf("Successfully created teams! %n");
        }
        int num = 0;
        for (Team team : allTeams) {
            num++;
            System.out.printf("%d.) %s %n", num, team.getDescription());
        }
    }

    // method to display teams
    public Team displayTeams(TeamsManager allTeams) {

        TreeSet<Team> teams = allTeams.getAllTeams();
        List<Team> teamsList = new ArrayList<>(teams);
        int option = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.printf("Available teams:%n");
                displayTeams(teamsList, "");

                System.out.printf("%nSelect an option (1-%d): ", teamsList.size());
                option = scanner.nextInt();
                scanner.nextLine();

                if (option >= 1 && option <= teamsList.size()) {
                    validInput = true;
                } else {
                    System.out.printf("Invalid option. Please select a number between 1 and %d.%n", teamsList.size());
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid option. Please enter a numeric value.");
                scanner.nextLine();
            }
        }

        Team selectedTeam = teamsList.get(option - 1);

        int numPlayers = selectedTeam.getTeamPlayers().size();
        System.out.printf("%nThe selected team has %d players.%n", numPlayers);

        return selectedTeam;
    }

    // Method to display players
    public Player displayPlayers(List<Player> listPlayer, String action) {
        int option = 0;
        displayPlayers(listPlayer, "", action);

        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.printf("%nSelect the player to %s in the team: ", action);
                option = scanner.nextInt();
                scanner.nextLine();

                if (option >= 1 && option <= listPlayer.size()) {
                    validInput = true;
                } else {
                    System.out.printf("Invalid option. Please select a number between 1 and %d.%n", listPlayer.size());
                }
            } catch (InputMismatchException e) {

                System.out.printf("Invalid option. Please enter a numeric value.%n");
                scanner.nextLine();
            }
        }
        return listPlayer.get(option - 1);


    }


    public void playerRemoved(Team team, String action, WaitingListManager waitList) {
        int attempts = 0;
        int option;

        System.out.printf("Player successfully removed from the team!.%n");
        if (action.equals("remove") && !waitList.getWaitList().isEmpty()) {
            System.out.printf("There are players on the waiting list. Do you want to add the next player on the list to the team? %n1.) Yes %n2.) No %n%nSelect an option: ");
            do {
                try {
                    if (attempts > 0) {
                        System.out.printf("Invalid option, select an option from 1 to 2.%n");
                    }
                    option = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.printf("Invalid option. Please enter a numeric value.%n");
                    scanner.nextLine();
                    option = 0;
                }
                attempts++;
            } while (option < 1 || option > 2);

            if (option == 1) {
                addPlayerFromWaitingList(waitList, team);
            }
        }
    }

    //-------- Methods for displaying messages ---------
    public void noPlayersToRemove(String teamName) {
        System.out.printf("Players cannot be removed from the team %s. %n%n", teamName);
    }

    public void playerAlreadyTeam(String firstName, String teamName) {
        System.out.printf("Player %s is already in team %s.%n", firstName, teamName);
    }

    public void noPlayers(String teamName) {
        System.out.printf("The selected team does not have players to display the roster %s. %n%n", teamName);
    }

    public void noPlayersToReport(String teamName) {
        System.out.printf("No player report can be generated for de team %s %n", teamName);
    }

    public void teamsNotAvailable(String action) {
        System.out.printf("%nThere are no teams available to %s. %n%n", action);
    }

    public void validationOfTeamsCreated() {
        System.out.printf("%nThere must be no previously created teams to use this option.%n%n");
    }

    public void limitTeamsReached() {
        System.out.printf("%nLimit teams reached. %nYou cannot add more teams to the league. There are already 3 teams created.%n");
    }

    public void invalidOption() {
        System.out.printf("Invalid option. %n");
    }

    public void AddedPlayer(String firstName, String teamName) {

        System.out.printf("Player %s successfully added to the team %s! %n", firstName, teamName);
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

    //Methods to display player reports
    public void displayReport(Map<String, TreeSet<Player>> reportMap, Map<Integer, Integer> reportMapByHeight) {

        int numGroup = 0;
        String action = "report";
        String range;
        for (Map.Entry<String, TreeSet<Player>> group : reportMap.entrySet()) {
            numGroup++;
            range = group.getKey();
            TreeSet<Player> listPlayers = group.getValue();
            List<Player> listTeamPlayer = new ArrayList<>(listPlayers);
            System.out.printf("%nGroup %d - Range %s inches %n%n", numGroup, range);
            displayPlayers(listTeamPlayer, "", action);
        }
        System.out.printf("%nPlayer counter by height in the team: %n%n");
        for (Map.Entry<Integer, Integer> height : reportMapByHeight.entrySet()) {
            System.out.printf("%d inches: %d %n", height.getKey(), height.getValue());
        }
        System.out.printf("%n");
    }

    //Methods to display player balance
    public void displayBalance(Map<String, List<Integer>> balanceMap) {
        String teamName;
        for (Map.Entry<String, List<Integer>> team : balanceMap.entrySet()) {
            teamName = team.getKey();
            List<Integer> listCounts = team.getValue();
            int totalExpert = listCounts.get(0);
            int total = totalExpert + listCounts.get(1);
            int percentageExperienced = (int) (((double) totalExpert / total) * 100);

            System.out.printf("%n%n *** Team: %s *** %n%nTotal number of players: %d %nExperienced players: %d %nInexperienced players: %d %nPercentage of experienced players: %d%% %n", teamName, total, totalExpert, listCounts.get(1), percentageExperienced);

        }
    }

    // Auxiliary Methods to display players
    public void displayPlayers(List<Player> listPlayers, String teamName, String action) {
        int num = 0;
        String experience;

        if ("roster".equals(action)) {
            System.out.printf("%n**** Team %s ****%n ", teamName);
        }
        if ("add".equals(action)) {
            System.out.printf("List of players: %n%n ");
        }
        for (Player player : listPlayers) {
            num++;
            if (player.isPreviousExperience()) {
                experience = "experienced";
            } else {
                experience = "inexperienced";
            }
            System.out.printf("%d.) %s %s (%s inches - %s ) %n", num, player.getLastName(), player.getFirstName(), player.getHeightInInches(), experience);
        }
    }

    // Method for adding a new player to the waiting list
    public void addPlayerToWaitingList(WaitingListManager waitList) {
        String firstName;
        String lastName;
        int heightInInches = 0;
        int optionExperience;
        boolean previousExperience;
        boolean validHeight = false;

        System.out.print("What is the player's first name? ");
        firstName = scanner.nextLine();
        System.out.print("What is the player's last name? ");
        lastName = scanner.nextLine();

        while (!validHeight) {
            try {
                System.out.print("What is the player's height in inches? ");
                heightInInches = scanner.nextInt();
                scanner.nextLine();

                if (heightInInches > 0) {
                    validHeight = true;
                } else {
                    System.out.printf("Invalid option. Height must be a positive number.%n");
                }
            } catch (InputMismatchException e) {
                System.out.printf("Invalid option. Please enter a numeric value.%n");
                scanner.nextLine();
            }
        }

        System.out.printf("The player is:%n 1.) Experienced.%n 2.) Inexperienced.%n Select an option: ");
        do {
            try {
                optionExperience = scanner.nextInt();
                scanner.nextLine();

                if (optionExperience == 1 || optionExperience == 2) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.printf("Invalid option. Please enter a numeric value.%n");
                scanner.nextLine();
            }

        } while (true);

        previousExperience = optionExperience == 1;
        Player player = new Player(capitalizeInput(firstName), capitalizeInput(lastName), heightInInches, previousExperience);
        if ((waitList.getWaitList().contains(player))) {
            System.out.printf("%n Player not created, already exists in the waiting list.%n");
        } else {
            waitList.getWaitList().add(player);

            System.out.printf("%n Player %s %s successfully created.%n", player.getFirstName(), player.getLastName());
        }
    }

    //Method for adding a new player from the waiting list to the team
    private void addPlayerFromWaitingList(WaitingListManager waitList, Team team) {
        Player player = waitList.getWaitList().removeFirst();
        System.out.printf("The player %s %s, who was next on the waiting list, has been successfully added to the team.%n", player.getFirstName(), player.getLastName());

    }

    //Method to capitalize user input.
    public static String capitalizeInput(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }


}
