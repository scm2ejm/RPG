package cum.edmund.models.map;

/**
 * Shitty model to represent a point on the map. Modified from
 * http://stackoverflow.com/questions/2050490/what-collection-to-use-instead-of-2d-array-in-java
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

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
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

  @Override
  public String toString() {
    return "(" + x + "," + y + ")";
  }
}
