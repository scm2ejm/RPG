package cum.edmund.models.characters.enemies;

import javax.swing.ImageIcon;
import cum.edmund.models.characters.CharacterAttributes;
import cum.edmund.models.maps.world.tiles.TileLoader;
import cum.edmund.models.maps.world.tiles.TileLoader.DrawType;

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
  private static final int DEFAULT_HP = 69;
  private static final int DEFAULT_MP = 0;

  private static final ImageIcon ATTACK_TILE =
      TileLoader.loadTile("cat-attack.gif", DrawType.STRETCH);

  private static final String ATTACK_SOUND = "cat-attack.wav";
  
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

  @Override
  public ImageIcon attackTile() {
    return ATTACK_TILE;
  }
  
  @Override
  public String attackSound() {
    return ATTACK_SOUND;
  }
}
