package cum.edmund.ui.fight.components;

/**
 * Behaviour relating to the attack menu
 * 
 * @author Ed
 *
 */
public class ExplosivoDiarrheaMenuItem extends AssFuckMenu{

  public ExplosivoDiarrheaMenuItem(String name, Runnable task) {
    super(name, task);
  }

  @Override
  public String couldPerform() {
    return "Point anus at enemy and relax bowels";
  }
  
  @Override
  public String hasPerformed() {
    return "Hero shit himself. Nothing else happened.";
  }
}
