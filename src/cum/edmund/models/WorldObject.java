package cum.edmund.models;

import cum.edmund.models.map.Coord;

/**
 * A world object is something that the hero can interact with
 * @author Ed
 *
 */
public abstract class WorldObject {
  private final String name;
  private WorldObjectType type;
  private Coord position;

  public WorldObject(String name, WorldObjectType type, Coord position) {
    super();
    this.name = name;
    this.type = type;
    this.position = position;
  }

  public WorldObjectType getType() {
    return type;
  }

  public void setType(WorldObjectType type) {
    this.type = type;
  }

  public Coord getPosition() {
    return position;
  }

  public void setPosition(Coord position) {
    this.position = position;
  }

  public String getName() {
    return name;
  }
  
}
