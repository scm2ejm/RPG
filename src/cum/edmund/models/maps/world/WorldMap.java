package cum.edmund.models.maps.world;

import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cum.edmund.core.Configuration;
import cum.edmund.helpers.WalkHelper;
import cum.edmund.models.blocks.Barrier;
import cum.edmund.models.blocks.House;
import cum.edmund.models.characters.Direction;
import cum.edmund.models.characters.enemies.Enemies;
import cum.edmund.models.characters.hero.Hero;
import cum.edmund.models.map.SparseMatrix;
import cum.edmund.models.maps.world.tiles.TileLoader;
import cum.edmund.models.maps.world.tiles.TileLoader.TileType;
import cum.edmund.ui.worldmap.WorldMapView;

/**
 * This class represents the whole world. May use other models to represent individual areas.
 * Implemented using a Sparse Matrix because most of the map will be empty
 * 
 * @author Ed
 *
 */
public class WorldMap extends SparseMatrix<WorldMapElement> {

  private static final Logger LOGGER = LoggerFactory.getLogger(WorldMap.class);
  private Hero fucker;

  public WorldMap(Hero fucker) {
    this.fucker = fucker;
  }

  public void put(Barrier barrier) {
    WorldMapElement element = get(barrier.getPosition());

    // Create element if it doesn't already exist
    if (element == null) {
      element = new WorldMapElement();
      put(barrier.getPosition(), element);
    }

    if (element.getBarrier() != null) {
      LOGGER.warn("Overriding barrier at {}", barrier.getPosition());
    }

    element.setBarrier(barrier);
  }

  /**
   * Creates view of the world to be rendered
   * @param worldMapView 
   */
  public ImageIcon[][] createView(WorldMapView worldMapView) {

    int size = Configuration.UI_GRID_SIZE;
    int centre = size / 2;

    // Ensure there is a dead centre (ie 'size' must be even)
    if (centre * 2 == size) {
      throw new RuntimeException("Size needs to be an even number");
    }

    // This is the view of the world
    ImageIcon[][] view = new ImageIcon[size][size];

    int fuckerXPos = fucker.getPosition().getX();
    int fuckerYPos = fucker.getPosition().getY();

    for (int viewYPos = 0; viewYPos < size; viewYPos++) {

      // Calculate which world column this view column should display
      int worldYPos = fuckerYPos - (viewYPos - centre);

      for (int viewXPos = 0; viewXPos < size; viewXPos++) {

        // Calculate which world row this view row should display
        int worldXPos = fuckerXPos + (viewXPos - centre);

        // Get the current world element for this view cell
        WorldMapElement element = get(worldXPos, worldYPos);

        // Apply the tile for this element to the view
        view[viewYPos][viewXPos] = getTile(element, viewXPos == centre && viewYPos == centre, worldMapView);

      }
    }

    return view;
  }

  /**
   * Looks up a tile for given element
   * 
   * @param element WorldMapElement to display
   * @param worldMapView 
   * @return Tile to display
   */
  public ImageIcon getTile(WorldMapElement element, boolean hero, ImageObserver observer) {
    if (hero) {
      return TileLoader.getTile(TileType.PLAYER, observer);
    } else if (element == null) {
      return TileLoader.getTile(TileType.GRASS, observer);
    } else if (element.getBarrier() instanceof House) {
      return TileLoader.getTile(TileType.HOUSE, observer);
    } else if (element.getBarrier() instanceof Enemies) {
      return TileLoader.getTile(TileType.ENEMY, observer);
    } else {
      throw new RuntimeException("what the fuck tile is this?");
    }

  }


  // TODO: FIX MOUSE AND DELETE toString() METHOD!!!

  @Override
  public String toString() {

    int xCentre = fucker.getPosition().getX();
    int yCentre = fucker.getPosition().getY();

    StringBuilder sb = new StringBuilder();

    sb.append("--------");

    for (int yOffset = 5; yOffset >= -5; yOffset--) {

      int yCurrent = yCentre + yOffset;

      for (int xOffset = -5; xOffset <= 5; xOffset++) {

        int xCurrent = xCentre + xOffset;

        WorldMapElement element = get(xCurrent, yCurrent);

        if (xOffset == 0 && yOffset == 0) {
          sb.append("X");
        } else if (element == null) {
          sb.append(".");
        } else if (element.getBarrier() instanceof House) {
          sb.append("H");
        } else if (element.getBarrier() instanceof Enemies) {
          sb.append("E");
        } else {
          sb.append("?");
        }
      }

      sb.append("\n");
    }

    sb.append("--------");

    return sb.toString();
  }

  public WalkOutcome walk(Direction direction) {
    WalkOutcome outcome = WalkHelper.walk(fucker, direction, this);

    if (outcome.isFight()) {
      LOGGER.debug("Starting fight!");
    }

    return outcome;
  }
}
