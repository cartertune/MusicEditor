package cs3500.music.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BinaryOperator;

/**
 * Represents a song in the music editor.
 */
public class Piece {

  private Map<Integer, Notations> notes;

  /**
   * Creates an  empty song.
   */
  public Piece() {
    this.notes = new HashMap<>();
  }

  public Piece(Map<Integer, Notations> notes) {
    this.notes = notes;
  }

  /**
   * Adds a note to the piece at a given beat number.
   * @param note note to be added.
   * @param beatNum beat number to add the note at.
   */
  void addNote(Note note, int beatNum) throws IllegalArgumentException {
    if (beatNum < 0) {
      throw new IllegalArgumentException("Beat number cannot be less than zero");
    }

    if (notes.containsKey(beatNum)) {
      notes.get(beatNum).addNote(note);
    } else {
      ArrayList<INote> n = new ArrayList<>();
      n.add(note);
      Notations nots = new Notations(n);
      notes.put(beatNum, nots);
    }
  }

  /**
   * Removes a note from the song.
   * @param beatNum beat number to remove note from.
   * @param pitch pitch of the note.
   * @param octave octave of the note.
   * @return the note that was deleted.
   */
  INote removeNote(int beatNum, Pitch pitch, int octave) throws IllegalArgumentException {

    if (!notes.containsKey(beatNum)) {
      throw new IllegalArgumentException("No note found at that beat.");
    }

    Notations n = notes.get(beatNum);
    return n.removeNote(pitch, octave);
  }

  /**
   * Edits the given note according to editor.
   * @param editor contains directions to edit note.
   * @param beatNum the beat number the note is at.
   * @param pitch the pitch of the note.
   * @param octave the octave of the note.
   */
  void editNote(INoteEditor editor, int beatNum, Pitch pitch, int octave)
      throws IllegalArgumentException {

    INote n = notes.get(beatNum).getNote(pitch, octave);
    editor.edit(n);
  }

  /**
   * Merges the given piece with the one in the model.
   * @param piece piece to merge.
   */
  void mergePiece(Piece piece) {

    Map<Integer, Notations> mapToMerge = piece.getNotes();
    BinaryOperator<Notations> combiner = (notations, notations2) -> notations.combine(notations2);
    mapToMerge.forEach((beat, not) ->
        notes.merge(beat, not, combiner::apply));
  }

  /**
   * Adds the given piece at the end of the  current one.
   * @param piece piece to be added.
   */
  void addPiece(Piece piece) {
    for (Integer beat : piece.getNotes().keySet()) {

      Notations notations = piece.getNotes().get(beat);
      notes.put(beat + lastBeat(), notations);
    }

  }

  /**
   * Gets the song in MIDI notation as a String.
   * @return the string.
   */
  String getMIDINotation() {
    StringBuilder strBldr = new StringBuilder();

    String topLine = topLine();
    strBldr.append(topLine);

    String notePrint = printNotes();

    strBldr.append(notePrint);

    return strBldr.toString();
  }

  /**
   * Gets all the notes in String form.
   * @return the notes as a String.
   */
  private String printNotes() {
    HashMap<Integer, BeatNotations> beat = getBeat();

    StringBuilder sb = new StringBuilder();
    int minValue = getMinNoteValue();
    int maxValue = getMaxNoteValue();
    int maxBeat = lastBeat();
    for (int i = 0; i < maxBeat; i++) {
      sb.append('\n');
      String column = columnHeader(i, leftHandColumnWidth());

      sb.append(column);
      if (beat.containsKey(i)) {
        sb.append(beat.get(i).toString(minValue, maxValue));
      } else {
        for (int j = 0; j < (maxValue - minValue) + 1; j++) {
          sb.append("     ");
        }
      }
    }
    return sb.toString();
  }

  /**
   * Creates the column header string.
   * @param i the column number.
   * @param width the width of the column.
   * @return the column header.
   */
  private String columnHeader(int i, int width) {

    String columnHeader = String.valueOf(i);

    while (columnHeader.length() < width) {
      columnHeader = " " + columnHeader;
    }
    return columnHeader;
  }

  /**
   * Creates a string for the top line from the lowest note in the song to the highest.
   * @return the string
   */
  private String topLine() {

    StringBuilder strBldr = new StringBuilder();
    int width = leftHandColumnWidth();

    for (int i = 0; i < width; i++) {
      strBldr.append(' ');
    }
    int min = getMinNoteValue();
    int max = getMaxNoteValue();

    for (int i = min; i <= max; i++) {

      Sound s = new Sound(i);
      strBldr.append(s.toString());
    }
    return strBldr.toString();
  }

  public Map<Integer, Notations> getNotes() {
    return notes;
  }

  /**
   * Gets the last beat of the current piece.
   * @return the last beat of the current piece plus a measure (4 beats)
   */
  private int lastBeat() {

    ArrayList<Integer> beatNums = new ArrayList<>(notes.keySet());
    try {
      return Collections.max(beatNums) + 4;
    } catch (NoSuchElementException e) {

      return 0;
    }
  }

  private int getMinNoteValue() {

    int min = 9999999;

    for (Notations n : notes.values()) {

      if (n.minValue() < min) {
        min = n.minValue();
      }
    }

    if (min < 9999999) {
      return min;
    } else {
      return 0;
    }
  }

  private int getMaxNoteValue() {

    int max = 0;

    for (Notations n : notes.values()) {

      if (n.maxValue() > max) {
        max = n.maxValue();
      }
    }

    return max;
  }

  private int leftHandColumnWidth() {

    int maxBeat = lastBeat();

    return String.valueOf(maxBeat).length();
  }

  /**
   * Takes the notes in the song and turns them all in to notes at each beat.
   * @return a map of the notes at each beat number.
   */
  private HashMap<Integer, BeatNotations> getBeat() {

    HashMap<Integer, BeatNotations> beat = new HashMap<>();

    for (int i: notes.keySet()) {
      Notations n = notes.get(i);
      for (INote note: n.notes) {
        addNoteToBeat(i, note, beat);
      }
    }
    return beat;
  }

  /**
   * Adds a note to the beat map.
   * @param startBeat the beat the note starts at.
   * @param note the note being added.
   * @param beat the current map of beats.
   */
  private void addNoteToBeat(int startBeat, INote note, HashMap<Integer, BeatNotations> beat) {

    ArrayList<Beat> noteToBeat = note.toBeat();

    for (int i = 0; i < noteToBeat.size(); i++) {
      if (beat.containsKey(i + startBeat)) {
        BeatNotations beatNotations = beat.get(startBeat + i);
        beatNotations.addBeat(noteToBeat.get(i));
      } else {
        ArrayList<Beat> b = new ArrayList<>();
        b.add(noteToBeat.get(i));
        BeatNotations notations = new BeatNotations(b);
        beat.put(startBeat + i, notations);
      }

    }
  }

  ArrayList<INote> getNotesAt(int beatNum) {

    if (notes.containsKey(beatNum)) {
      return notes.get(beatNum).getNotes();
    }
    return new ArrayList<>();
  }
}
