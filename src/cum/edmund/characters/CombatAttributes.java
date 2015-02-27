package cum.edmund.characters;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CombatAttributes {

  public enum Attribute {
    PHYSICAL_STRENGTH,

    MAGICAL_STRENGTH,

    PHYSICAL_DEFENSE,

    MAGICAL_DEFENSE,

    AGILITY
  }

  private static final int DEFAULT_VALUE;

  private Map<Attribute, Integer> attributeMap;

  static {
    DEFAULT_VALUE = 1;
  }

  public CombatAttributes() {
    attributeMap = new ConcurrentHashMap<CombatAttributes.Attribute, Integer>();
  }

  public void put(Attribute attribute, int value) {
    attributeMap.put(attribute, value);
  }

  public int get(Attribute attribute) {
    Integer value = attributeMap.get(attribute);

    return value == null ? DEFAULT_VALUE : value;
  }
}
