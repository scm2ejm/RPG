package cum.edmund.ui.fight.components;

/**
 * Behaviour relating to the attack menu
 * 
 * @author Ed
 *
 */
public class CockRocketMenuItem extends AssFuckMenu{

  public CockRocketMenuItem(String name, Runnable task) {
    super(name, task);
  }

  @Override
  public String couldPerform() {
    return "Shoot a rocket powered penis at enemy";
  }
  
  @Override
  public String hasPerformed() {
    return "A rocket powered penis was fired.";
  }
}
