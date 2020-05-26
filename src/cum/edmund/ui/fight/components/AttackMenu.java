package cum.edmund.ui.fight.components;

/**
 * Behaviour relating to the attack menu
 * 
 * @author Ed
 *
 */
public class AttackMenu extends AssFuckMenu{

  public AttackMenu(String name, Runnable task) {
    super(name, task);
  }

  @Override
  public String couldPerform() {
    return "Perform an attack...";
  }
  
  @Override
  public String hasPerformed() {
    return "Select an attack to perform...";
  }
  
}
