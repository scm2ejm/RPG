package cum.edmund.characters;

import cum.edmund.characters.CombatAttributes.Attribute;

public class Hero extends FightableCharacter implements PlayableCharacter {

  // private static final Logger LOGGER;

  // static {
  // LOGGER = LoggerFactory.getLogger(Hero.class);
  // }

  public Hero(String name, int startingHealth) {
    super(name, startingHealth);

    getCombatAttributes().put(Attribute.PHYSICAL_STRENGTH, 100);
    getCombatAttributes().put(Attribute.PHYSICAL_DEFENSE, 80);
  }

}
