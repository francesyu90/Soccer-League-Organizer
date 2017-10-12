import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.utils.Prompter;
import com.teamtreehouse.utils.Action;
import com.teamtreehouse.utils.SLOException;

public class LeagueManager {

  public static void main(String[] args) {
    Player[] players = Players.load();
    System.out.printf("%n%nThere are currently %d registered players.%n%n", players.length);
    // Your code here!
   
    do {

    	try {

    		Prompter.displayMenu();
    		String option = Prompter.promptUserInputForOption();
    		System.out.println(Action.findByKey(option));

    	} catch(SLOException sloe) {

    		System.out.println(sloe.getErrorMessage());
    	}

    } while(false);
   
  }


}
