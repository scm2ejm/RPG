package cum.edmund.models.house;

import cum.edmund.models.WorldObject;
import cum.edmund.models.WorldObjectType;
import cum.edmund.models.map.Coord;

/**
 * Model of an in-game house
 * 
 * @author Ed
 *
 */
public class House extends WorldObject {

  public House(String name, Coord position) {
    super(name, WorldObjectType.HOUSE, position);
  }

}
