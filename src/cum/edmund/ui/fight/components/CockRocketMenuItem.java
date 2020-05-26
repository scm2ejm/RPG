package cum.edmund.ui.fight.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Behaviour relating to the attack menu
 * 
 * @author Ed
 *
 */
public class CockRocketMenuItem extends AssFuckMenu {

  private static final Logger LOGGER = LoggerFactory.getLogger(CockRocketMenuItem.class);

  public CockRocketMenuItem() {
    super("Mikey's Cock Rocket", () -> LOGGER.error("Mikey's Cock Rocket"));
    setDuration(2500);
    setIconFilename("cockrocket.gif");
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
