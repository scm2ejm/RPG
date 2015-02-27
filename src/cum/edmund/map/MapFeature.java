package cum.edmund.map;

/**
 * Model for a thing you could find at a specific x and y coordinate on the map
 * 
 * @author Ed
 *
 */
public class MapFeature {

  private String name;

  private MapFeatureType type;

  private Coord coord;

  public MapFeature(String name, MapFeatureType type, Coord coord) {
    this.name = name;
    this.type = type;
    this.coord = coord;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MapFeatureType getType() {
    return type;
  }

  public void setType(MapFeatureType type) {
    this.type = type;
  }

  public Coord getCoord() {
    return coord;
  }

  public void setCoord(Coord coord) {
    this.coord = coord;
  }


}
