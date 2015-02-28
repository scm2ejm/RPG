package cum.edmund.models.maps.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cum.edmund.models.blocks.Barrier;
import cum.edmund.models.blocks.House;
import cum.edmund.models.characters.enemies.FightableNPC;

/**
 * Model represents the objects contained within a point on the map. They may be many enemies and/or
 * items, etc
 * 
 * @author Ed
 *
 */
public class WorldMapElement {

  private List<FightableNPC> enemies = Collections.synchronizedList(new ArrayList<>());

  private Barrier barrier;

  public List<FightableNPC> getEnemies() {
    return enemies;
  }

  public void setEnemies(List<FightableNPC> enemies) {
    this.enemies = enemies;
  }

  public House getHouse() {
    return (House) barrier;
  }

  public Barrier getBarrier() {
    return barrier;
  }

  public void setBarrier(Barrier barrier) {
    this.barrier = barrier;
  }

}
