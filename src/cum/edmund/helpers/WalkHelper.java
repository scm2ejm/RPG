package cum.edmund.helpers;

import java.math.RoundingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cum.edmund.models.WorldObject;
import cum.edmund.models.characters.Character;
import cum.edmund.models.characters.Direction;
import cum.edmund.models.characters.enemies.Enemies;
import cum.edmund.models.map.Coord;
import cum.edmund.models.maps.world.WalkOutcome;
import cum.edmund.models.maps.world.WorldMap;
import cum.edmund.ui.utils.GridConverter;

/**
 * Helper utility regarding moving a player around the world map
 * 
 * @author Ed
 *
 */
public class WalkHelper {

  private static final Logger LOGGER = LoggerFactory.getLogger(WalkHelper.class);

  /**
   * Makes the character walk in a direct
   * 
   * @param direction
   * @return true if successful, false if unable to walk in that direction
   */
  public static WalkOutcome walk(Character character, Direction direction,
      WorldMap characterWorldMap, WorldMap blockersWorldMap) {
    Coord oldPositionFine = characterWorldMap.get(character);
    Coord newPositionFine = adjacentPosition(oldPositionFine, direction);
    Coord newPositionCoarse = GridConverter.fineToCoarse(newPositionFine, RoundingMode.UP);

    WorldObject adjacentElement = blockersWorldMap.get(newPositionCoarse);

    boolean success;

    if (adjacentElement == null) {
      success = true;
    } else {
      success = !adjacentElement.isBarrier();
    }

    if (success) {
      characterWorldMap.put(newPositionFine, character);
      LOGGER.debug("{} walks {}. New position is (fine) {}", character.getName(), direction,
          newPositionFine);
    } else {
      newPositionFine = oldPositionFine;
      LOGGER.debug("{} cannot walk {}, the path is blocked. New position is (fine) {}",
          character.getName(), direction, newPositionFine);
    }

    Enemies enemies = closeEnemies(character, characterWorldMap, blockersWorldMap);

    return new WalkOutcome(success, enemies, newPositionFine, adjacentElement);
  }

  /**
   * Used to look for enemies in immediate area
   */
  private static Enemies closeEnemies(Character fucker, WorldMap characterWorldMap,
      WorldMap blockersLayer) {

    Coord playerLocation = GridConverter.fineToCoarse(characterWorldMap.get(fucker));
    int xCentre = playerLocation.getX();
    int yCentre = playerLocation.getY();

    for (int yOffset = 1; yOffset >= -1; yOffset--) {

      int yCurrent = yCentre + yOffset;

      for (int xOffset = -1; xOffset <= 1; xOffset++) {

        int xCurrent = xCentre + xOffset;

        WorldObject element = blockersLayer.get(xCurrent, yCurrent);

        if (element instanceof Enemies) {
          // Found adjacent enemies
          return (Enemies) element;
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
        return new Coord(x, y - 1);
      case NORTH_EAST:
        return new Coord(x + 1, y - 1);
      case NORTH_WEST:
        return new Coord(x - 1, y - 1);
      case SOUTH:
        return new Coord(x, y + 1);
      case SOUTH_EAST:
        return new Coord(x + 1, y + 1);
      case SOUTH_WEST:
        return new Coord(x - 1, y + 1);
      case WEST:
        return new Coord(x - 1, y);
      default:
        throw new RuntimeException("Dafuq is " + direction);
    }
  }
}
