package cum.edmund.models.maps.world;

import cum.edmund.models.WorldObject;
import cum.edmund.models.map.SparseMatrix;

/**
 * This class represents the whole world. May use other models to represent individual areas.
 * Implemented using a Sparse Matrix because most of the map will be empty
 * 
 * @author Ed
 *
 */
public class WorldMap extends SparseMatrix<WorldObject> {
  public void put(WorldObject object) {
    put(object.getPosition(), object);
  }
}
