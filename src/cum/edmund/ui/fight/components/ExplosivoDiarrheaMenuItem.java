package cum.edmund.ui.fight.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cum.edmund.audio.AudioEngine;
import cum.edmund.helpers.CombatHelper;
import cum.edmund.models.characters.FightableCharacter;
import cum.edmund.ui.fight.FightView;

/**
 * Behaviour relating to the attack menu
 * 
 * @author Ed
 *
 */
public class ExplosivoDiarrheaMenuItem extends AssFuckMenu {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExplosivoDiarrheaMenuItem.class);

  public ExplosivoDiarrheaMenuItem(FightView fightView) {
    super("Explosivo Diarrhea", () -> {
      FightableCharacter hero = fightView.currentCharacter();
      FightableCharacter enemy = fightView.currentEnemy();
      CombatHelper.physicalAttack(hero, enemy);
    }, fightView);
    setDuration(5000);
    setIconFilename("poop.gif");
  }

  @Override
  public String couldPerform() {
    return "Point anus at enemy and relax bowels";
  }

  @Override
  public void playSound() {
    try {
      AudioEngine.playSoundEffect("fart2.wav");
      Thread.sleep(500L);
      AudioEngine.playSoundEffect("fart1.wav");
      Thread.sleep(500L);
      AudioEngine.playSoundEffect("fart2.wav");
    } catch (Exception e) {
      LOGGER.error("Unable to play sound effects", e);
    }
  }

  @Override
  public String hasPerformed() {
    return "Hero shit himself.";
  }
}
