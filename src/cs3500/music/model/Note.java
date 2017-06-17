package cs3500.music.model;

import java.util.ArrayList;

/**
 * Class to represent a Note in the music editor.
 */
public class Note implements INote {


  private Sound sound;
  private int duration;//changed to int to add flexibility
  private int instrument;
  private int volume;

  /**
   * Creates a note with given sound and duration.
   *
   * @param sound sound to be set.
   * @param duration duration to be set.
   */
  public Note(Sound sound, int duration) {
    this.sound = sound;
    this.duration = duration;
    this.instrument = 0;
    this.volume = 64;
  }

  /**
   * Alternate constructor. Allows control over midi volume and instrument.
   * @param sound this note's pitch and octave
   * @param duration this note's duration in beats
   * @param instrument the Midi instrument of this note
   * @param volume this note's volume
   */
  public Note(Sound sound, int duration, int instrument, int volume) {

    this.sound = sound;
    this.duration = duration;
    this.instrument = instrument;
    this.volume = volume;
  }

  @Override
  public void setPitch(Pitch pitch) {
    this.sound.setPitch(pitch);
  }

  @Override
  public void setOctave(int octave) {
    this.sound.setOctave(octave);
  }

  @Override
  public void setDuration(int duration) {

    if (duration <= 0) {
      throw new IllegalArgumentException("Duration must be at least 1 beat.");
    }

    this.duration = duration;
  }

  public Sound getSound() {
    return sound;
  }


  @Override
  public int value() {
    return this.sound.value();
  }


  @Override
  public ArrayList<Beat> toBeat() {

    ArrayList<Beat> beat = new ArrayList<Beat>();
    beat.add(new Beat(sound, BeatType.NOTEHEAD));

    for (int i = 1; i < duration; i++) {
      beat.add(new Beat(sound, BeatType.NOTESUSTAINED));
    }
    return beat;
  }

  @Override
  public int getOctave() {
    return sound.getOctave();
  }

  @Override
  public int getDuration() {
    return duration;
  }

  @Override
  public String getPitch() {
    return sound.getPitch().toString();
  }

  @Override
  public int getVolume() {
    return volume;
  }

  @Override
  public int getInstrument() {
    return instrument;
  }
}
