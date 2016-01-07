package cum.edmund.models.maps.world.tiles;

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
    loadTile(TileType.PLAYER, "player.png", DrawType.STRETCH);
    loadTile(TileType.ENEMY, "enemy.png", DrawType.STRETCH);
    loadTile(TileType.HOUSE, "house.png", DrawType.STRETCH);
  }

  public enum TileType {
    GRASS, PLAYER, ENEMY, HOUSE
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

  public static ImageIcon getTile(TileType type) {
    return TILES.get(type);
  }
}
