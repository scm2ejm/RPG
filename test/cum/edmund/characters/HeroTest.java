package cum.edmund.characters;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cum.edmund.models.characters.FightableCharacter;
import cum.edmund.models.characters.enemies.ButtasaurusAss;
import cum.edmund.models.characters.helpers.CombatHelper;
import cum.edmund.models.characters.helpers.CombatHelper.CombatOutcome;
import cum.edmund.models.characters.hero.Hero;

public class HeroTest {

  private static final Logger LOGGER;

  static {
    LOGGER = LoggerFactory.getLogger(HeroTest.class);
  }

  @Test
  public void testDeath() {

    FightableCharacter hero = new Hero("fucker", 0, 0);
    FightableCharacter monster = new ButtasaurusAss(0, 1);

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
