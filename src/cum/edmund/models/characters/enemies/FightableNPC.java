package cum.edmund.models.characters.enemies;

import javax.swing.ImageIcon;
import cum.edmund.models.WorldObjectType;
import cum.edmund.models.characters.FightableCharacter;

/**
 * This model is used to represent a fightable non-player character
 * 
 * @author Ed
 *
 */
public abstract class FightableNPC extends FightableCharacter {

  public FightableNPC(String name, String imageFilename) {
    super(name, WorldObjectType.ENEMY, imageFilename);

    // Do not allow characters to walk through other characters
    setBarrier(true);
  }

  public ImageIcon attackTile() {
    // FIXME
    return null;
  }

  public String attackSound() {
    // TODO Auto-generated method stub
    return null;
  }
}
