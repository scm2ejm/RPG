package cum.edmund.ui.fight.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cum.edmund.ui.fight.FightView;

/**
 * Behaviour relating to the attack menu
 * 
 * @author Ed
 *
 */
public class NuggetMenuItem extends AssFuckMenu {
  private static final Logger LOGGER = LoggerFactory.getLogger(NuggetMenuItem.class);
  public NuggetMenuItem(FightView fightView) {
    super("Butt attack",  () -> LOGGER.error("Nugget"), fightView);
    setDuration(2800);
    setIconFilename("nugget.gif");
  }

  @Override
  public String couldPerform() {
    return "Expel shit nugget in direction of enemy";
  }

  @Override
  public String hasPerformed() {
    return "Now the enemy smells worse than the hero.";
  }
}
