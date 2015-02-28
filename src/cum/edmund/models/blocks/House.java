package cum.edmund.models.blocks;

import cum.edmund.models.WorldObjectType;
import cum.edmund.models.map.Coord;

/**
 * Model of an in-game house
 * 
 * @author Ed
 *
 */
public class House extends Barrier {

  public House(String name, Coord position) {
    super(name, WorldObjectType.HOUSE, position);
  }

}
