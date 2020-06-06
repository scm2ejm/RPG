package cum.edmund.models.blocks;

import cum.edmund.models.WorldObjectType;

/**
 * Model of an in-game house
 * 
 * @author Ed
 *
 */
public class House extends Barrier {

  public House() {
    super("Default house", WorldObjectType.HOUSE, "house.png");
  }

  public House(String name) {
    super(name, WorldObjectType.HOUSE, "house.png");
  }

}
