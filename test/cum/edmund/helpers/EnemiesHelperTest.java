package cum.edmund.helpers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cum.edmund.models.characters.enemies.Enemies;
import cum.edmund.models.characters.enemies.FightableNPC;
import cum.edmund.models.map.Coord;

public class EnemiesHelperTest {

  @Test(expected = RuntimeException.class)
  public void testCreateTooMany() {
    // This should throw an exception
    EnemiesHelper.createButtasaurusAss(10, new Coord(0, 0));
  }

  @Test
  public void testSixTotalSixAlive() {
    Enemies enemies = EnemiesHelper.createButtasaurusAss(6, new Coord(0, 0));
    assertEquals(6, enemies.aliveCount());
    assertEquals(6, enemies.totalCount());
  }

  @Test
  public void testThreeTotalZeroAlive() {
    Enemies enemies = EnemiesHelper.createButtasaurusAss(3, new Coord(0, 0));

    int dead = 0;
    for (FightableNPC enemy : enemies.allEnemies()) {
      if (dead++ == 3) {
        break;
      }
      enemy.kill();
    }

    assertEquals(0, enemies.aliveCount());
    assertEquals(3, enemies.totalCount());
  }
}
