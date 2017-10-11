package cum.edmund.ui.fight.components;

import java.util.HashMap;
import java.util.Map;

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

  public AssFuckMenu(String name, Runnable task) {
    this.name = name;
    this.task = task;
    children = new HashMap<>();

    // setForeground(Color.WHITE);
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
}
