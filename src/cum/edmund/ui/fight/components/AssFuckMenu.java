package cum.edmund.ui.fight.components;

import java.util.Optional;

/**
 * Represents a menu. Used by {@link ActionsPanel}
 * 
 * @author Ed
 *
 */
public class AssFuckMenu {
  private final String name;
  private final Runnable task;
  private String couldPerform;
  private String isPerforming;
  private String hasPerformed;
  private String iconFilename;
  private int duration;

  public AssFuckMenu(String name, Runnable task) {
    this.name = name;
    this.task = task;
    
    // 2.5 seconds by default
    this.duration = 2500;
  }

  public String couldPerform() {
    return Optional.ofNullable(couldPerform).orElse("Perform " + name + "...");
  }
  
  public String isPerforming() {
    return Optional.ofNullable(isPerforming).orElse("Hero is performing " + name + "...");
  }
  
  public String hasPerformed() {
    return Optional.ofNullable(hasPerformed).orElse("Select an action");
  }

  public void select() {
    task.run();
  }

  public String getIconFilename() {
    return iconFilename;
  }

  public void setIconFilename(String iconFilename) {
    this.iconFilename = iconFilename;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public String getName() {
    return name;
  }
  

}
