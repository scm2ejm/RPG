package cum.edmund.characters;

import cum.edmund.characters.CombatAttributes.Attribute;

public class ButtasaurusAss extends FightableCharacter {

  public ButtasaurusAss() {
    super("Buttasaurus Ass", 9999);
    
    getCombatAttributes().put(Attribute.PHYSICAL_STRENGTH, 100);
    getCombatAttributes().put(Attribute.PHYSICAL_DEFENSE, 80);
  }

}
