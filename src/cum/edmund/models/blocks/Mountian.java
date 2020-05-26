package cum.edmund.models.blocks;

import cum.edmund.models.WorldObjectType;
import cum.edmund.models.map.Coord;

/**
 * Model of an in-game house
 * 
 * @author Ed
 *
 */
public class Mountian extends Barrier {

  public Mountian(Coord position) {
    super("Mountain", WorldObjectType.BARRIER, position);
  }

}
