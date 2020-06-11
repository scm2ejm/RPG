package cum.edmund.models.blocks;

import java.util.Timer;
import cum.edmund.models.WorldObject;
import cum.edmund.models.WorldObjectType;
import cum.edmund.models.characters.Direction;
import cum.edmund.models.maps.world.WorldMap;

/**
 * Model of an impassable mountain
 * 
 * @author Ed
 *
 */
public class Birds extends WorldObject {

  private final int maxExecutions = 100;

  public Birds(WorldMap worldMap) {
    super("Birds", WorldObjectType.BACKGROUND, "bigbirds.gif");

    // Schedule the moving of the birds
    Timer timer = new Timer();
    int executions = 0;
    while (executions++ < maxExecutions) {
      timer.schedule(new Mover(this, Direction.NORTH, worldMap, true), (1000L * executions));
    }
  }

}
