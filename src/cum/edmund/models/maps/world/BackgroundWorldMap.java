package cum.edmund.models.maps.world;

import cum.edmund.models.WorldObject;
import cum.edmund.models.blocks.Grass;
import cum.edmund.models.map.Coord;

/**
 * This class extends the standard {@link WorldMap} to provide a default texture
 * 
 * @author Ed
 *
 */
public class BackgroundWorldMap extends WorldMap {

  private static final WorldObject DEFAULT_TEXTURE = new Grass();

  public BackgroundWorldMap() {
    super();
  }

  public WorldObject get(Coord position) {
    WorldObject worldObject = super.get(position);
    if (worldObject == null) {
      return DEFAULT_TEXTURE;
    } else {
      return worldObject;
    }
  }

}
