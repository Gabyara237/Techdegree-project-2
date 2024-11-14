import com.teamtreehouse.model.*;

import java.util.HashSet;
import java.util.Set;

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
                            player = prompter.displayPlayers();
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
                            playerToRemove = prompter.displayPlayers(teamToRemovePlayer);
                            teamToRemovePlayer.removePlayerToTeam(playerToRemove);
                        } else {
                            prompter.noPlayersToRemove(teamToRemovePlayer.getTeamName());
                        }
                    }
                    break;
                default:
                    break;
            }
        } while (!choice.equals("quit"));
    }

}
