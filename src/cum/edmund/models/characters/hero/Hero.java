package cum.edmund.models.characters.hero;

import cum.edmund.models.WorldObjectType;
import cum.edmund.models.characters.CharacterAttributes;
import cum.edmund.models.characters.FightableCharacter;
import cum.edmund.models.characters.PlayableCharacter;
import cum.edmund.models.map.Coord;

public class Hero extends FightableCharacter implements PlayableCharacter {

  private int DEFAULT_PHYSICAL_STRENGTH = 50;
  private int DEFAULT_MAGICAL_STRENGTH = 30;
  private int DEFAULT_PHYSICAL_DEFENSE = 25;
  private int DEFAULT_MAGICAL_DEFENCE = 10;
  private int DEFAULT_AGILITY = 25;
  private int DEFAULT_HP = 100;
  private int DEFAULT_MP = 0;

  public Hero(String name, int x, int y) {
    super(name, WorldObjectType.HERO, new Coord(x, y));

    CharacterAttributes attributes = buildDefaultAttributes();
    setCharacterAttributes(attributes);
  }

  private CharacterAttributes buildDefaultAttributes() {
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

    return characterAttributes;
  }
}
