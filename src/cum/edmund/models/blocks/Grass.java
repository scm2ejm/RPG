package cum.edmund.models.blocks;

import cum.edmund.models.WorldObject;
import cum.edmund.models.WorldObjectType;

/**
 * Model of an grass which can be walked on
 * 
 * @author Ed
 *
 */
public class Grass extends WorldObject {

  public Grass() {
    super("Grass", WorldObjectType.BACKGROUND, "grasshd.jpg");
  }

}
