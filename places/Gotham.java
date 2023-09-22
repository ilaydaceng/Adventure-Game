package places;


import game.Player;
import enemies.Loki;

public class Gotham extends FightPlace {
	 public Gotham(Player player) {
	        super(6, "Gothamn", player, new Loki(),"wood");
	    }
}
