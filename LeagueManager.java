import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;
import java.util.Map;


import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.utils.Prompter;

public class LeagueManager {

  public static void main(String[] args) {
    Player[] players = Players.load();
    System.out.printf("%n%nThere are currently %d registered players.%n%n", players.length);
    // Your code here!

   
    do {
    	Prompter.displayMenu();
    } while(false);
   
  }


}
