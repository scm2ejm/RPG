package cum.edmund.models.characters.hero;

import cum.edmund.models.WorldObjectType;
import cum.edmund.models.characters.CharacterAttributes;
import cum.edmund.models.characters.FightableCharacter;
import cum.edmund.models.characters.PlayableCharacter;

public class Hero extends FightableCharacter implements PlayableCharacter {

  private static final int DEFAULT_PHYSICAL_STRENGTH = 50;
  private static final int DEFAULT_MAGICAL_STRENGTH = 30;
  private static final int DEFAULT_PHYSICAL_DEFENSE = 25;
  private static final int DEFAULT_MAGICAL_DEFENCE = 10;
  private static final int DEFAULT_AGILITY = 25;
  private static final int DEFAULT_HP = 100;
  private static final int DEFAULT_MP = 0;

  public Hero(String name) {
    super(name, WorldObjectType.HERO, "walk.gif");
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
