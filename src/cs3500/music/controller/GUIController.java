package cs3500.music.controller;

import cs3500.music.model.MusicOperations;
import cs3500.music.view.IGUIView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Class to represent a simple controller for the GUIView.
 */
public class GUIController {

  private final IGUIView view;
  private HashMap<Integer, Runnable> keyCMDs;

  /**
   * Creates a controller for the given view, adds the KeyCommands specified in addKeyCommands(),
   * and adds a Listener to the view.
   *
   * @param view the view to control.
   */
  public GUIController(IGUIView view, MusicOperations model) {

    this.view = view;
    addKeyCommands();
    addListener();
  }

  /**
   * ADDS Following Commands:
   * press left key to move the beat num left in the view.
   * press right key to move the beat num right in the view.
   */
  private void addKeyCommands() {
    this.keyCMDs = new HashMap<>();
    keyCMDs.put(39, () -> view.moveRight());
    keyCMDs.put(37, () -> view.moveLeft());
  }

  private void addListener() {

    view.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {

      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (keyCMDs.containsKey(e.getKeyCode())) {
          keyCMDs.get(e.getKeyCode()).run();
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {

      }
    });
  }
}
