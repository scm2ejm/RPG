package cum.edmund.models.blocks;

import cum.edmund.models.WorldObjectType;

/**
 * Model of an impassable mountain
 * 
 * @author Ed
 *
 */
public class Mountian extends Barrier {

  public Mountian() {
    super("Mountain", WorldObjectType.BARRIER, "mountian.png");
  }

}
