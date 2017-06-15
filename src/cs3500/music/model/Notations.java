package cs3500.music.model;

import java.util.ArrayList;


/**
 * A class that represents all the notes that start at the same beat.
 */
public class Notations {

  ArrayList<INote> notes;

  /**
   * Creates a Notations with given notes.
   *
   * @param notes notes to be set.
   */
  public Notations(ArrayList<INote> notes) {
    this.notes = notes;
  }

  void addNote(INote note) {
    notes.add(note);
  }

  INote removeNote(Pitch pitch, int octave) {

    int expValue = ((octave - 1) * 12)  + pitch.value();

    for (int i = 0; i < notes.size(); i++) {
      INote note = notes.get(i);
      if (note.value() == expValue) {

        return notes.remove(i);
      }
    }
    throw new IllegalArgumentException("Note note found.");
  }

  INote getNote(Pitch pitch, int octave) {

    int expValue = ((octave - 1) * 12)  + pitch.value();
    for (INote note : notes) {
      if (note.value() == expValue) {
        return note;
      }
    }
    throw new IllegalArgumentException("Note not found.");
  }

  ArrayList<INote> getNotes() {

    ArrayList<INote> n = notes;
    return n;
  }

  int minValue() {
    int min = notes.get(0).value();
    for (INote note : notes) {
      if (note.value() < min) {
        min = note.value();
      }
    }
    return min;
  }

  int maxValue() {
    int max = 0;
    for (INote note : notes) {
      if (note.value() > max) {
        max = note.value();
      }
    }
    return max;
  }

  /**
   * Adds the given notations to the this one.
   *
   * @param notations to add.
   * @return this
   */
  Notations combine(Notations notations) {
    ArrayList<INote> notesToAdd = notations.getNotes();

    for (INote aNotesToAdd : notesToAdd) {
      this.addNote(aNotesToAdd);
    }
    return this;
  }
}
