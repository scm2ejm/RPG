package cum.edmund.ui.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import cum.edmund.models.map.Coord;
import cum.edmund.ui.View;

/**
 * Converts fine grained to coarse grained pixel counts
 * 
 * @author Ed
 *
 */
public class GridConverter {

  public static int coarseToFine(int course) {
    return (View.FINE_GRID_SIZE / View.COARSE_GRID_SIZE) * course;
  }

  /**
   * Converts from a fine grid value to a course grid value
   */
  public static int fineToCoarse(int fine) {
    return fineToCoarseExact(fine, RoundingMode.DOWN).intValue();
  }

  /**
   * Converts from a fine grid value to a course grid value
   */
  public static BigDecimal fineToCoarseExact(int fine, RoundingMode roundingMode) {
    if (fine == 0) {
      return BigDecimal.ZERO;
    }

    BigDecimal ratio = BigDecimal.valueOf(View.FINE_GRID_SIZE)
        .divide(BigDecimal.valueOf(View.COARSE_GRID_SIZE), roundingMode);
    return BigDecimal.valueOf(fine).divide(ratio, roundingMode);
  }

  /**
   * Converts from a fine grid value to a course grid value
   */
  public static Set<Coord> fineToCoarseExactThing(Coord fine) {
    Set<Integer> xs = fineToCoarseExactThing(fine.getX());
    Set<Integer> ys = fineToCoarseExactThing(fine.getY());
    Set<Coord> coords = new HashSet<>();
    for (int x : xs) {
      for (int y : ys) {
        coords.add(new Coord(x, y));
      }
    }
    return coords;
  }

  public static Set<Integer> fineToCoarseExactThing(int fine) {
    if (fine == 0) {
      return Collections.singleton(fine);
    }

    float ratio = View.FINE_GRID_SIZE / (float) View.COARSE_GRID_SIZE;

    float exact = fine / ratio;
    Set<Integer> course = new HashSet<>();
    if (isInteger(exact)) {
      course.add((int) exact);
    } else {
      course.add((int) Math.floor(exact));
      course.add((int) Math.ceil(exact));
    }
    return course;
  }

  /**
   * Returns true if given float is a whole number. Tolerance is 0.0001f
   */
  public static boolean isInteger(float f) {
    return Math.abs(f) - (int) Math.abs(f) < 0.0001f;
  }

  public static Coord coarseToFine(Coord coarse) {
    return new Coord(coarseToFine(coarse.getX()), coarseToFine(coarse.getY()));
  }

  public static Coord fineToCoarse(Coord fine) {
    return new Coord(fineToCoarse(fine.getX()), fineToCoarse(fine.getY()));
  }

}
