package cs3500.music.model;

/**
 * A class to represent the sound of a note. Basically a note without a duration.
 */
public class Sound {

  private int octave;
  private Pitch pitch;

  /**
   * Creates a Sound with given pitch and octave.
   * @param pitch pitch to set to sound.
   * @param octave octave to set to sound.
   */
  public Sound(Pitch pitch, int octave) {

    this.pitch = pitch;
    setOctave(octave);
  }


  /**
   * Takes a given value and creates the sound that corresponds to that value.
   * For example: 0 would be an A in first octave, and a 14 would be an A in the second octave.
   *
   * @param value the value to be converted to a Sound.
   */
  public Sound(int value) {
    if (value < 0) {
      throw new IllegalArgumentException("Note value must be positive");
    }
    int o = (value / 12) + 1;
    Pitch p = Pitch.values() [value % 12];
    this.pitch = p;
    setOctave(o);
  }


  void setPitch(Pitch pitch) {
    this.pitch = pitch;
  }

  void setOctave(int octave) {
    if (octave > 12) {
      throw new IllegalArgumentException("Max octave is 12");
    }
    if (octave < 0) {
      throw new IllegalArgumentException("Note value must be positive");
    }
    this.octave = octave;
  }

  /**
   * Gets the value of the note. The higher the frequency, the higher the value.
   * @return the value of the frequency.
   */
  int value() {

    return ((octave - 1) * 12)  + pitch.value();
  }

  @Override
  public String toString() {

    String o = String.valueOf(octave);
    if (o.length() == 1) {
      o += " ";
    }

    return pitch.toString() + o;
  }


  public Pitch getPitch() {
    return pitch;
  }

  public int getOctave() {
    return octave;
  }
}
