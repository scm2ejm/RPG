package cum.edmund.ui.fight.components;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.swing.JLabel;

/**
 * Represents a menu. Used by {@link ActionsPanel}
 * 
 * @author Ed
 *
 */
public class AssFuckMenu {
  private final String name;
  private final Runnable task;
  private final Map<AssFuckMenu, JLabel> children;
  private String couldPerform;
  private String isPerforming;
  private String hasPerformed;

  public AssFuckMenu(String name, Runnable task) {
    this.name = name;
    this.task = task;
    children = new HashMap<>();
  }

  public void addChild(AssFuckMenu menu) {
    JLabel menuLabel = new JLabel(menu.getName());
    children.put(menu, menuLabel);
  }

  public Map<AssFuckMenu, JLabel> getChildren() {
    return children;
  }

  public String getName() {
    return name;
  }

  public void select() {
    task.run();
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
}
