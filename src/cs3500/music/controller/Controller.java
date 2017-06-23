package cs3500.music.controller;

import cs3500.music.model.MusicOperations;
import cs3500.music.view.IGUIView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Class to represent a simple controller for the GUIView.
 */
public class Controller implements MusicController {

  private final IGUIView view;
  private final MusicKeyListener keyListener;
  private final MusicOperations model;
  private final MusicMouseListener mouseListener;

  /**
   * Creates a controller for the given view, adds the KeyCommands specified in addKeyCommands(),
   * and adds a Listener to the view.
   *
   * @param view the view to control.
   */
  public Controller(IGUIView view, MusicOperations model) {

    this.view = view;
    this.model = model;

    this.keyListener = new MusicKeyListener(view);
    this.mouseListener = new MusicMouseListener(view, this);
  }

  @Override
  public void addNote(int octave, int beatNum, String pitch) {

    model.addNote(1, octave,beatNum, pitch);
  }
}
