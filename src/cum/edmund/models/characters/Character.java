package cum.edmund.models.characters;

import cum.edmund.models.WorldObject;
import cum.edmund.models.WorldObjectType;
import cum.edmund.models.map.Coord;

public abstract class Character extends WorldObject {

  public Character(String name, WorldObjectType type, Coord position) {
    super(name, type, position);
  }
}
