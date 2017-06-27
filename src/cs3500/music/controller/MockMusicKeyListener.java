package cs3500.music.controller;

import cs3500.music.view.IGUIView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Mock for music key listener.
 */
public class MockMusicKeyListener implements KeyListener {

  private Map<Integer, Runnable> keyCMDs; //contains commands
  private IGUIView view;
  private StringBuilder log;

  MockMusicKeyListener(IGUIView view) {
    this.keyCMDs = new HashMap<>();
    this.log = new StringBuilder();
    addKeyCommands();
    view.addKeyListener(this);
  }

  /**
   * In Music Key Listener, this adds the commands associated with each input to the keyCMDs map.
   * This mock, however, replaces these commands with log commands.
   *
   * <p>Logs Following Commands:
   * press left key to move the beat num left in the view.
   * press right key to move the beat num right in the view.
   * </p>
   */
  private void addKeyCommands() {
    this.keyCMDs = new HashMap<>();


    keyCMDs.put(39, () -> log.append("  Scroll Right\n"));
    keyCMDs.put(37, () -> log.append("  Scroll Left\n"));
    keyCMDs.put(35, () -> log.append("  Jump to Beginning\n"));
    keyCMDs.put(36, () -> log.append("  Jump to End\n"));
    keyCMDs.put(32, () -> log.append("  Play / Pause\n"));
  }

  /**
   * Returns the Key Listener's log.
   *
   * @return the log.
   */
  @Override
  public String toString() {
    return log.toString();
  }

  /**
   * not used.
   */
  @Override
  public void keyTyped(KeyEvent e) {
    //do nothing.
  }

  /**
   * Invoked when a key has been pressed.
   * See the class description for {@link KeyEvent} for a definition of
   * a key pressed event. This mock
   */
  @Override
  public void keyPressed(KeyEvent e) {
    if (keyCMDs.containsKey(e.getKeyCode())) {
      keyCMDs.get(e.getKeyCode()).run();
    }
  }

  /**
   * Invoked when a key has been released.
   * See the class description for {@link KeyEvent} for a definition of
   * a key released event.
   */
  @Override
  public void keyReleased(KeyEvent e) {
    //do nothing.
  }
}
