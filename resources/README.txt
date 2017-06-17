
GUIVIEW:
---------------------------------------------------------------------------------------------------
  interface IGUIView extends ViewInterface:
    - an interface that extends the ViewInterface to allow for methods specific to the GUI

    METHODS:
      -moveRight()/moveLeft() : moves the current beat left or right.
      -addKeyListener()
      -setController()

  class GUIViewFrame extends JFrame implements IGUIView:
    -crates a frame where which contains a NotesPanel for the notes by beat number reader and
     a PianoPanel at the bottom which shows the keys currently being played.

    FIELDS:
      -NotesPanel (is turned into added to a JScrollPane to allow movement.)
      -PianoPanel

  class NotesPanel extend JPanel:
    -A JPanel that is layed out to show all the notes played at each beat.

MODEL:
---------------------------------------------------------------------------------------------------
  class Beat:
    - Represents the Sound played at a single beat and whether it is the beginning of a note
    or a note sustained.

  class BeatNotations:

    - Represents all the Beat at a single beat number. aka all the Beat played at the same time.

  Enum BeatType:

    - Distinguishes a beat between the beginning of a Note or a sustained Note.


  class EditDuration implements INoteEditor:

    -Used to edit a Duration of a given Note.

  class EditOctave implements INoteEditor:

    -Used to edit the octave of a given Note.

  class EditPitch implements INoteEditor:

    -Used to edit the Pitch of a given Note.

  Interface INoteEditor:

    -An interface that has a function edit(Note), which does as expected; it edits a note.

  class MainMusic:

     -A class to demonstrate a basic piece being displayed on console.

  class Music Model implenents MusicOperations:

    -Used to implement methods from interface.


    FIELD:

      - Piece

  interface MusicOperations:

    -Represents the operations guaranteed by the model as follows.


    METHODS:

      -addNote(Note, int) //adds a note at given beat number.

      -removeNote //removes a specified note and returns it

      -editNote //which edits a note in different ways depending on the INoteEditor it was given.

      -mergePiece // merges a given piece with the current one.

      -addPiece // adds the given piece at the end of the current one.

      -getMIDINotation //returns the song as MIDI Notation in String form

  class Notations:

    -Represents the notes that start at the same beat number in a piece. (a List of Note).

  interface INote:

    -has a few methods that can get date from implementing Notes.
  class Note implements INote:

    -Represents a note in the music editor with a duration.

    FIELDS:
      -Sound // the octave and pitch of the note.
      -Duration // the duration a note is played.

  class Piece:

    - Represents a song by using a HashMap<Integer, Notations> where the Integer is the beat number
        and the Notations are all the Notes at that beat number.

  class Sound:

    - Represents a Sound. aka a note without a specified duration.