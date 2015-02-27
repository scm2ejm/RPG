package cum.edmund.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Modified from
 * http://stackoverflow.com/questions/2050490/what-collection-to-use-instead-of-2d-array-in-java
 * 
 * @author Ed
 */
public class SparseMatrix<T> {
  private Map<Coord, T> values;

  public SparseMatrix() {
    values = new HashMap<Coord, T>();
  }

  public T get(int x, int y) {
    return values.get(new Coord(x, y));
  }

  public void put(int x, int y, T value) {
    values.put(new Coord(x, y), value);
  } 
  
  public void put(Coord coord, T value) {
    values.put(coord, value);
  } 
}
