package cs3500.music.model;


import cs3500.music.util.CompositionBuilder;
import java.util.ArrayList;

/**
 * A class to represent the model of the MusicEditor.
 */
public class MusicModel implements MusicOperations {


  private int tempo;
  private Piece piece;

  private MusicModel(compBuilder compBuilder) {
    this.tempo = compBuilder.tempo;
    this.piece = compBuilder.piece;
  }

  public MusicModel() {
    this.piece = new Piece();
    this.tempo = 90;
  }

  public static class compBuilder implements CompositionBuilder<MusicModel> {


    private int tempo;
    private Piece piece = new Piece();


    public compBuilder() {

    }
    /**
     * Constructs an actual composition, given the notes that have been added
     *
     * @return The new composition
     */
    @Override
    public MusicModel build() {
      return new MusicModel(this);
    }

    /**
     * Sets the tempo of the piece
     *
     * @param tempo The speed, in microseconds per beat
     * @return This builder
     */
    @Override
    public CompositionBuilder<MusicModel> setTempo(int tempo) {

      this.tempo = tempo;
      return this;
    }

    /**
     * Adds a new note to the piece
     *
     * @param start The start time of the note, in beats
     * @param end The end time of the note, in beats
     * @param instrument The instrument number (to be interpreted by MIDI)
     * @param pitch The pitch (in the range [0, 127], where 60 represents C4, the middle-C on a
     * piano)
     * @param volume The volume (in the range [0, 127])
     */
    @Override
    public CompositionBuilder<MusicModel> addNote(int start, int end, int instrument, int pitch,
        int volume) {

      Note note = new Note(new Sound(pitch), end - start, instrument, volume);
      piece.addNote(note, start);
      return this;
    }
  }

  @Override
  public void addNote(int duration, int octave, int beatNum, String pitch) {


    Pitch p = pitchSelector(pitch);

    Note note = new Note(new Sound(p, octave), duration);
    piece.addNote(note, beatNum);
  }

  @Override
  public void addNote(int duration, int octave, int beatNum, int instrument, int volume,
      String pitch) throws IllegalArgumentException {

    Pitch p = pitchSelector(pitch);

    Note note = new Note(new Sound(p, octave), duration, instrument, volume);
    piece.addNote(note, beatNum);
  }

  @Override
  public INote removeNote(int beatNum, String pitch, int octave) {

    Pitch p = pitchSelector(pitch);

    return piece.removeNote(beatNum, p, octave);
  }


  @Override
  public void editNote(String editor, int beatNum, String pitch, int octave) {

    INoteEditor noteEdit = noteEditorSelector(editor);
    Pitch p = pitchSelector(pitch);
    piece.editNote(noteEdit, beatNum, p, octave);
  }


  @Override
  public void mergePiece(Piece piece) {

    if (piece == null) {
      piece = new Piece();
    }
    this.piece.mergePiece(piece);
  }

  @Override
  public void addPiece(Piece piece) {

    if (piece == null) {
      piece = new Piece();
    }

    this.piece.addPiece(piece);
  }

  @Override
  public String getMIDINotation() {
    return piece.getMIDINotation();
  }

  /**
   * Gets a copy of the Notes that start at given beat number.
   *
   * @param beatNum number to get notes from.
   * @return the list of notes.
   */
  @Override
  public ArrayList<INote> getNotesAt(int beatNum) {
    return piece.getNotesAt(beatNum);
  }

  @Override
  public int minNoteValue() {
    return piece.getMinNoteValue();
  }

  @Override
  public int maxNoteValue() {
    return piece.getMaxNoteValue();
  }

  @Override
  public int maxBeatNum() {
    return piece.lastBeat();
  }


  private Pitch pitchSelector(String pitch) {

    try {
      return Pitch.valueOf(pitch);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid pitch received. Check JavaDoc.");
    }
  }



  private INoteEditor noteEditorSelector(String editor) {

    String[] input = editor.split(" ");

    if (input.length == 2) {

      String editorType = input[0];
      String param = input[1];

      switch (editorType) {

        case "Duration":
          try {return new EditDuration(Integer.valueOf(param));}
          catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid duration, provide an integer.");
          }
        case "Pitch":
          return new EditPitch(pitchSelector(param));
        case "Octave":
          try {return new EditOctave(Integer.valueOf(param));}
          catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid octave, provide an integer.");
          }
        default:
          throw new IllegalArgumentException("Invalid editor given. Reference JavaDoc.");
      }

    }

    throw new IllegalArgumentException("Please give only 2 arguments "
        + "to edit a note. Reference JavaDoc.");
  }

  @Override
  public int getTempo() {
    return tempo;
  }

  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }
}
