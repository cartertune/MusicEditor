package cs3500.music.model;

import java.util.ArrayList;

/**
 * An Interface that MusicEditor models must apply to.
 */
public interface MusicOperations {


  /**
   * Adds a note to the piece at a given beat number.
   * @param pitch pitch of the note to be added.
   * @param octave octave of the note to be added.
   * @param duration duration of the note to be added.
   * @param beatNum beat number to add the note at.
   * @throws IllegalArgumentException if note is invalid.
   */
  void addNote(int duration, int octave, int beatNum, String pitch)
      throws IllegalArgumentException;

  /**
   * Adds a note to the piece at a given beat number.
   * @param pitch pitch of the note to be added.
   * @param octave octave of the note to be added.
   * @param duration duration of the note to be added.
   * @param beatNum beat number to add the note at.
   * @throws IllegalArgumentException if note is invalid.
   */
  void addNote(int duration, int octave, int beatNum, int instrument, int volume, String pitch)
      throws IllegalArgumentException;
  /**
   * Removes a note from the song.
   * @param beatNum beat number to remove note from.
   * @param pitch pitch of the note.
   * @param octave octave of the note.
   * @return the note that was deleted.
   * @throws IllegalArgumentException if note cannot be found.
   */
  INote removeNote(int beatNum, String pitch, int octave) throws IllegalArgumentException;

  /**
   * Edits the given note according to editor.
   * @param editor contains directions to edit note.
   * @param beatNum the beat number the note is at.
   * @param pitch the pitch of the note.
   * @param octave the octave of the note.
   * @throws IllegalArgumentException if note cannot be found.
   */
  void editNote(String editor, int beatNum, String pitch, int octave)
      throws IllegalArgumentException;

  /**
   * Merges the given piece with the one in the model.
   * @param piece piece to merge.
   */
  void mergePiece(Piece piece);

  /**
   * Adds the given piece at the end of the  current one.
   * @param piece piece to be added to current one.
   */
  void addPiece(Piece piece);


  /**
   * Gets the song in MIDI notation as a String.
   * @return the string.
   */
  String getMIDINotation();

  /**
   * Gets a copy of the Notes that start at given beat number.
   * @param beatNum number to get notes from.
   * @return the list of notes.
   */
  ArrayList<INote> getNotesAt(int beatNum);

  int minNoteValue();
  int maxNoteValue();

  int maxBeatNum();

  int getTempo();

  void setTempo(int tempo);
}
