package cs3500.music.controller;

import cs3500.music.view.IGUIView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * A KeyListener that manipulates an IGUIView.
 * Look at JavaDoc for addKeyCommands to look at actual commands.
 */
public class MusicKeyListener implements KeyListener {



  private Map<Integer, Runnable> keyCMDs;
  private IGUIView view;

  /**
   * Creates this MusicKeyListener and adds it to given view.
   * @param view view to add listener to.
   */
  MusicKeyListener(IGUIView view) {

    this.view = view;
    this.keyCMDs = new HashMap<>();
    addKeyCommands();
    view.addKeyListener(this);
  }

  /**
   * ADDS Following Commands in this order:
   * press left key to move the beat num left in the view.
   * press right key to move the beat num right in the view.
   * press home key to jump to the beginning of the song.
   * press the end key to jump to the end of the song.
   * press the SPACE bar to play or pause the song.
   *
   */
  private void addKeyCommands() {
    this.keyCMDs = new HashMap<>();
    keyCMDs.put(39, () -> view.scrollRight());
    keyCMDs.put(37, () -> view.scrollLeft());
    keyCMDs.put(35, () -> view.jumpToBeginning());
    keyCMDs.put(36, () -> view.jumpToEnd());
    keyCMDs.put(32, () -> view.playPause());
  }

  /**
   * Invoked when a key has been typed.
   * See the class description for {@link KeyEvent} for a definition of
   * a key typed event.
   */
  @Override
  public void keyTyped(KeyEvent e) {
    //do nothing.
  }

  /**
   * Invoked when a key has been pressed.
   * See the class description for {@link KeyEvent} for a definition of
   * a key pressed event.
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
