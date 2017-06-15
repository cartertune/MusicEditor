package cs3500.music.model;

import java.util.ArrayList;

/**
 * A class to represent the model of the MusicEditor.
 */
public class MusicModel implements MusicOperations {


  private Piece piece;

  public MusicModel() {
    this.piece = new Piece();
  }

  @Override
  public void addNote(int duration, int octave, int beatNum, String pitch) {


    Pitch p = pitchSelector(pitch);

    Note note = new Note(new Sound(p, octave), duration);
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
}
