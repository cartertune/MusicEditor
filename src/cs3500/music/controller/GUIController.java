package cs3500.music.controller;

import cs3500.music.model.MusicOperations;
import cs3500.music.view.IGUIView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Created by cartertune on 6/16/17.
 */
public class GUIController implements IController {

  private final IGUIView view;
  private final MusicOperations model;
  private HashMap<Integer, Runnable> keyCMDs;

  public HashMap<Integer, Runnable> getKeyCMDs() {
    return keyCMDs;
  }

  public GUIController(IGUIView view, MusicOperations model) {

    this.view = view;
    this.model = model;
    addKeyCommands();
    addListener();
  }

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

        keyCMDs.get(e.getKeyCode()).run();
      }

      @Override
      public void keyReleased(KeyEvent e) {

      }
    });
  }
}
