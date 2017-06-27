package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;

/**
 * An Interface that MusicEditor models must apply to.
 */
public interface MusicOperations {


  /**
   * Adds a note to the piece at a given beat number with generic instrument and volume.
   *
   * <p>the String needed for the pitch is the letter of the note, followed by "SHARP" if
   * the note is a sharp. Everything must be in all caps.
   * (i.e a C would be given as "C", and a C# would be "CSHARP" </p>
   *
   * @param pitch pitch of the note to be added as a String
   * @param octave octave of the note to be added.
   * @param duration duration of the note to be added.
   * @param beatNum beat number to add the note at.
   * @throws IllegalArgumentException if note is invalid.
   */
  void addNote(int duration, int octave, int beatNum, String pitch)
      throws IllegalArgumentException;


  /**
   * Adds a given Repeat at given beat.
   * @param beatNum beatNumber to be placed at.
   * @param repeatType type of repeat to add.
   */
  void addRepeat(int beatNum, RepeatType repeatType);

  /**
   * Adds a note to model with given parameters.
   * @param duration duration of Note added.
   * @param octave octave of Note added.
   * @param beatNum beat number to place note at.
   * @param instrument instrument of the note.
   * @param volume the volume of the note added.
   * @param pitch the pitch of the note added as a String. (see prior method for details)
   * @throws IllegalArgumentException if it is an illegal note.
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
   * NEW METHOD to aid view play notes at given beat without parsing.
   * Gets a copy of the Notes that start at given beat number.
   * @param beatNum number to get notes from.
   * @return the list of notes.
   */
  ArrayList<INote> getNotesAt(int beatNum);

  /**
   * Gets the minimum note value.
   * @return returns the min.
   */
  int minNoteValue();

  /**
   * gets the max note value.
   * @return the value.
   */
  int maxNoteValue();

  /**
   * Gets the last beat of the song.
   * @return the last beat number.
   */
  int maxBeatNum();

  /**
   * Gets the Tempo of the song.
   * @return the tempo of the song.
   */
  int getTempo();

  /**
   * Sets the tempo of the song.
   * @param tempo tempo to be set.
   */
  void setTempo(int tempo);

  Repeat getRepeatAt(int beat);

  boolean hasRepeatAt(int beat);
}
