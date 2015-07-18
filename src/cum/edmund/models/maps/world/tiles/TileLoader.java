package cum.edmund.models.maps.world.tiles;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;

import cum.edmund.models.maps.world.StretchIcon;
import cum.edmund.models.maps.world.TileIcon;

public class TileLoader {

  private static final Map<TileType, ImageIcon> TILES;

  static {
    TILES = new ConcurrentHashMap<>();

    URL grassUrl = ClassLoader.getSystemResource("grass.png");
    URL playerUrl = ClassLoader.getSystemResource("player.png");
    URL enemyUrl = ClassLoader.getSystemResource("enemy.png");
    URL houseUrl = ClassLoader.getSystemResource("house.png");
    ImageIcon grassTile = new TileIcon(grassUrl);
    ImageIcon playerTile = new StretchIcon(playerUrl);
    ImageIcon enemyTile = new StretchIcon(enemyUrl);
    ImageIcon houseTile = new StretchIcon(houseUrl);
    TILES.put(TileType.GRASS, grassTile);
    TILES.put(TileType.PLAYER, playerTile);
    TILES.put(TileType.ENEMY, enemyTile);
    TILES.put(TileType.HOUSE, houseTile);
  }

  public enum TileType {
    GRASS, PLAYER, ENEMY, HOUSE
  };

  public static ImageIcon getTile(TileType type) {
    return TILES.get(type);
  }
}
