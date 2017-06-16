package cs3500.music.model;

import java.util.ArrayList;

/**
 * Created by cartertune on 6/15/17.
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

  int getOctave();

  int getDuration();

  String getPitch();

  void setDuration(int duration);

  void setOctave(int octave);

  void setPitch(Pitch pitch);

  int getVolume();

  int getInstrument();
}
