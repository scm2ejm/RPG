package cum.edmund.models.maps.world;

import cum.edmund.models.WorldObject;
import cum.edmund.models.characters.enemies.Enemies;
import cum.edmund.models.map.Coord;


/**
 * When walking, you may be successful or not and you may have some enemies to fight
 * 
 * @author Ed
 *
 */
public class WalkOutcome {
  private final boolean success;
  private final Enemies enemies;
  private final WorldObject worldObject;
  private final Coord newPosition;

  public WalkOutcome(boolean success, Enemies enemies, Coord newPosition, WorldObject element) {
    this.success = success;
    this.enemies = enemies;
    this.newPosition = newPosition;
    this.worldObject = element;
  }

  public boolean isSuccess() {
    return success;
  }

  public WorldObject getWorldObject() {
    return worldObject;
  }

  public Coord getNewPosition() {
    return newPosition;
  }

  public Enemies getEnemies() {
    return enemies;
  }

  public boolean isFight() {
    return enemies != null;
  }
}
