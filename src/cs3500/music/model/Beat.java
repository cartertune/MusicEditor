package cs3500.music.model;

/**
 * Represents a note at a single beat.
 * */
public class Beat {

  Sound note;
  private BeatType type;

  /**
   * creates a beat with given note and type.
   * @param note note to set.
   * @param type type to set.
   */
  Beat(Sound note, BeatType type) {
    this.note = note;
    this.type = type;
  }

  @Override
  public String toString() {
    return type.toString();
  }

  /**
   * Finds the value of the note. The higher the frequency, the higher the value.
   * @return the value of the sound.
   */
  int value() {

    return note.value();
  }
}
