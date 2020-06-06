package cum.edmund.newui.utils;

import cum.edmund.newui.View;

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

  public static int fineToCoarse(int fine) {
    if (fine == 0) {
      return fine;
    }

    int ratio = View.FINE_GRID_SIZE / View.COARSE_GRID_SIZE;
    float exact = fine / (float) ratio;
    return (int) exact;
  }

}
