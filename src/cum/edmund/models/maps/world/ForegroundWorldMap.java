package cum.edmund.models.maps.world;

import java.awt.event.KeyEvent;
import java.util.Random;
import cum.edmund.models.blocks.Birds;
import cum.edmund.models.map.Coord;
import cum.edmund.ui.View;

/**
 * Foreground map allows things like birds and clouds to float by
 * 
 * @author Ed
 *
 */
public class ForegroundWorldMap extends WorldMap {

  private View view;

  public ForegroundWorldMap(View view) {
    this.view = view;

    // Put some birds on screen to start with
    birds();
  }

  public void birds() {
//    int startY = View.HALF_COURSE_GRID_SIZE + 1 + view.getViewY();
//    int randomX = new Random().nextInt(View.HALF_COURSE_GRID_SIZE) + view.getViewY();
    
    int startY = View.HALF_COURSE_GRID_SIZE + 1;
    int randomX = new Random().nextInt(View.HALF_COURSE_GRID_SIZE);
    put(new Coord(randomX - 1, startY + 1), new Birds(this));
    put(new Coord(randomX, startY), new Birds(this));
    put(new Coord(randomX + 1, startY + 1), new Birds(this));
    put(new Coord(randomX + 2, startY + 2), new Birds(this));
  }

  public void handleKeyPress(int key) {
    if (key == KeyEvent.VK_T) {
      birds();
    }
  }

}
