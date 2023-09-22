package places;
import game.Player;
import enemies.Joker;

public class Park extends FightPlace {
	 public Park(Player player) {
	        super(3, "Park", player, new Joker(),"water");
	    }
}
