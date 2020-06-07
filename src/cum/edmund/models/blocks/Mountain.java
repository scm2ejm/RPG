package cum.edmund.models.blocks;

import cum.edmund.models.WorldObjectType;

/**
 * Model of an impassable mountain
 * 
 * @author Ed
 *
 */
public class Mountain extends Barrier {

  public Mountain() {
    super("Mountain", WorldObjectType.BARRIER, "mountian.png");
  }

}
