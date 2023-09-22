package places;

import game.Player;
import enemies.*;

public class Newyork extends FightPlace {
	 public Newyork(Player player) {
	        super(5, "Newyork", player, new Thanos(),"gold");
	    }
}
