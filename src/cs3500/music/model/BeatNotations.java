package cs3500.music.model;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents the various notes being played at a given beat.
 */
class BeatNotations {

  private ArrayList<Beat> beats;

  /**
   * Creates a Beat notation with given beat.
   * @param beats beat to set to notation.
   */
  BeatNotations(ArrayList<Beat> beats) {
    this.beats = beats;
  }

  /**
   * Adds the given beat to the list of Beat.
   * @param beat beat to be added.
   */
  void addBeat(Beat beat) {
    beats.add(beat);
  }


  /**
   * creates a string of the notes played at a given beat.
   * @param minNote the min value of the entire row.
   * @return the string created.
   */
  String toString(int minNote, int maxNote) {
    StringBuilder sb = new StringBuilder();

    beats.sort(Comparator.comparingInt(Beat::value));
    int lastValue = minNote;
    for (Beat beat: beats) {
      sb.append(toStringHelper(lastValue, beat));
      lastValue = beat.value() + 1;
    }
    for (int i = 0; i < (maxNote - lastValue) + 1; i++) {
      sb.append("     ");
    }
    return sb.toString();
  }

  private String toStringHelper(int lastValue, Beat beat) {

    StringBuilder str = new StringBuilder(beat.toString());
    for (int i = 0; i < (beat.value() - lastValue); i++) {
      str.insert(0, "     ");
    }
    return str.toString();
  }
}
