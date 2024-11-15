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
        Map<Integer, Integer> reportMapByHeight;
        Map<String,Object> mapReportsMap;
        Map<String, TreeSet<Player>> reportMap;
        Map<String, List<Integer>> balanceMap;
        List<Player> waitListSet = new ArrayList<>();
        WaitingListManager waitList = new WaitingListManager(waitListSet);

        do {
            choice = prompter.displayMenu();
            switch (choice) {
                case "create":
                    prompter.createNewTeam(allTeams);
                    break;
                case "add":
                    if(allTeams.getAllTeams().size()<3) {
                        if (allTeams.getAllTeams().isEmpty()) {
                            action = "add players";
                            prompter.teamsNotAvailable(action);
                        } else {
                            Team team = prompter.displayTeams(allTeams);
                            Player player;
                            if (!team.completeTeam()) {

                                TreeSet<Player> treeSetListPlayer = new TreeSet<>(List.of(Players.load()));
                                List<Player> players = new ArrayList<>(treeSetListPlayer);
                                action = "add";
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
                    }else{
                        prompter.limitTeamsReached();

                    }
                    break;
                case "remove", "rotate":
                    if(allTeams.getAllTeams().isEmpty()){
                        action="remove players";
                        prompter.teamsNotAvailable(action);
                    }else {
                        Team teamToRemovePlayer;
                        Player playerToRemove;
                        teamToRemovePlayer = prompter.displayTeams(allTeams);
                        if (!teamToRemovePlayer.getTeamPlayers().isEmpty()) {
                            if(choice.equals("remove")){
                                action="remove";
                            }else{
                                action="rotate";
                            }
                            TreeSet<Player> treeSetListTeamPlayer= new TreeSet<>(teamToRemovePlayer.getTeamPlayers());
                            List<Player> listTeamPlayer= new ArrayList<>(treeSetListTeamPlayer);
                            playerToRemove = prompter.displayPlayers(listTeamPlayer, action);
                            teamToRemovePlayer.removePlayerToTeam(playerToRemove, teamToRemovePlayer,action,waitList);
                        } else {
                            prompter.noPlayersToRemove(teamToRemovePlayer.getTeamName());
                        }
                    }
                    break;
                case "report":
                    if(allTeams.getAllTeams().isEmpty()){
                        action="generate player reports";
                        prompter.teamsNotAvailable(action);
                    }else {
                        Team teamToReportPlayers;
                        teamToReportPlayers = prompter.displayTeams(allTeams);
                        if (!teamToReportPlayers.getTeamPlayers().isEmpty()) {
                            mapReportsMap= teamToReportPlayers.createReport(teamToReportPlayers);

                            reportMapByHeight = (Map<Integer, Integer>) mapReportsMap.get("reportMapByHeight");
                            reportMap= (Map<String, TreeSet<Player>>) mapReportsMap.get("reportMap");
                            prompter.displayReport(reportMap, reportMapByHeight);
                        } else {
                            prompter.noPlayersToReport(teamToReportPlayers.getTeamName());
                        }
                    }
                    break;
                case "balance":
                    if(allTeams.getAllTeams().isEmpty()){
                        action="generate teams balance";
                        prompter.teamsNotAvailable(action);
                    }else {
                        balanceMap= allTeams.createBalance(allTeams.getAllTeams());
                        prompter.displayBalance(balanceMap);
                    }
                    break;
                case "roster":
                    if(allTeams.getAllTeams().isEmpty()){
                        action="view roster";
                        prompter.teamsNotAvailable(action);
                    }else {
                        action="roster";
                        Team teamToRoster;
                        teamToRoster = prompter.displayTeams(allTeams);
                        if (!teamToRoster.getTeamPlayers().isEmpty()) {
                            TreeSet<Player> treeSetListTeamPlayer= teamToRoster.getTeamPlayers();
                            List<Player> listTeamPlayer= new ArrayList<>(treeSetListTeamPlayer);
                            prompter.displayPlayers(listTeamPlayer,teamToRoster.getTeamName(),action);

                        } else {
                            prompter.noPlayers(teamToRoster.getTeamName());
                        }
                    }
                    break;
                case "build":
                    action= "build";
                    if(allTeams.getAllTeams().isEmpty()){
                        allTeams.teamBuilding();
                        List<Team> teamsList = new ArrayList<>(allTeams.getAllTeams());
                        prompter.displayTeams(teamsList,action);
                    }else {
                        prompter.validationOfTeamsCreated();
                    }
                    break;
                case "wait":
                    prompter.addPlayerToWaitingList(waitList);
                    break;
                default:
                    prompter.invalidOption();
                    break;
            }
        } while (!choice.equals("quit"));
    }


}
