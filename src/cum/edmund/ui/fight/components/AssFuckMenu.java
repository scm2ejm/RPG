package cum.edmund.ui.fight.components;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import cum.edmund.ui.fight.FightView;

/**
 * Represents a menu. Used by {@link ActionsPanel}
 * 
 * @author Ed
 *
 */
public class AssFuckMenu {
  private final String name;
  private final Runnable task;
  protected final FightView fightView;
  private String couldPerform;
  private String isPerforming;
  private String hasPerformed;
  private String iconFilename;
  private int duration;
  private boolean turnOver;

  public AssFuckMenu(String name, Runnable task, FightView fightView) {
    this.name = name;
    this.task = task;
    this.fightView = fightView;
    
    // Assume the turn ends after the first action
    this.turnOver = true;

    // 2.5 seconds by default
    this.duration = 2500;
  }

  public String couldPerform() {
    return Optional.ofNullable(couldPerform).orElse("Perform " + name + "...");
  }

  public String isPerforming() {
    return Optional.ofNullable(isPerforming).orElse("Hero is performing " + name + "...");
  }

  public String hasPerformed() {
    return Optional.ofNullable(hasPerformed).orElse("Select an action");
  }

  public void select() {
    
    // Play sound after 1 second
    Timer soundTimer = new Timer();
    soundTimer.schedule(new TimerTask() {
      @Override
      public void run() {
        playSound();
      }
    }, 1000L);
    
    task.run();

    if (turnOver) {
      fightView.scheduleNextTurn();
    }
  }

  public String getIconFilename() {
    return iconFilename;
  }

  public void setIconFilename(String iconFilename) {
    this.iconFilename = iconFilename;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public String getName() {
    return name;
  }

  public boolean isTurnOver() {
    return turnOver;
  }

  public void setTurnOver(boolean turnOver) {
    this.turnOver = turnOver;
  }

  public void playSound() {
    // NO-OP
  }
}
