package cum.edmund.characters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class FightableCharacter extends Character {

  private static final Logger LOGGER;

  private int maxHealth;
  private int health;
  private final CombatAttributes combatAttributes;

  static {
    LOGGER = LoggerFactory.getLogger(FightableCharacter.class);
  }

  public FightableCharacter(String name, int startingHealth) {

    super(name);
    this.health = startingHealth;
    this.maxHealth = startingHealth;
    this.combatAttributes = new CombatAttributes();

  }

  public int getHealth() {
    return health;
  }

  public int getMaxHealth() {
    return maxHealth;
  }

  public CombatAttributes getCombatAttributes() {
    return combatAttributes;
  }

  public void takeDamage(int damage) {

    health -= damage;
    
    LOGGER.debug("{} took {} damage. Current health is {}", getName(), damage, health);

  }

  public boolean isAlive() {

    boolean isAlive = health >= 0;

    if (!isAlive) {
      LOGGER.debug("{} has died", getName());
    }

    return isAlive;
  }

}
