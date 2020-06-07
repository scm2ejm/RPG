package cum.edmund.helpers;

import java.util.HashSet;
import java.util.Set;
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

    // Check all of the tiles that we would be covering if we made this move
    boolean success = true;
    Set<WorldObject> adjacentTiles = new HashSet<>();
    for (Coord destination : GridConverter.fineToCoarseExactThing(newPositionFine)) {
      WorldObject adjacentElement = blockersWorldMap.get(destination);
      if (adjacentElement != null) {
        adjacentTiles.add(adjacentElement);

        if (adjacentElement.isBarrier()) {
          // This is a tile that we can't walk through
          success = false;
        }
      }
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

    Enemies enemies = closeEnemies(adjacentTiles);

    return new WalkOutcome(success, enemies, newPositionFine, adjacentTiles);
  }

  /**
   * Used to look for enemies in immediate area
   */
  private static Enemies closeEnemies(Set<WorldObject> adjacentTiles) {
    for (WorldObject object : adjacentTiles) {
      if (object instanceof Enemies) {
        return (Enemies) object;
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
