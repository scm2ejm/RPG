package cum.edmund.models.characters.enemies;

import cum.edmund.models.characters.CharacterAttributes;

/**
 * First encountered enemy
 * 
 * @author Ed
 *
 */
public class ButtasaurusAss extends FightableNPC {

  private static final int DEFAULT_PHYSICAL_STRENGTH = 50;
  private static final int DEFAULT_MAGICAL_STRENGTH = 30;
  private static final int DEFAULT_PHYSICAL_DEFENSE = 10;
  private static final int DEFAULT_MAGICAL_DEFENCE = 10;
  private static final int DEFAULT_AGILITY = 25;
  private static final int DEFAULT_HP = 50;
  private static final int DEFAULT_MP = 0;

  public ButtasaurusAss() {
    super("Buttasaurus Ass", "cat.gif");
  }

  @Override
  protected void buildCharacterAttributes() {
    CharacterAttributes characterAttributes = new CharacterAttributes();

    characterAttributes.setPhysicalStrength(DEFAULT_PHYSICAL_STRENGTH);
    characterAttributes.setMagicalStrength(DEFAULT_MAGICAL_STRENGTH);
    characterAttributes.setPhysicalDefense(DEFAULT_PHYSICAL_DEFENSE);
    characterAttributes.setMagicalDefense(DEFAULT_MAGICAL_DEFENCE);
    characterAttributes.setAgility(DEFAULT_AGILITY);
    characterAttributes.setHp(DEFAULT_HP);
    characterAttributes.setMaxMp(DEFAULT_MP);
    characterAttributes.setMaxHp(DEFAULT_HP);
    characterAttributes.setMaxMp(DEFAULT_MP);

    setCharacterAttributes(characterAttributes);
  }
}
