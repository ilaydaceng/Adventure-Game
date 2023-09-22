package places;

import game.Player;

public class NormalPlace extends Places{

    public NormalPlace(int id,String name, Player player) {
        super(id,name, player);
    }

    @Override
    public boolean onPlaces() {
        return true;
    }
}
