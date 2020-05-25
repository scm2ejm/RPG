package cum.edmund.models.maps.world.tiles;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.ImageIcon;
import cum.edmund.models.maps.world.StretchIcon;
import cum.edmund.models.maps.world.TileIcon;
import cum.edmund.ui.UI;

/**
 * Loads tiles from file to be used by {@link UI}
 * 
 * @author Ed
 *
 */
public class TileLoader {

  private static final Map<TileType, ImageIcon> TILES;

  static {
    TILES = new ConcurrentHashMap<>();

    loadTile(TileType.GRASS, "grass.png", DrawType.TILE);
    loadTile(TileType.PLAYER, "walk.gif", DrawType.STRETCH);
    loadTile(TileType.ENEMY, "cat.gif", DrawType.STRETCH);
    loadTile(TileType.HOUSE, "house.png", DrawType.STRETCH);
    loadTile(TileType.HAND, "hand2.png", DrawType.STRETCH);
  }

  public enum TileType {
    GRASS, PLAYER, ENEMY, HOUSE, HAND
  };

  public enum DrawType {
    TILE, STRETCH
  };

  private static void loadTile(TileType tileType, String filename, DrawType drawType) {
    URL url = ClassLoader.getSystemResource(filename);
    switch (drawType) {
      case STRETCH:
        TILES.put(tileType, new StretchIcon(url));
        break;
      case TILE:
        TILES.put(tileType, new TileIcon(url));
        break;
    }
  }

  public static ImageIcon getNewPlayerTile() {
    return new StretchIcon(ClassLoader.getSystemResource("walk.gif"));
  }

  public static ImageIcon getNewEnemyTile() {
    return new StretchIcon(ClassLoader.getSystemResource("cat.gif"));
  }

  public static ImageIcon getTile(TileType type, ImageObserver observer) {
    ImageIcon icon = TILES.get(type);

    // This is to make GIFs animate
    icon.setImageObserver(observer);

    return icon;
  }
}
