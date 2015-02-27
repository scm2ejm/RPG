package cum.edmund.models.maps.world;

import cum.edmund.models.map.Coord;


/**
 * When walking, you may be successful or not and you may have some enemies to fight
 * 
 * @author Ed
 *
 */
public class WalkOutcome {
  private final boolean success;
  private final boolean fight;
  private final WorldMapElement element;
  private final Coord newPosition;

  public WalkOutcome(boolean success, boolean fight, Coord newPosition, WorldMapElement element) {
    this.success = success;
    this.fight = fight;
    this.newPosition = newPosition;
    this.element = element;
  }

  public boolean isSuccess() {
    return success;
  }

  public WorldMapElement getElement() {
    return element;
  }

  public Coord getNewPosition() {
    return newPosition;
  }

  public boolean isFight() {
    return fight;
  }
}
