package cs3500.music.model;

import java.util.ArrayList;

/**
 * ADDED INTERFACE to allow view to access information about Note easily.
 * An interface to illustrate functionality of a note.
 */
public interface INote {


  /**
   * Gets the value of the sound.
   *
   * @return the value of the sound.
   */
  int value();

  /**
   * Creates an array of Beats where the first is a NoteHead and the rest are  NoteTails.
   *
   * @return the array.
   */
  ArrayList<Beat> toBeat();

  /**
   * Gets the octave of the note.
   * @return the octave.
   */
  int getOctave();

  /**
   * Gets the duration of the Note.
   * @return the duration.
   */
  int getDuration();


  /**
   * Gets the pitch of the note as a String.
   * The letter with a "#" beside it if it is a sharp. (i.e. "C" and "C#".
   *
   * @return the pitch as a String.
   */
  String getPitch();

  /**
   * Sets the duration of the Note.
   * @param duration sets the Duration of the note.
   */
  void setDuration(int duration);


  /**
   * Sets the octave of the Note.
   * @param octave octave to be set to.
   */
  void setOctave(int octave);

  /**
   * Sets the pitch of the Note to the given one.
   * @param pitch pitch to be set to.
   */
  void setPitch(Pitch pitch);

  /**
   * Gets the volume of the given Note.
   * @return the integer representing the volume.
   */
  int getVolume();


  /**
   * Gets the Integer associated with the instrument.
   * @return the instrument.
   */
  int getInstrument();
}
