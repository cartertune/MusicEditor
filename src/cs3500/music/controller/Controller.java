package cs3500.music.controller;

import cs3500.music.model.MusicOperations;
import cs3500.music.view.IGUIView;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Class to represent a simple controller for the GUIView.
 */
public class Controller implements MusicController {

  private final IGUIView view;
  private final KeyListener keyListener;
  private final MusicOperations model;
  private final MouseListener mouseListener;

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


  /**
   * Convenience constructor. Used for mocking.
   * @param keyListener
   */
  public Controller (IGUIView view, MusicOperations model, KeyListener keyListener) {
    this.view = view;
    this.model = model;
    this.keyListener = keyListener;
    this.mouseListener = new MockMusicMouseListener(view, this);
  }


  @Override
  public void addNote(int octave, int beatNum, String pitch) {
    model.addNote(1, octave,beatNum, pitch);
  }

  @Override
  public String toString() {
    StringBuilder out = new StringBuilder();
    out.append("Key Listener Log: \n" + this.keyListener.toString());
    out.append("Mouse Listener Log: \n" + this.mouseListener.toString());

    return out.toString();
  }
}
