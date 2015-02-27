package cum.edmund.models.characters.enemies;

import cum.edmund.models.WorldObjectType;
import cum.edmund.models.characters.FightableCharacter;
import cum.edmund.models.map.Coord;

/**
 * This model is used to represent a fightable non-player character
 * 
 * @author Ed
 *
 */
public abstract class FightableNPC extends FightableCharacter {

  public FightableNPC(String name, Coord position) {
    super(name, WorldObjectType.ENEMY, position);
  }

}
