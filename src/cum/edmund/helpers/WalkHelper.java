package cum.edmund.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cum.edmund.models.characters.Character;
import cum.edmund.models.characters.Direction;
import cum.edmund.models.characters.enemies.Enemies;
import cum.edmund.models.map.Coord;
import cum.edmund.models.maps.world.WalkOutcome;
import cum.edmund.models.maps.world.WorldMap;
import cum.edmund.models.maps.world.WorldMapElement;

public class WalkHelper {

  private static final Logger LOGGER;

  static {
    LOGGER = LoggerFactory.getLogger(WalkHelper.class);
  }

  /**
   * Makes the character walk in a direct
   * 
   * @param direction
   * @return true if successful, false if unable to walk in that direction
   */
  public static WalkOutcome walk(Character character, Direction direction, WorldMap map) {
    Coord oldPosition = character.getPosition();
    Coord newPosition = adjacentPosition(oldPosition, direction);

    WorldMapElement adjacentElement = map.get(newPosition);

    boolean success;

    if (adjacentElement == null) {
      success = true;
    } else {
      success = adjacentElement.getBarrier() == null;
    }

    if (success) {
      character.setPosition(newPosition);
      LOGGER.debug("{} walks {}. New position is {}", character.getName(), direction, newPosition);
    } else {
      newPosition = oldPosition;
      LOGGER.debug("{} cannot walk {}, the path is blocked. New position is {}",
          character.getName(), direction, newPosition);
    }

    Enemies enemies = closeEnemies(character, map);

    return new WalkOutcome(success, enemies, newPosition, adjacentElement);
  }

  /**
   * Used to look for enemies in immediate area
   */
  private static Enemies closeEnemies(Character fucker, WorldMap map) {

    int xCentre = fucker.getPosition().getX();
    int yCentre = fucker.getPosition().getY();

    for (int yOffset = 1; yOffset >= -1; yOffset--) {

      int yCurrent = yCentre + yOffset;

      for (int xOffset = -1; xOffset <= 1; xOffset++) {

        int xCurrent = xCentre + xOffset;

        WorldMapElement element = map.get(xCurrent, yCurrent);

        if (element != null && element.getEnemies() != null) {
          // Found adjacent enemies
          return element.getEnemies();
        }
      }

    }

    return null;
  }

  private static Coord adjacentPosition(Coord position, Direction direction) {

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
