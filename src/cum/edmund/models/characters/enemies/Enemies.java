package cum.edmund.models.characters.enemies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import cum.edmund.models.WorldObjectType;
import cum.edmund.models.blocks.Barrier;


/**
 * This motherfucker represents the enemies which you have to fight in the random battles. Max of
 * six enemies at a time
 * 
 * @author Ed
 *
 */
public class Enemies extends Barrier {
  public Enemies() {
    super("Enemies", WorldObjectType.ENEMIES, "cat.gif");
  }

  private List<FightableNPC> frontRow = Collections.synchronizedList(new ArrayList<>());

  private List<FightableNPC> backRow = Collections.synchronizedList(new ArrayList<>());

  public void add(FightableNPC enemy) {
    if (frontRow.size() < 3) {
      frontRow.add(enemy);
    } else if (backRow.size() < 3) {
      backRow.add(enemy);
    } else {
      throw new RuntimeException("Already six enemies in this object");
    }
  }

  public int totalCount() {
    return frontRow.size() + backRow.size();
  }

  public int aliveCount() {
    int alive = 0;

    alive += frontRow.parallelStream().filter(enemy -> enemy.isAlive()).count();

    alive += backRow.parallelStream().filter(enemy -> enemy.isAlive()).count();

    return alive;
  }

  public List<FightableNPC> getFrontRow() {
    return frontRow;
  }

  public List<FightableNPC> getBackRow() {
    return backRow;
  }

  public List<FightableNPC> allEnemies() {
    List<FightableNPC> allEnemies = Collections.synchronizedList(new ArrayList<>());
    allEnemies.addAll(frontRow);
    allEnemies.addAll(backRow);
    return allEnemies;
  }
}
