package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

/**
 * Simple interface for music editor views that are more advanced than a console output.
 */
public interface EnhancedView extends ViewInterface {


  /**
   * If the music is not playing, it plays the music at current beat, else it pauses the song.
   */
  void playPause();

  /**
   * Scrolls the current beat to the left if possible.
   */
  void scrollLeft();

  /**
   * Scrolls the current beat to the right if possible.
   */
  void scrollRight();

  /**
   * Jumps ot the end of the song.
   */
  void jumpToEnd();

  /**
   * Jumps to the beginning of the song.
   */
  void jumpToBeginning();
}
