package cum.edmund.models.characters.enemies;

import cum.edmund.models.WorldObjectType;
import cum.edmund.models.blocks.Barrier;
import cum.edmund.models.characters.Entourage;
import cum.edmund.models.map.Coord;


/**
 * This motherfucker represents the enemies which you have to fight in the random battles. Max of
 * six enemies at a time
 * 
 * @author Ed
 *
 */
public class Enemies extends Barrier {
  
  private Entourage entourage;
  
  public Enemies(Coord position) {
    super("Enemies", WorldObjectType.ENEMIES, position);
    entourage = new Entourage();
  }

  public Entourage getEntourage() {
    return entourage;
  }

  public void setEntourage(Entourage entourage) {
    this.entourage = entourage;
  }
}
