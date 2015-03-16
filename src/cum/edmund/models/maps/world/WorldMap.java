package cum.edmund.models.maps.world;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cum.edmund.models.blocks.Barrier;
import cum.edmund.models.blocks.House;
import cum.edmund.models.characters.enemies.Enemies;
import cum.edmund.models.characters.hero.Hero;
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

  private Hero fucker;

  static {
    LOGGER = LoggerFactory.getLogger(WorldMap.class);
  }

  public WorldMap(Hero fucker) {
    this.fucker = fucker;
  }

  public void put(Barrier barrier) {
    WorldMapElement element = get(barrier.getPosition());

    // Create element if it doesn't already exist
    if (element == null) {
      element = new WorldMapElement();
      put(barrier.getPosition(), element);
    }

    if (element.getBarrier() != null) {
      LOGGER.warn("Overriding barrier at {}", barrier.getPosition());
    }

    element.setBarrier(barrier);
  }

  @Override
  public String toString() {

    int xCentre = fucker.getPosition().getX();
    int yCentre = fucker.getPosition().getY();

    StringBuilder sb = new StringBuilder();

    sb.append("--------");

    for (int yOffset = 5; yOffset >= -5; yOffset--) {

      int yCurrent = yCentre + yOffset;

      for (int xOffset = -5; xOffset <= 5; xOffset++) {

        int xCurrent = xCentre + xOffset;

        WorldMapElement element = get(xCurrent, yCurrent);

        if (xOffset == 0 && yOffset == 0) {
          sb.append("X");
        } else if (element == null) {
          sb.append(".");
        } else if (element.getBarrier() instanceof House) {
          sb.append("H");
        } else if (element.getBarrier() instanceof Enemies) {
          sb.append("E");
        } else {
          sb.append("?");
        }
      }

      sb.append("\n");
    }

    sb.append("--------");

    return sb.toString();
  }
}
