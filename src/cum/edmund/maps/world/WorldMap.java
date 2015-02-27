package cum.edmund.maps.world;

import cum.edmund.map.MapFeature;
import cum.edmund.map.SparseMatrix;

/**
 * This class represents the whole world. May use other models to represent individual areas.
 * Implemented using a Sparse Matrix because most of the map will be empty
 * 
 * @author Ed
 *
 */
public class WorldMap extends SparseMatrix<MapFeature> {
  public void putFeature(MapFeature feature) {
    put(feature.getCoord(), feature);
  }
}
