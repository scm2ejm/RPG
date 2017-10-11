package cum.edmund.models.characters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This motherfucker represents the peeps who fight in the random battles. Max of six enemies or
 * heroes at a time
 * 
 * @author Ed
 *
 */
public class Entourage {

  private List<FightableCharacter> frontRow = Collections.synchronizedList(new ArrayList<>());

  private List<FightableCharacter> backRow = Collections.synchronizedList(new ArrayList<>());

  public void add(FightableCharacter peep) {
    if (frontRow.size() < 3) {
      frontRow.add(peep);
    } else if (backRow.size() < 3) {
      backRow.add(peep);
    } else {
      throw new RuntimeException("Already six people in this object");
    }
  }

  public int totalCount() {
    return frontRow.size() + backRow.size();
  }

  public int aliveCount() {
    int alive = 0;

    alive += frontRow.parallelStream().filter(peep -> peep.isAlive()).count();

    alive += backRow.parallelStream().filter(peep -> peep.isAlive()).count();

    return alive;
  }

  public List<FightableCharacter> getFrontRow() {
    return frontRow;
  }

  public List<FightableCharacter> getBackRow() {
    return backRow;
  }

  public List<FightableCharacter> allCharacters() {
    List<FightableCharacter> allPeeps = Collections.synchronizedList(new ArrayList<>());
    allPeeps.addAll(frontRow);
    allPeeps.addAll(backRow);
    return allPeeps;
  }
}
