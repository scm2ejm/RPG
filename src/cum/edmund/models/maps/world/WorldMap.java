package cum.edmund.models.maps.world;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cum.edmund.models.characters.Character;
import cum.edmund.models.characters.Direction;
import cum.edmund.models.characters.enemies.FightableNPC;
import cum.edmund.models.house.House;
import cum.edmund.models.map.Coord;
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

  public void putEnemy(FightableNPC enemy) {
    WorldMapElement element = get(enemy.getPosition());

    // Create element if it doesn't already exist
    if (element == null) {
      element = new WorldMapElement();
      put(enemy.getPosition(), element);
    }

    element.getEnemies().add(enemy);

    // Can't have more than 6 enemies in an element
    if (element.getEnemies().size() > 6) {
      throw new RuntimeException("Cannot fit more enemies in " + enemy.getPosition());
    }

  }

  public void putHouse(House house) {
    WorldMapElement element = get(house.getPosition());

    // Create element if it doesn't already exist
    if (element == null) {
      element = new WorldMapElement();
      put(house.getPosition(), element);
    }

    if (element.getHouse() != null) {
      LOGGER.warn("Overriding house at {}", house.getPosition());
    }

    element.setHouse(house);
  }

  /**
   * Makes the character walk in a direct
   * 
   * @param direction
   * @return true if successful, false if unable to walk in that direction
   */
  public WalkOutcome walk(Character character, Direction direction) {
    Coord oldPosition = character.getPosition();
    Coord newPosition = adjacentPosition(oldPosition, direction);

    WorldMapElement adjacentElement = get(newPosition);

    boolean success;

    if (adjacentElement == null) {
      success = true;
    } else {
      success = adjacentElement.getHouse() == null;
    }

    if (success) {
      character.setPosition(newPosition);
      LOGGER.debug("{} walks {}. New position is {}", character.getName(), direction, newPosition);
    } else {
      newPosition = oldPosition;
      LOGGER.debug("{} cannot walk {}, the path is blocked. New position is {}",
          character.getName(), direction, newPosition);
    }

    int enemyCount = adjacentElement == null ? 0 : adjacentElement.getEnemies().size();
    if (enemyCount > 0) {
      LOGGER.debug("{} has to fight {} enemies!", character.getName(), enemyCount);
    }

    return new WalkOutcome(success, enemyCount > 0, newPosition, adjacentElement);
  }

  private Coord adjacentPosition(Coord position, Direction direction) {

    int x = position.getX();
    int y = position.getY();

    switch (direction) {
      case EAST:
        return new Coord(x + 1, y);
      case NORTH:
        return new Coord(x, y + 1);
      case NORTH_EAST:
        return new Coord(x + 1, y + 1);
      case NORTH_WEST:
        return new Coord(x - 1, y + 1);
      case SOUTH:
        return new Coord(x, y - 1);
      case SOUTH_EAST:
        return new Coord(x + 1, y - 1);
      case SOUTH_WEST:
        return new Coord(x - 1, y - 1);
      case WEST:
        return new Coord(x - 1, y);
      default:
        throw new RuntimeException("Dafuq is " + direction);
    }
  }

}
