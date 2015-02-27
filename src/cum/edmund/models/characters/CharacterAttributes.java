package cum.edmund.models.characters;

/**
 * CharacterAttributes such as health and magic points
 * 
 * @author Ed
 *
 */
public class CharacterAttributes {

  private int physicalStrength;
  private int magicalStrength;
  private int physicalDefense;
  private int magicalDefense;
  private int agility;
  private int hp;
  private int mp;
  private int maxHp;
  private int maxMp;

  public int getPhysicalStrength() {
    return physicalStrength;
  }

  public void setPhysicalStrength(int physicalStrength) {
    this.physicalStrength = physicalStrength;
  }

  public int getMagicalStrength() {
    return magicalStrength;
  }

  public void setMagicalStrength(int magicalStrength) {
    this.magicalStrength = magicalStrength;
  }

  public int getPhysicalDefense() {
    return physicalDefense;
  }

  public void setPhysicalDefense(int physicalDefense) {
    this.physicalDefense = physicalDefense;
  }

  public int getMagicalDefense() {
    return magicalDefense;
  }

  public void setMagicalDefense(int magicalDefense) {
    this.magicalDefense = magicalDefense;
  }

  public int getAgility() {
    return agility;
  }

  public void setAgility(int agility) {
    this.agility = agility;
  }

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }

  public int getMp() {
    return mp;
  }

  public void setMp(int mp) {
    this.mp = mp;
  }

  public int getMaxHp() {
    return maxHp;
  }

  public void setMaxHp(int maxHp) {
    this.maxHp = maxHp;
  }

  public int getMaxMp() {
    return maxMp;
  }

  public void setMaxMp(int maxMp) {
    this.maxMp = maxMp;
  }
}
