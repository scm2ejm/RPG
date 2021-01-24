package cum.edmund.models.characters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cum.edmund.models.WorldObjectType;

/**
 * Represents a playable or non-playable character who take part in a fight
 * 
 * @author Ed
 *
 */
public abstract class FightableCharacter extends Character {

  private static final Logger LOGGER = LoggerFactory.getLogger(FightableCharacter.class);

  private CharacterAttributes characterAttributes;

  public FightableCharacter(String name, WorldObjectType type, String imageFilename) {
    super(name, type, imageFilename);

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

    // Lowest health is zero
    int newHp = Math.max(oldHp - damage, 0);

    characterAttributes.setHp(newHp);

    LOGGER.debug("{} took {} damage. Current health is {}", getName(), damage, newHp);

  }

  public boolean isAlive() {

    int hp = characterAttributes.getHp();

    boolean isAlive = hp > 0;

    if (!isAlive) {
      LOGGER.debug("{} is dead", getName());
    }

    return isAlive;
  }

  public void kill() {
    int oldHp = characterAttributes.getHp();

    takeDamage(oldHp);
  }
}
