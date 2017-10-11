package cum.edmund.ui.fight.components;

/**
 * Represents a menu item and its associated task. Used by {@link ActionsPanel}
 * 
 * @author Ed
 *
 */
public class AssFuckMenu {
  private final String name;
  private final Runnable task;

  public AssFuckMenu(String name, Runnable task) {
    this.name = name;
    this.task = task;
  }

  public String getName() {
    return name;
  }

  public void select() {
    task.run();
  }
}
