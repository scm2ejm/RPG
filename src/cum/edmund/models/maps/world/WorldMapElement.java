package cum.edmund.models.maps.world;

import cum.edmund.models.blocks.Barrier;
import cum.edmund.models.blocks.House;
import cum.edmund.models.characters.enemies.Enemies;

/**
 * Model represents the objects contained within a point on the map. They may be many enemies and/or
 * items, etc
 * 
 * @author Ed
 *
 */
public class WorldMapElement {

  private Barrier barrier;

  public House getHouse() {
    if (barrier instanceof House) {
      return (House) barrier;
    } else {
      return null;
    }
  }

  public Enemies getEnemies() {
    if (barrier instanceof Enemies) {
      return (Enemies) barrier;
    } else {
      return null;
    }
  }

  public Barrier getBarrier() {
    return barrier;
  }

  public void setBarrier(Barrier barrier) {
    this.barrier = barrier;
  }

}
