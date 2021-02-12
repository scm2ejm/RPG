package cum.edmund.models;

import java.util.UUID;
import javax.swing.ImageIcon;
import cum.edmund.models.maps.world.tiles.TileLoader;
import cum.edmund.models.maps.world.tiles.TileLoader.DrawType;

/**
 * A world object is something that the hero can interact with
 * 
 * @author Ed
 *
 */
public abstract class WorldObject {

  private final UUID id;
  private final String name;
  private final ImageIcon unscaledImage;
  private final ImageIcon scaledImage;
  private WorldObjectType type;
  private boolean enabled;

  /**
   * If true then this object cannot be walked through. Default is false
   */
  private boolean barrier;

  public WorldObject(String name, WorldObjectType type, String filename) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.type = type;
    this.unscaledImage = new ImageIcon(ClassLoader.getSystemResource(filename));
    this.scaledImage = TileLoader.loadTile(filename, DrawType.STRETCH);

    // By default this object can be walked through
    this.barrier = false;
    this.enabled = true;
  }

  public WorldObjectType getType() {
    return type;
  }

  public void setType(WorldObjectType type) {
    this.type = type;
  }

  public boolean barrier() {
    return enabled && barrier;
  }

  public void setBarrier(boolean barrier) {
    this.barrier = barrier;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public ImageIcon getUnscaledImage() {
    return unscaledImage;
  }
  
  public ImageIcon getScaledImage() {
    return scaledImage;
  }

  public boolean isEnabled() {
    return enabled;
  }
  
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

}
