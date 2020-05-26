package cum.edmund.ui.fight.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Behaviour relating to the attack menu
 * 
 * @author Ed
 *
 */
public class ExplosivoDiarrheaMenuItem extends AssFuckMenu {
  private static final Logger LOGGER = LoggerFactory.getLogger(ExplosivoDiarrheaMenuItem.class);
  public ExplosivoDiarrheaMenuItem() {
    super("Explosivo Diarrhea",  () -> LOGGER.error("Explosivo Diarrhea"));
    setDuration(5000);
    setIconFilename("poop.gif");
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
