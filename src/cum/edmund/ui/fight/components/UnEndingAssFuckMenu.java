package cum.edmund.ui.fight.components;

import cum.edmund.ui.fight.FightView;

/**
 * Represents a menu. Same as {@link AssFuckMenu} but turn doesn't end afterwards
 * 
 * @author Ed
 *
 */
public class UnEndingAssFuckMenu extends AssFuckMenu {
  public UnEndingAssFuckMenu(String name, Runnable task, FightView fightView) {
    super(name, task, fightView);
    this.setTurnOver(false);
  }


}
