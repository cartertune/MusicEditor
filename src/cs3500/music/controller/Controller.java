package cs3500.music.controller;

import cs3500.music.model.MusicOperations;
import cs3500.music.view.IGUIView;


/**
 * Class used to control the relationship between an IGUIView and a MusicOperations.
 * Check MusicKeyListener and MusicMouseListener for command details.
 */
public class Controller implements MusicController {


  private final MusicOperations model;


  /**
   * Creates a controller for the given view, adds the KeyCommands specified in addKeyCommands(),
   * and adds a Listener to the view.
   *
   * @param view the view to control.
   */
  public Controller(IGUIView view, MusicOperations model) {

    this.model = model;

    new MusicKeyListener(view);
    new MusicMouseListener(view, this);
  }

  @Override
  public void addNote(int octave, int beatNum, String pitch) {

    model.addNote(1, octave,beatNum, pitch);
  }
}
