package cum.edmund.newui;

/**
 * Contains metadata relating to current perspective
 * 
 * @author Ed
 *
 */
public class View {
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
  }

  public int getViewY() {
    return viewY;
  }

  public void setViewY(int viewY) {
    this.viewY = viewY;
  }
}
