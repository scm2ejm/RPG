package cum.edmund.models.blocks;

import cum.edmund.models.WorldObject;
import cum.edmund.models.WorldObjectType;
import cum.edmund.models.map.Coord;

/**
 * Model of an barrier that cannot be walked past, such as a wall
 * 
 * @author Ed
 *
 */
public class Barrier extends WorldObject {

  public Barrier(Coord position) {
    super("Barrier", WorldObjectType.BARRIER, position);
  }

  public Barrier(String name, WorldObjectType type, Coord position) {
    super(name, type, position);
  }
}
