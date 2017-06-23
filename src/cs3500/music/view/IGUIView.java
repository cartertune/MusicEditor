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
   * Adds a quarter note at current beat with a default volume, and instrument at current beat.
   * @param me the mouse event to use to find the key pressed.
   */
  void addNoteAt(MouseEvent me, MusicController controller);

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
}
