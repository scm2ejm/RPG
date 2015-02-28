package cum.edmund.models.characters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cum.edmund.models.WorldObjectType;
import cum.edmund.models.map.Coord;

public abstract class FightableCharacter extends Character {

  private static final Logger LOGGER;

  private CharacterAttributes characterAttributes;

  static {
    LOGGER = LoggerFactory.getLogger(FightableCharacter.class);
  }

  public FightableCharacter(String name, WorldObjectType type, Coord position) {
    super(name, type, position);

    buildCharacterAttributes();
  }

  public CharacterAttributes getCharacterAttributes() {
    return characterAttributes;
  }

  public void setCharacterAttributes(CharacterAttributes characterAttributes) {
    this.characterAttributes = characterAttributes;
  }

  /**
   * This is used for setting initial values for this character
   */
  protected abstract void buildCharacterAttributes();

  public void takeDamage(int damage) {

    int oldHp = characterAttributes.getHp();

    int newHp = oldHp -= damage;

    characterAttributes.setHp(newHp);

    LOGGER.debug("{} took {} damage. Current health is {}", getName(), damage, newHp);

  }

  public boolean isAlive() {

    int hp = characterAttributes.getHp();

    boolean isAlive = hp >= 0;

    if (!isAlive) {
      LOGGER.debug("{} is dead", getName());
    }

    return isAlive;
  }

}
