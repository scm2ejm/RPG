package cum.edmund.models.characters;

import cum.edmund.models.WorldObject;
import cum.edmund.models.WorldObjectType;

public abstract class Character extends WorldObject {

  public Character(String name, WorldObjectType type, String imageFilename) {
    super(name, type, imageFilename);
  }

}
