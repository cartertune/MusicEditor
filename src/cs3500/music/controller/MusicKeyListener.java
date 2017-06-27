package cs3500.music.controller;

import com.sun.org.apache.regexp.internal.RE;
import cs3500.music.model.MusicOperations;
import cs3500.music.model.RepeatType;
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
  private final MusicOperations model;

  /**
   * Creates this MusicKeyListener and adds it to given view.
   * @param view view to add listener to.
   */
  MusicKeyListener(IGUIView view, MusicOperations model) {

    this.view = view;
    this.keyCMDs = new HashMap<>();
    this.model = model;
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
   * press the down key to decrease the tempo of the song.
   * press the up key to increase the tempo of the song.
   */
  private void addKeyCommands() {
    this.keyCMDs = new HashMap<>();
    keyCMDs.put(39, () -> view.scrollRight());
    keyCMDs.put(37, () -> view.scrollLeft());
    keyCMDs.put(36, () -> view.jumpToBeginning());
    keyCMDs.put(35, () -> view.jumpToEnd());
    keyCMDs.put(32, () -> view.playPause());
    keyCMDs.put(40, () -> view.decreaseTempo());
    keyCMDs.put(38, () -> view.increaseTempo());
    keyCMDs.put(66, () -> model.addRepeat(view.getCurrentBeat(), RepeatType.StartRepeat));
    keyCMDs.put(82, () -> model.addRepeat(view.getCurrentBeat(), RepeatType.EndRepeat));
    keyCMDs.put(69, () -> model.addRepeat(view.getCurrentBeat(), RepeatType.EndingVariation));
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
