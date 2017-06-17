package cs3500.music.view;

import java.awt.event.KeyListener;

/**
 * An interface that extends the ViewInterface so that the view can have methods the
 * MIDIview does not need.
 */
public interface IGUIView extends ViewInterface {


  /**
   * Moves the current beat to the right... moves forward in the song.
   */
  void moveRight();

  /**
   * moves the current beat of the song to the left, rewinds it.
   */
  void moveLeft();

  /**
   * Adds the given keyListener to the view.
   * @param kl the keylistener to add.
   */
  void addKeyListener(KeyListener kl);
}
