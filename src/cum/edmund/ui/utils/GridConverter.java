package cum.edmund.ui.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
   * Converts from a fine grid value to a course grid value. Uses "half-up" rounding
   */
  public static int fineToCoarse(int fine) {
    return fineToCoarse(fine, RoundingMode.HALF_UP);
  }

  /**
   * Converts from a fine grid value to a course grid value
   */
  public static int fineToCoarse(int fine, RoundingMode roundingMode) {
    if (fine == 0) {
      return fine;
    }

    int ratio = View.FINE_GRID_SIZE / View.COARSE_GRID_SIZE;
    BigDecimal exact = BigDecimal.valueOf(fine).divide(BigDecimal.valueOf(ratio), roundingMode);
    return exact.intValue();
  }

  public static Coord coarseToFine(Coord coarse) {
    return new Coord(coarseToFine(coarse.getX()), coarseToFine(coarse.getY()));
  }

  public static Coord fineToCoarse(Coord fine) {
    return new Coord(fineToCoarse(fine.getX()), fineToCoarse(fine.getY()));
  }

  public static Coord fineToCoarse(Coord fine, RoundingMode roundingMode) {
    return new Coord(fineToCoarse(fine.getX(), roundingMode),
        fineToCoarse(fine.getY(), roundingMode));
  }

}
