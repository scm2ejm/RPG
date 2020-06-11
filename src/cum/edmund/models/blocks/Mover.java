package cum.edmund.models.blocks;

import java.util.TimerTask;
import cum.edmund.models.WorldObject;
import cum.edmund.models.characters.Direction;
import cum.edmund.models.map.Coord;
import cum.edmund.models.maps.world.WorldMap;

/**
 * Moves a world object in given direction
 * 
 * @author Ed
 *
 */
public class Mover extends TimerTask {

  private WorldObject worldObject;
  private Direction direction;
  private WorldMap worldMap;

  public Mover(WorldObject birds, Direction direction, WorldMap worldMap, boolean onlyWhileVisible) {
    this.worldObject = birds;
    this.direction = direction;
    this.worldMap = worldMap;
  }

  @Override
  public void run() {
    Coord oldPosition = worldMap.get(worldObject);

    switch (direction) {
      case EAST:
        worldMap.put(new Coord(oldPosition.getX() + 1, oldPosition.getY()), worldObject);
        break;
      case NORTH:
        worldMap.put(new Coord(oldPosition.getX(), oldPosition.getY() - 1), worldObject);
        break;
      case NORTH_EAST:
        worldMap.put(new Coord(oldPosition.getX() + 1, oldPosition.getY() - 1), worldObject);
        break;
      case NORTH_WEST:
        worldMap.put(new Coord(oldPosition.getX() - 1, oldPosition.getY() - 1), worldObject);
        break;
      case SOUTH:
        worldMap.put(new Coord(oldPosition.getX(), oldPosition.getY() + 1), worldObject);
        break;
      case SOUTH_EAST:
        worldMap.put(new Coord(oldPosition.getX() + 1, oldPosition.getY() + 1), worldObject);
        break;
      case SOUTH_WEST:
        worldMap.put(new Coord(oldPosition.getX() - 1, oldPosition.getY() + 1), worldObject);
        break;
      case WEST:
        worldMap.put(new Coord(oldPosition.getX() - 1, oldPosition.getY()), worldObject);
        break;
      default:
        break;

    }

  }

}
