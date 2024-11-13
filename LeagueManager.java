import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Prompter;
import com.teamtreehouse.model.Team;

import java.util.HashSet;
import java.util.Set;

public class LeagueManager {

    public static void main(String[] args) {
        Player[] players = Players.load();
        System.out.printf("There are currently %d registered players.%n", players.length);
        // Your code here!
        choiceMenu();

    }

    public static void choiceMenu() {
        Prompter prompter = new Prompter();
        String choice;
        Set<Player> playersNoAvailable = new HashSet<>();

        do {
            choice = prompter.displayMenu();
            switch (choice) {
                case "create":
                    prompter.createNewTeam();
                    break;
                case "add":
                    Team team;
                    Player player;
                    team = prompter.displayTeams();
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
                    break;
                default:
                    break;
            }
        } while (!choice.equals("quit"));
    }

}
