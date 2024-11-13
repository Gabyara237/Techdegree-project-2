import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Prompter;

public class LeagueManager {

  public static void main(String[] args) {
    Player[] players = Players.load();
    System.out.printf("There are currently %d registered players.%n", players.length);
    // Your code here!
    choiceMenu();




  }

  public static void choiceMenu(){
    Prompter prompter = new Prompter();
    String choice="";
    do{
      choice = prompter.displayMenu();
      switch (choice){
        case "create":
          prompter.createNewTeam();
          break;
        default:
          break;
      }

    }while (!choice.equals("quit"));
  }

}
