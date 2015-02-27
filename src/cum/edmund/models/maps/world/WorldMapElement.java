package cum.edmund.models.maps.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cum.edmund.models.characters.enemies.FightableNPC;
import cum.edmund.models.house.House;

/**
 * Model represents the objects contained within a point on the map. They may be many enemies and/or
 * items, etc
 * 
 * @author Ed
 *
 */
public class WorldMapElement {

  private List<FightableNPC> enemies = Collections.synchronizedList(new ArrayList<>());

  private House house;

  public List<FightableNPC> getEnemies() {
    return enemies;
  }

  public void setEnemies(List<FightableNPC> enemies) {
    this.enemies = enemies;
  }

  public House getHouse() {
    return house;
  }

  public void setHouse(House house) {
    this.house = house;
  }

}
