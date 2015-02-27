package cum.edmund.characters;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cum.edmund.characters.helpers.CombatHelper;
import cum.edmund.characters.helpers.CombatHelper.CombatOutcome;

public class HeroTest {

  private static final Logger LOGGER;

  static {
    LOGGER = LoggerFactory.getLogger(HeroTest.class);
  }

  @Test
  public void testDeath() {

    FightableCharacter hero = new Hero("fucker", 9999);
    FightableCharacter monster = new ButtasaurusAss();

    while (true) {
      LOGGER.debug("{} attacks {}", hero.getName(), monster.getName());
      CombatOutcome heroOutcome = CombatHelper.physicalAttack(hero, monster);
      LOGGER.debug("outcome is {}", heroOutcome);

      if (!monster.isAlive()) {
        break;
      }

      LOGGER.debug("{} attacks {}", monster.getName(), hero.getName());
      CombatOutcome monsterOutcome = CombatHelper.physicalAttack(monster, hero);
      LOGGER.debug("outcome is {}", monsterOutcome);

      if (!hero.isAlive()) {
        break;
      }
    }
  }

}
