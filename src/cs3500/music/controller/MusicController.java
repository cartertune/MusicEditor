package cs3500.music.controller;

/**
 * An Interface to represent controllers for Music Models.
 */
public interface MusicController {


  /**
   * Adds a note to the Controller's model with specified values.
   * @param duration the duration of  the  note to add.
   * @param octave the octave of the note to be added.
   * @param beatNum the beat number to add the note at.
   * @param pitch the pitch as a String of the Note to be added.
   */
  void addNote(int duration, int octave, int beatNum, String pitch);
}
