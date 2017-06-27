package cs3500.music.view;

import cs3500.music.controller.MusicController;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * An interface that extends the ViewInterface so that the view can have methods the
 * MIDI View and console view does not need.
 */
public interface IGUIView extends EnhancedView {


  /**
   * Adds a note at current beat with a default volume, and instrument with a duration according
   * to how long the mouse was held.
   * @param me the mouse event to use to find the key pressed.
   * @param controller the controller to add the note.
   * @param holdTime the time that the mouse was held in microseconds.
   */
  void addNoteAt(MouseEvent me, MusicController controller, double holdTime);

  /**
   * Gets the currentBeat of the song.
   * @return the currentBeat.
   */
  int getCurrentBeat();

  /**
   * Adds the given keyListener to the view.
   * @param kl the keylistener to add.
   */
  void addKeyListener(KeyListener kl);

  /**
   * adds the given mouse listener to the piano panel.
   * @param ml the mouse listener to be added.
   */
  void addMouseListener(MouseListener ml);

  /**
   * Increases the tempo of the model represented by 10 ms per beat.
   */
  void increaseTempo();

  /**
   * Decreases the tempo of the model represented by 10 ms per beat.
   */
  void decreaseTempo();
}
