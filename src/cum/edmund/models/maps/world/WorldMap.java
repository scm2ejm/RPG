package cum.edmund.models.maps.world;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cum.edmund.models.characters.enemies.FightableNPC;
import cum.edmund.models.house.House;
import cum.edmund.models.map.SparseMatrix;

/**
 * This class represents the whole world. May use other models to represent individual areas.
 * Implemented using a Sparse Matrix because most of the map will be empty
 * 
 * @author Ed
 *
 */
public class WorldMap extends SparseMatrix<WorldMapElement> {

  private static final Logger LOGGER;

  static {
    LOGGER = LoggerFactory.getLogger(WorldMap.class);
  }

  public void putEnemy(FightableNPC object) {
    WorldMapElement element = get(object.getPosition());

    // Create element if it doesn't already exist
    if (element == null) {
      element = new WorldMapElement();
      put(object.getPosition(), element);
    }

    element.getEnemies().add(object);

    // Can't have more than 6 enemies in an element
    if (element.getEnemies().size() > 6) {
      throw new RuntimeException("Cannot fit more enemies in " + object.getPosition());
    }

  }

  public void putHouse(House object) {
    WorldMapElement element = get(object.getPosition());

    // Create element if it doesn't already exist
    if (element == null) {
      element = new WorldMapElement();
      put(object.getPosition(), element);
    }

    if (element.getHouse() != null) {
      LOGGER.warn("Overriding house at {}", object.getPosition());
    }

    element.setHouse(object);
  }

}
