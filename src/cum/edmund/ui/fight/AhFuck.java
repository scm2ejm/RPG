package cum.edmund.ui.fight;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import cum.edmund.models.maps.world.tiles.TileLoader;

/**
 * Screen used when all players die
 * 
 * @author Ed
 *
 */
public class AhFuck extends JLabel {

  private static final ImageIcon AH_FUCK = TileLoader.ahFuckTile();

  public AhFuck(JPanel fightView) {
    setIcon(AH_FUCK);
    AH_FUCK.setImageObserver(fightView);
  }
}
