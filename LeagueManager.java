import com.teamtreehouse.model.*;

import java.util.*;

public class LeagueManager {

    public static void main(String[] args) {
        Player[] players = Players.load();
        System.out.printf("There are currently %d registered players.%n", players.length);
        // Your code here!
        TeamsManager allTeams = new TeamsManager();
        choiceMenu(allTeams);

    }

    public static void choiceMenu(TeamsManager allTeams) {
        Prompter prompter = new Prompter();
        String choice;
        Set<Player> playersNoAvailable = new HashSet<>();
        String action;
        Map<String, TreeSet<Player>> reportMap;

        do {
            choice = prompter.displayMenu();
            switch (choice) {
                case "create":
                    prompter.createNewTeam(allTeams);
                    break;
                case "add":
                    if(allTeams.getAllTeams().isEmpty()){
                        action="add";
                        prompter.teamsNotAvailable(action);
                    }else {
                        Team team = prompter.displayTeams(allTeams);
                        Player player;
                        if (!team.completeTeam()) {

                            TreeSet<Player> treeSetListPlayer= new TreeSet<>(List.of(Players.load()));
                            List<Player> players = new ArrayList<>(treeSetListPlayer) ;
                            action="add";
                            player = prompter.displayPlayers(players, action);
                            if (!playersNoAvailable.contains(player)) {
                                playersNoAvailable.add(player);
                                team.addPlayerToTeam(player);
                            } else {
                                boolean isInTheTeam;
                                isInTheTeam = team.getTeamPlayers().contains(player);
                                prompter.playerNoAvailable(isInTheTeam);
                            }
                        } else prompter.playerLimitReached();
                    }
                    break;
                case "remove":
                    if(allTeams.getAllTeams().isEmpty()){
                        action="remove";
                        prompter.teamsNotAvailable(action);
                    }else {
                        Team teamToRemovePlayer;
                        Player playerToRemove;
                        teamToRemovePlayer = prompter.displayTeams(allTeams);
                        if (!teamToRemovePlayer.getTeamPlayers().isEmpty()) {
                            action= "remove";
                            TreeSet<Player> treeSetListTeamPlayer= new TreeSet<>(teamToRemovePlayer.getTeamPlayers());
                            List<Player> listTeamPlayer= new ArrayList<>(treeSetListTeamPlayer);
                            playerToRemove = prompter.displayPlayers(listTeamPlayer, action);
                            teamToRemovePlayer.removePlayerToTeam(playerToRemove);
                        } else {
                            prompter.noPlayersToRemove(teamToRemovePlayer.getTeamName());
                        }
                    }
                    break;
                case "report":
                    if(allTeams.getAllTeams().isEmpty()){
                        action="report";
                        prompter.teamsNotAvailable(action);
                    }else {
                        Team teamToReportPlayers;
                        Player playerToRemove;
                        teamToReportPlayers = prompter.displayTeams(allTeams);
                        if (!teamToReportPlayers.getTeamPlayers().isEmpty()) {
                            reportMap= teamToReportPlayers.createReport(teamToReportPlayers);
                            prompter.displayReport(reportMap);
                        } else {
                            prompter.noPlayersToReport(teamToReportPlayers.getTeamName());
                        }
                    }
                    break;
                default:
                    break;
            }
        } while (!choice.equals("quit"));
    }

}
