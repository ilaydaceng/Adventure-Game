package places;

import enemies.Voldemort;
import game.Player;


public class Hogwarts extends FightPlace {
	 public Hogwarts(Player player) {
	        super(4, "Hogwarts", player, new Voldemort(),"food");
	    }
}
