package cum.edmund.models.maps.world;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import cum.edmund.models.WorldObject;
import cum.edmund.models.map.Coord;

/**
 * This class represents the whole world. May use other models to represent individual areas.
 * Implemented using a Sparse Matrix because most of the map will be empty
 * 
 * @author Ed
 *
 */
public class WorldMap {

  private Map<Coord, WorldObject> objectsByLocation;
  private Map<UUID, Coord> locationById;

  public WorldMap() {
    objectsByLocation = new ConcurrentHashMap<>();
    locationById = new ConcurrentHashMap<>();
  }

  public WorldObject get(int x, int y) {
    return get(new Coord(x, y));
  }

  public WorldObject get(Coord position) {
    WorldObject worldObject = objectsByLocation.get(position);
    return worldObject != null && worldObject.isEnabled() ? worldObject : null;
  }

  public Coord get(WorldObject worldObject) {
    return locationById.get(worldObject.getId());
  }

  public void put(int x, int y, WorldObject value) {
    put(new Coord(x, y), value);
  }

  public void put(Coord coord, WorldObject value) {
    Coord oldLocation = locationById.remove(value.getId());
    if (oldLocation != null) {
      // This is a move not an insert
      objectsByLocation.remove(oldLocation);
    }
    objectsByLocation.put(coord, value);
    locationById.put(value.getId(), coord);
  }

}
