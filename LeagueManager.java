import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Teams;
import com.teamtreehouse.utils.Prompter;
import com.teamtreehouse.utils.Action;
import com.teamtreehouse.utils.SLOException;
import com.teamtreehouse.utils.Executor;

public class LeagueManager {

  public static void main(String[] args) {
    Player[] players = Players.load();
    System.out.printf("%n%nThere are currently %d registered players.%n%n", players.length);

    Teams teams = new Teams();
   
    String option = null;
    do {

    	try {

    		Prompter.displayMenu();
    		option = Prompter.promptUserInputForOption();
            if(option == null) {
                continue;
            }
            Executor.execAction(option, teams);

    	} catch(SLOException sloe) {

    		System.out.println(sloe.getErrorMessage() + "\n");
    	}

    } while(option != null && !option.equalsIgnoreCase(Action.Quit.getDescription()));
   
  }


}
