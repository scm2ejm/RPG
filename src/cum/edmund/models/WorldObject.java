package cum.edmund.models;

import java.util.UUID;
import javax.swing.ImageIcon;

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
  private WorldObjectType type;

  /**
   * If true then this object cannot be walked through. Default is false
   */
  private boolean barrier;

  public WorldObject(String name, WorldObjectType type, String filename) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.type = type;
    this.unscaledImage = new ImageIcon(ClassLoader.getSystemResource(filename));

    // By default this object can be walked through
    this.barrier = false;
  }

  public WorldObjectType getType() {
    return type;
  }

  public void setType(WorldObjectType type) {
    this.type = type;
  }

  public boolean isBarrier() {
    return barrier;
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

}
