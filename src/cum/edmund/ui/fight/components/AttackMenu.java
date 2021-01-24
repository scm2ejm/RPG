package cum.edmund.ui.fight.components;

import cum.edmund.ui.fight.FightView;

/**
 * Behaviour relating to the attack menu
 * 
 * @author Ed
 *
 */
public class AttackMenu extends UnEndingAssFuckMenu {

  public AttackMenu(String name, Runnable task, FightView fightView) {
    super(name, task, fightView);
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
