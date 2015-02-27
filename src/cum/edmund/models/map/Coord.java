package cum.edmund.models.map;

/**
 * Shitty model to represent a point on the map
 * 
 * @author Ed
 *
 */
public class Coord {
  private final int x;
  private final int y;

  public Coord(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Coord) {
      Coord o = (Coord) other;
      return o.x == x && o.y == y;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return ((x * 199) + (y * 151) * 419);
  }

}
