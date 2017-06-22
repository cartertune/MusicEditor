package cs3500.music.controller;

/**
 * Created by cartertune on 6/22/17.
 */
public interface MusicController {


  /**
   * Adds a note to the Controller's model with specified values.
   * @param octave the octave of the note to be added.
   * @param beatNum the beat number to add the note at.
   * @param pitch the pitch as a String of the Note to be added.
   */
  void addNote(int octave, int beatNum, String pitch);
}
