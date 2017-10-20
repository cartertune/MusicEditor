![ScreenShot](/resources/Mystery2Screenshot.png)

VIEW:
---------------------------------------------------------------------------------------------------

  interface ViewInterface:

    - an interface that all views implement.
    - has one method "initialize()" which initializes the implementing view.

  interface EnhancedView extends ViewInterface
    - a slightly more complex interface for views that are more complex than a console view.

    METHODS:
      -playPause() // plays or pauses the song.
      -moveRight()/moveLeft() // moves the current beat left or right.
      -jumpToBeginning()/jumpToEnd() // jumps to beginning or end of song.

  interface IGUIView extends EnhancedView:
    - an interface that extends the ViewInterface to allow for methods specific to the GUI

    METHODS:
      -addKeyListener()

  class ViewFactory:

    - a class that has one job of creating a view as specified by the makeView(model, type) method.

    -METHOD:
    - makeView(MusicOperations model, String type):
        model: the model to be applied to the view.
        type: a string of the type of view to be created.
          - "composite" creates a composite view.
          - "gui" creates a gui view.
          - "console" creates a console view.
          - "midi" creates a midi view.


  class CompositeView implements IGUIView:
    -basically it has a GUI view and a Midi view and ensures that they are synchronized.

    FIELDS:
      -GUIViewFrame
      -MidiViewImpl


  class GUIViewFrame extends JFrame implements IGUIView:
    -crates a frame where which contains a NotesPanel for the notes by beat number reader and
     a PianoPanel at the bottom which shows the keys currently being played.

    FIELDS:
      -NotesPanel (is turned into added to a JScrollPane to allow movement.)
      -PianoPanel

  class MidiViewImpl implements EnhancedView:

     - a class that if implemented itself, plays the song from beginning to end, and if a part of a
      CompositeView is  synchronized to play according to guiView interaction.


  class NotesPanel extend JPanel:
    -A JPanel that is laid out to show all the notes played at each beat.

CONTROLLER:
---------------------------------------------------------------------------------------------------

  Controller implements MusicController:
    -adds a keyListener to the view so the following commands are possible:
      -the "VK_LEFT" (left key) moves the GUIView currentBeat to the left
      -the "VK_RIGHT" (right key) moves the currentBeat to the right.
      -the "VK_HOME" (home key) moves the currentBeat to 0, the beginning of the song.
      -the "VK_END" (end key) moves the currentBeat to the end of the song.
      -the "VK_SPACE" (space bar) plays the song if it is paused and pauses it otherwise.

    -adds a mouse listener that adds a note to the model at the currentBeat with note represented
      by the piano key pressed.

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
# MusicEditor
