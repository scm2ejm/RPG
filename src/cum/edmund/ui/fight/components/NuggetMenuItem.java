package cum.edmund.ui.fight.components;

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
public class NuggetMenuItem extends AssFuckMenu {

  public NuggetMenuItem(FightView fightView) {
    super("Butt attack", () -> {
      FightableCharacter hero = fightView.currentCharacter();
      FightableCharacter enemy = fightView.currentEnemy();
      CombatHelper.physicalAttack(hero, enemy);
    }, fightView);
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
  
  @Override
  public void playSound() {
    AudioEngine.playSoundEffect("fart1.wav");
  }
}
