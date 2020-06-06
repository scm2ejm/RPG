package cum.edmund.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cum.edmund.ui.utils.GridConverter;

/**
 * Contains metadata relating to current perspective
 * 
 * @author Ed
 *
 */
public class View {
  private static final Logger LOGGER = LoggerFactory.getLogger(View.class);

  public static final int MULTIPLIER = 5;

  public static final int COARSE_GRID_SIZE = 11;
  public static final int FINE_GRID_SIZE = COARSE_GRID_SIZE * MULTIPLIER;

  public static final int HALF_COURSE_GRID_SIZE = (COARSE_GRID_SIZE - 1) / 2;
  public static final int HALF_FINE_GRID_SIZE = (FINE_GRID_SIZE - 1) / 2;

  private int viewX;
  private int viewY;

  public View() {
    // Starting at -5, -5
    viewX = -HALF_COURSE_GRID_SIZE;
    viewY = -HALF_COURSE_GRID_SIZE;

  }

  public int getViewX() {
    return viewX;
  }

  public void setViewX(int viewX) {
    this.viewX = viewX;

    log();
  }

  private void log() {
    LOGGER.info("----");
    LOGGER.info("View is X {} to {} (course)", viewX, viewX - 1 + COARSE_GRID_SIZE);
    LOGGER.info("View is Y {} to {} (course)", viewY, viewY - 1 + COARSE_GRID_SIZE);
    LOGGER.info("View is X {} to {} (fine)", GridConverter.coarseToFine(viewX),
        GridConverter.coarseToFine(viewX - 1 + COARSE_GRID_SIZE));
    LOGGER.info("View is Y {} to {} (fine)", GridConverter.coarseToFine(viewY),
        GridConverter.coarseToFine(viewY - 1 + COARSE_GRID_SIZE));
    LOGGER.info("----");
  }

  public int getViewY() {
    return viewY;
  }

  public void setViewY(int viewY) {
    this.viewY = viewY;

    log();
  }
}
