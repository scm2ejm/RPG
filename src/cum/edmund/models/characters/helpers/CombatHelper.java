package cum.edmund.models.characters.helpers;

import java.util.Random;

import cum.edmund.models.characters.CharacterAttributes;
import cum.edmund.models.characters.FightableCharacter;

public class CombatHelper {

  private static final Random random;
  // private static final Logger LOGGER;

  static {
    random = new Random();
    // LOGGER = LoggerFactory.getLogger(CombatHelper.class);
  }

  public enum CombatOutcome {

    DEFENDER_DEAD,

    ATTACKER_DOES_DAMAGE,

    ATTACKER_MISSES

  }

  public static CombatOutcome physicalAttack(FightableCharacter attacker,
      FightableCharacter defender) {
    CharacterAttributes attackerAttrs = attacker.getCharacterAttributes();
    CharacterAttributes defenderAttrs = defender.getCharacterAttributes();

    int attack = attackerAttrs.getPhysicalStrength();
    int defense = defenderAttrs.getPhysicalDefense();

    boolean strongAttack = random.nextBoolean();
    boolean strongDefence = random.nextBoolean();

    int attackOffset = (int) (attack * (random.nextFloat() * 0.3f));
    int defenseOffset = (int) (defense * (random.nextFloat() * 0.3f));

    if (strongAttack) {
      attack += attackOffset;
    } else {
      attack -= attackOffset;
    }

    if (strongDefence) {
      defense += defenseOffset;
    } else {
      defense -= defenseOffset;
    }

    int damage = attack - defense < 0 ? 0 : attack - defense;

    defender.takeDamage(damage);

    if (!defender.isAlive()) {
      return CombatOutcome.DEFENDER_DEAD;
    } else if (damage == 0) {
      return CombatOutcome.ATTACKER_MISSES;
    } else {
      return CombatOutcome.ATTACKER_DOES_DAMAGE;
    }
  }
}
