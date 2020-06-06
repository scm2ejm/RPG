package cum.edmund.models.blocks;

import cum.edmund.models.WorldObject;
import cum.edmund.models.WorldObjectType;

/**
 * Model of an barrier that cannot be walked past, such as a wall
 * 
 * @author Ed
 *
 */
public abstract class Barrier extends WorldObject {
  public Barrier(String name, WorldObjectType type, String imageFilename) {
    super(name, type, imageFilename);

    // All extending classes cannot be walked through
    setBarrier(true);
  }
}
